package com.buterfleoge.whale.log;

import org.aopalliance.intercept.MethodInvocation;

/**
 * 
 * 日志打印器，使得使用者可以自己决定日志如何打印
 * 
 * @author xiezhenzong
 * 
 */
public interface Logger {

    /**
     * 是否可以打印invoke日志
     * 
     * @return boolean
     */
    boolean isInvokeLogAllowed();

    /**
     * 打印invoke日志， invokeLog将会是格式化后的日志串，可以直接打印
     * 
     * @param invokeLog invokeLog
     */
    void logInvoke(String invokeLog);

    /**
     * 打印调用异常日志，注意：调用异常时，我们不仅需要打印异常栈信息，可能还需要打印invoke日志
     * 
     * @param exception 调用发生的异常
     */
    void logError(MethodInvocation invocation, Exception exception);

}
