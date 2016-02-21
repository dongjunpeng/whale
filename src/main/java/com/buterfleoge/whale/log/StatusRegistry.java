package com.buterfleoge.whale.log;

/**
 * 状态码注册中心，一般，后面两位留作拓展，可以自行定义， 十位以上的 一般标识异常的类型
 * 
 * @author xiezhenzong
 * 
 */
public interface StatusRegistry {

    /**
     * 调用正常，获取状态码
     * 
     * @return int
     */
    int getStatusCode();

    /**
     * 发生异常时，根据异常获取状态码
     * 
     * @param exception 调用发生的异常
     * @return int
     */
    int getStatusCode(Exception exception);

}
