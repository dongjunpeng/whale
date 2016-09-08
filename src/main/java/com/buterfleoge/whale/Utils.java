package com.buterfleoge.whale;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Locale;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.number.CurrencyStyleFormatter;

import com.buterfleoge.whale.type.entity.TravelGroup;
import com.buterfleoge.whale.type.entity.TravelRoute;

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

    /**
     * 格式化价格
     *
     * @param price
     *            price
     * @return price
     */
    public static final String formatPrice(BigDecimal price) {
        CurrencyStyleFormatter formatter = new CurrencyStyleFormatter();
        formatter.setFractionDigits(0);
        return formatter.print(price, Locale.CHINA);
    }

    /**
     * 获取产品名称
     *
     * @param route
     *            route
     * @param group
     *            group
     * @return product
     */
    public static final String getProductName(TravelRoute route, TravelGroup group) {
        StringBuilder subject = new StringBuilder(route.getName());
        subject.append("(").append(group.getTitle()).append(")");
        return subject.toString();
    }

    public static void main(String[] args) {
        String a = "127.0.0.1?zhenzong@icoud.com,12783734648392";

        System.out.println(stringMD5(a));
    }

}
