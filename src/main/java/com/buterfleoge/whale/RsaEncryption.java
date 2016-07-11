/**
 *
 */
package com.buterfleoge.whale;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author xiezhenzong
 *
 */
@Component
public class RsaEncryption implements InitializingBean {

    private static final Logger LOG = LoggerFactory.getLogger(RsaEncryption.class);

    private static final String ALGORITHM = "RSA";

    @Value("${rsa.private.key}")
    private String privateKeyPath;

    @Value("${rsa.public.key}")
    private String publicKeyPath;

    private PrivateKey privateKey;

    private PublicKey publicKey;

    @Override
    public void afterPropertiesSet() throws Exception {
        File privateKeyFile = new File(privateKeyPath);
        File publicKeyFile = new File(publicKeyPath);
        if (!privateKeyFile.exists() || !publicKeyFile.exists()) {
            generateKey(privateKeyFile, publicKeyFile);
        } else {
            privateKey = (PrivateKey) readObjectFromFile(privateKeyFile);
            publicKey = (PublicKey) readObjectFromFile(publicKeyFile);
        }
    }

    public byte[] encrypt(String text) {
        byte[] cipherText = null;
        try {
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            cipherText = cipher.doFinal(text.getBytes());
            return cipherText;
        } catch (Exception e) {
            LOG.error("encrypt failed", e);
            return null;
        }
    }

    public String decrypt(byte[] text) {
        byte[] dectyptedText = null;
        try {
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            dectyptedText = cipher.doFinal(text);
            return new String(dectyptedText);
        } catch (Exception e) {
            LOG.error("decrypt failed", e);
            return null;
        }
    }

    private void generateKey(File privateKeyFile, File publicKeyFile) throws Exception {
        final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
        keyGen.initialize(1024);
        final KeyPair key = keyGen.generateKeyPair();
        if (privateKeyFile.exists()) {
            privateKeyFile.delete();
        }
        if (publicKeyFile.exists()) {
            publicKeyFile.delete();
        }
        privateKeyFile.createNewFile();
        publicKeyFile.createNewFile();

        privateKey = key.getPrivate();
        publicKey = key.getPublic();
        ObjectOutputStream privateKeyOS = new ObjectOutputStream(new FileOutputStream(privateKeyFile));
        privateKeyOS.writeObject(privateKey);
        privateKeyOS.close();
        ObjectOutputStream publicKeyOS = new ObjectOutputStream(new FileOutputStream(publicKeyFile));
        publicKeyOS.writeObject(publicKey);
        publicKeyOS.close();
    }

    private Object readObjectFromFile(File file) throws Exception {
        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream(file));
            return inputStream.readObject();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
