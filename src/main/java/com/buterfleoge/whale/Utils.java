package com.buterfleoge.whale;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 *
 * @author xiezhenzong
 *
 */
public abstract class Utils {

    private static final Logger LOG = LoggerFactory.getLogger(Utils.class);

    public static final boolean isPasswordValid(String password) {
        return true;
    }

    /**
     * Get host IP address
     *
     * @return IP Address
     */
    public static final InetAddress getAddress() {
        try {
            for (Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces(); 
                    interfaces.hasMoreElements();) {
                NetworkInterface networkInterface = interfaces.nextElement();
                if (networkInterface.isLoopback() || networkInterface.isVirtual() || !networkInterface.isUp()) {
                    continue;
                }
                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                if (addresses.hasMoreElements()) {
                    return addresses.nextElement();
                }
            }
        } catch (SocketException e) {
            LOG.error("get address failed", e);
        }
        return null;
    }

    private static final char[] HEX_DIGITS =
            { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    public static String stringMD5(String input) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(input.getBytes("UTF-8"));
            byte[] resultByteArray = messageDigest.digest();
            char[] resultCharArray = new char[resultByteArray.length * 2];
            int index = 0;
            for (byte b : resultByteArray) {
                resultCharArray[index++] = HEX_DIGITS[b >>> 4 & 0xf];
                resultCharArray[index++] = HEX_DIGITS[b & 0xf];
            }
            return new String(resultCharArray);
        } catch (Exception e) {
            return "";
        }
    }

    public static void main(String[] args) {
        String a = "127.0.0.1?zhenzong@icoud.com,12783734648392";

        System.out.println(stringMD5(a));
    }

}
