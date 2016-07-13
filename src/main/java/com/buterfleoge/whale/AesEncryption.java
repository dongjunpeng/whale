/**
 *
 */
package com.buterfleoge.whale;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author xiezhenzong
 *
 */
@Component
public class AesEncryption implements InitializingBean {

    private static final Logger LOG = LoggerFactory.getLogger(AesEncryption.class);

    private static final String ALGORITHM = "AES";

    @Value("${aes.key}")
    private String key;

    private Key spec;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (StringUtils.isEmpty(key)) {
            throw new IllegalArgumentException("invalid value of aes.key configuration");
        }
        spec = new SecretKeySpec(key.getBytes(), ALGORITHM);
    }

    public String encrypt(String text) {
        try {
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, spec);
            byte[] cipherText = cipher.doFinal(text.getBytes());
            return Base64.encodeBase64String(cipherText);
        } catch (Exception e) {
            LOG.error("encrypt failed", e);
            return null;
        }
    }

    public String decrypt(String text) {
        try {
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, spec);
            byte[] dectyptedText = cipher.doFinal(Base64.decodeBase64(text));
            return new String(dectyptedText);
        } catch (Exception e) {
            LOG.error("decrypt failed", e);
            return null;
        }
    }

}
