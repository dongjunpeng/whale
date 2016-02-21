package com.buterfleoge.whale.log;

import org.aopalliance.intercept.MethodInvocation;

/**
 * 默认的日志打印器
 * 
 * @author xiezhenzong
 * 
 */
public class DefaultLogger implements Logger {

    @Override
    public boolean isInvokeLogAllowed() {
        return true;
    }

    @Override
    public void logInvoke(String invokeLog) {
        Log.info(invokeLog);
    }

    @Override
    public void logError(MethodInvocation invocation, Exception exception) {
        Log.error(invocation.getThis() + invocation.getMethod().getName(), exception);
    }

}
