package com.buterfleoge.whale.log.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.buterfleoge.whale.log.LogInfo;
import com.buterfleoge.whale.log.LogProxy;

/**
 * 默认的日志打印器
 * 
 * @author xiezhenzong
 * 
 */
public class SimpleLogProxy implements LogProxy {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleLogProxy.class);

    private static class DefaultLogProxyHolder {
        public static final SimpleLogProxy INSTANCE = new SimpleLogProxy();
    }

    public static SimpleLogProxy instance() {
        return DefaultLogProxyHolder.INSTANCE;
    }

    @Override
    public boolean isInvokeLogAllowed() {
        return true;
    }

    @Override
    public void logInvoke(LogInfo info) {
        LOG.info(info.toString());
    }

    @Override
    public void logError(LogInfo info, Exception exception) {
        // LOG.error(invocation.getThis() + invocation.getMethod().getName(), exception);
    }

}
