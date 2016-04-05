package com.buterfleoge.whale;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.util.Assert;

import com.buterfleoge.whale.Constants.Status;

/**
 * 
 * 默认的状态码注册中心，先查询手动注册的状态码，然后查询默认状态码(根据类的名称， exception.getClass().getName())，如果都没有，返回{@code WebStatus.UNKNOW_ERROR}
 * 
 * @author xiezhenzong
 * 
 */
public class SimpleStatusRegistry implements StatusRegistry {

    private static class DefaultStatusRegistryHolder {
        public static final SimpleStatusRegistry INSTANCE = new SimpleStatusRegistry();
    }

    public static SimpleStatusRegistry instance() {
        return DefaultStatusRegistryHolder.INSTANCE;
    }

    /**
     * exception type to status
     */
    private static final Map<String, Integer> DEFAULT_STATUS_MAP = new ConcurrentHashMap<String, Integer>();

    static {
        // 100的异常
        DEFAULT_STATUS_MAP.put("java.lang.IllegalArgumentException", Status.PARAM_ERROR);
        DEFAULT_STATUS_MAP.put("java.lang.IndexOutOfBoundsException", Status.PARAM_ERROR);
        DEFAULT_STATUS_MAP.put("java.lang.IndexOutOfBoundsException", Status.PARAM_ERROR);
        DEFAULT_STATUS_MAP.put("java.lang.ArrayStoreException", Status.PARAM_ERROR);
        DEFAULT_STATUS_MAP.put("com.google.protobuf.InvalidProtocolBufferException", Status.PARAM_ERROR);
        DEFAULT_STATUS_MAP.put("java.io.InvalidObjectException", Status.PARAM_ERROR);
        DEFAULT_STATUS_MAP.put("java.lang.NullPointerException", Status.PARAM_ERROR);

        // 200的异常
        DEFAULT_STATUS_MAP.put("com.buterfleoge.whale.log.NullResponseException", Status.RESPONSE_ERROR);

        // 300的异常
        DEFAULT_STATUS_MAP.put("java.net.SocketTimeoutException", Status.TIMEOUT_ERROR);
        DEFAULT_STATUS_MAP.put("java.util.concurrent.TimeoutException", Status.TIMEOUT_ERROR);
        DEFAULT_STATUS_MAP.put("com.mchange.v2.resourcepool.TimeoutException", Status.TIMEOUT_ERROR);
        DEFAULT_STATUS_MAP.put("org.apache.commons.httpclient.util.TimeoutController.TimeoutException",
                Status.TIMEOUT_ERROR);

        // 400的异常
        DEFAULT_STATUS_MAP.put("java.io.IOException", Status.NETWORK_ERROR); // 调用外部服务的io exception，应该是属于网络异常吧
        DEFAULT_STATUS_MAP.put("java.net.SocketException", Status.NETWORK_ERROR);
        DEFAULT_STATUS_MAP.put("java.net.ConnectException", Status.NETWORK_ERROR);
        DEFAULT_STATUS_MAP.put("java.rmi.ConnectException", Status.NETWORK_ERROR);
        DEFAULT_STATUS_MAP.put("java.rmi.ConnectIOException", Status.NETWORK_ERROR);

        // 500的异常
        DEFAULT_STATUS_MAP.put("java.lang.RuntimeException", Status.SYSTEM_ERROR);

        // 600的异常
        DEFAULT_STATUS_MAP.put("java.rmi.server.SocketSecurityException", Status.AUTH_ERROR);
        DEFAULT_STATUS_MAP.put("org.apache.commons.httpclient.auth.AuthenticationException", Status.AUTH_ERROR);
    }

    private Map<Class<? extends Exception>, Integer> statusMap =
            new ConcurrentHashMap<Class<? extends Exception>, Integer>();

    @Override
    public int getStatusCode() {
        return Status.OK;
    }

    @Override
    public int getStatusCode(Exception exception) {
        Integer status = statusMap.get(exception.getClass());
        if (status != null) {
            return status;
        }
        status = DEFAULT_STATUS_MAP.get(exception.getClass().getName());
        return status != null ? status : Status.UNKNOW_ERROR;
    }

    /**
     * 注册一个异常，状态码映射
     * 
     * @param exceptionType 异常类型
     * @param status 状态码
     */
    protected void registerStatus(Class<? extends Exception> exceptionType, int status) {
        Assert.notNull(exceptionType, "ExceptionType argument can't be null");
        statusMap.put(exceptionType, status);
    }

}
