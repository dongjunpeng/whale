package com.buterfleoge.whale;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.MapUtils;
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

    /**
     * 除去数组中的空值和签名参数
     *
     * @param sArray
     *            签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, Object> paraFilter(Map<String, Object> sArray) {
        if (MapUtils.isEmpty(sArray)) {
            return new HashMap<String, Object>(0);
        }
        Map<String, Object> result = new HashMap<String, Object>(sArray.size());
        for (String key : sArray.keySet()) {
            Object value = sArray.get(key);
            if (value != null && !key.equalsIgnoreCase("sign")) {
                result.put(key, value);
            }
        }
        return result;
    }

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param params
     *            需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, Object> params) {
        if (MapUtils.isEmpty(params)) {
            throw new IllegalArgumentException("params can't be null or empty");
        }
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        StringBuilder builder = new StringBuilder();
        String firstKey = keys.get(0);
        builder.append(firstKey).append("=").append(params.get(firstKey));
        for (int i = 1, n = keys.size(); i < n; i++) {
            String key = keys.get(i);
            Object value = params.get(key);
            builder.append("&").append(key).append("=").append(value);
        }
        return builder.toString();
    }

    /**
     * 创建微信的加密串
     * 
     * @param param
     * @param key
     * @return
     */
    public static String createWxSign(Map<String, Object> param, String key) {
        param = paraFilter(param);
        String linkString = Utils.createLinkString(param) + "&key=" + key;
        return Utils.stringMD5(linkString).toUpperCase();
    }

    public static void main(String[] args) {
        String a = "127.0.0.1?zhenzong@icoud.com,12783734648392";

        System.out.println(stringMD5(a));
    }

}
