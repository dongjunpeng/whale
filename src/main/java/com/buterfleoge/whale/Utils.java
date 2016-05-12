package com.buterfleoge.whale;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.apache.commons.codec.digest.DigestUtils;
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

    public static String stringMD5(String input) {
        return DigestUtils.md5Hex(input);
    }

    public static void main(String[] args) {
        String a = "127.0.0.1?zhenzong@icoud.com,12783734648392";

        System.out.println(stringMD5(a));
    }

}
