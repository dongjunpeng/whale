package com.buterfleoge.whale.log;

/**
 * 
 * 日志打印器，使得使用者可以自己决定日志如何打印
 * 
 * @author xiezhenzong
 * 
 */
public interface LogProxy {

    String DEFAULT_LOG_PROXY = "logProxy";

    /**
     * 是否可以打印invoke日志
     * 
     * @return boolean
     */
    boolean isInvokeLogAllowed();

    /**
     * 打印invoke日志
     * 
     * @param info 调用信息
     */
    void logInvoke(LogInfo info);

    /**
     * 打印调用异常日志
     * 
     * @param info 调用信息
     * @param exception 调用发生的异常
     */
    void logError(LogInfo info, Exception exception);

}
