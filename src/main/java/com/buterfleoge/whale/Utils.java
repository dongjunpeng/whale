package com.buterfleoge.whale;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
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
     * get local host
     *
     * @return if exception occur, then return Unknown host.
     */
    public static final String getLocalHost() {
        InetAddress address = getAddress();
        return address != null ? address.getHostAddress() : "Unknown host";
    }

    /**
     * Get host IP address
     *
     * @return IP Address
     */
    public static final InetAddress getAddress() {
        try {
            for (Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces(); interfaces
                    .hasMoreElements();) {
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

    public static final String stringMD5(String input) {
        return DigestUtils.md5Hex(input);
    }

    /**
     * 返回枚举的toString字符串
     *
     * @param enumObj
     *            枚举对象
     * @return string
     */
    public static final String enumToString(Enum<?> enumObj) {
        return ReflectionToStringBuilder.toString(enumObj, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public static void main(String[] args) {
        String a = "127.0.0.1?zhenzong@icoud.com,12783734648392";

        System.out.println(stringMD5(a));
    }

}
