package com.buterfleoge.whale;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;
import org.springframework.format.number.CurrencyStyleFormatter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.buterfleoge.whale.Constants.DefaultValue;
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
        if (StringUtils.hasText(group.getTitle())) {
            subject.append("(").append(group.getTitle()).append(")");
        }
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

    /**
     * 创建随机字符串
     *
     * @return
     */
    public static String createNonceStr() {
        StringBuilder builder = new StringBuilder(DefaultValue.TOKEN) //
                .append(DefaultValue.SEPARATOR).append(System.currentTimeMillis()) //
                .append(DefaultValue.SEPARATOR).append(Math.random());
        return Utils.stringMD5(builder.toString());
    }

    public static <T> String join(List<T> list, String separator) {
        return join(list, separator, new Printer<T>() {

            @Override
            public String print(T object, Locale locale) {
                return Objects.toString(object, "");
            }
        });
    }

    public static <T> String join(List<T> list, String separator, Printer<T> printer) {
        if (CollectionUtils.isEmpty(list)) {
            return "";
        }
        StringBuilder builder = new StringBuilder(printer.print(list.get(0), Locale.CHINA));
        for (int i = 1, n = list.size(); i < n; i++) {
            builder.append(separator).append(printer.print(list.get(i), Locale.CHINA));
        }
        return builder.toString();
    }

    public static List<String> split(String string, String separator) {
        return split(string, separator, new Parser<String>() {

            @Override
            public String parse(String text, Locale locale) throws ParseException {
                return text;
            }
        });
    }

    public static <T> List<T> split(String string, String separator, Parser<T> parser) {
        if (StringUtils.isEmpty(string)) {
            return new ArrayList<T>(0);
        }
        String[] items = string.split(separator);
        List<T> result = new ArrayList<T>(items.length);
        for (String item : items) {
            try {
                result.add(parser.parse(item, Locale.CHINA));
            } catch (ParseException e) {
                LOG.error("parse fail, item: " + item, e);
            }
        }
        return result;
    }

    public interface ListFilter<T> {
        boolean filter(T target);
    }

    public static <T> List<T> filter(List<T> list, ListFilter<T> filter) {
        if (CollectionUtils.isEmpty(list)) {
            return list;
        }
        List<T> toRemove = new ArrayList<T>(list.size());
        for (T t : list) {
            if (!filter.filter(t)) {
                toRemove.add(t);
            }
        }
        list.removeAll(toRemove);
        return list;
    }

    public static String filterOffUtf8Mb4(String text) {
        try {
            byte[] bytes = text.getBytes("UTF-8");
            ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
            int i = 0;
            while (i < bytes.length) {
                short b = bytes[i];
                if (b > 0) {
                    buffer.put(bytes[i++]);
                    continue;
                }
                b += 256;
                if ((b ^ 0xC0) >> 4 == 0) {
                    buffer.put(bytes, i, 2);
                    i += 2;
                } else if ((b ^ 0xE0) >> 4 == 0) {
                    buffer.put(bytes, i, 3);
                    i += 3;
                } else if ((b ^ 0xF0) >> 4 == 0) {
                    i += 4;
                }
            }
            buffer.flip();
            return new String(buffer.array(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOG.error("un supported encoding", e);
            return "";
        }
    }

    @SafeVarargs
    public static <T> Set<T> asSet(T... elements) {
        Set<T> set = new HashSet<T>(elements.length);
        for (int i = 0; i < elements.length; i++) {
            set.add(elements[i]);
        }
        return set;
    }

    public static void main(String[] args) {
        String a = "127.0.0.1?zhenzong@icoud.com,12783734648392";

        System.out.println(stringMD5(a));
    }

}
