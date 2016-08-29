package com.buterfleoge.whale.log.interceptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.buterfleoge.whale.exception.NullResponseException;
import com.buterfleoge.whale.log.LogProxy;
import com.buterfleoge.whale.log.StatusRegistry;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 调用拦截器
 * 
 * @author xiezhenzong
 * 
 */
public class LogInterceptor extends AutoLogAttribute implements MethodInterceptor, InitializingBean {

    private static final Long DEFAULT_ACCOUNTID = Long.valueOf(0L);

    @Override
    public void afterPropertiesSet() {
        Assert.notNull(getLogger(), "LogInterceptor requires not null logger");
        Assert.notNull(getMinorTagCreator(), "LogInterceptor requires not null minorTagCreator");
        Assert.notNull(getStatusRegistry(), "LogInterceptor requires not null statusRegistry");
    }

    /**
     * {@inheritDoc}
     * 
     * 对外部服务的调用进行耗时计算，异常处理，并且在发生异常的时候调用{@code failedInvoke}
     * 
     * @throws Throwable 调用异常
     */
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        LogProxy logProxy = getLogger();
        StatusRegistry statusRegistry = getStatusRegistry();
        Object response = null;
        int status = -1;
        long startTime = -1;
        long endTime = -1;

        try {
            startTime = System.currentTimeMillis();
            response = invocation.proceed();
            if (!isResponseNullable() && response == null) {
                throw new NullResponseException("Invoke return unexpect null response!");
            }
            status = statusRegistry.getStatusCode();
            return response;
        } catch (Exception e) {
            status = statusRegistry.getStatusCode(e);
            // logProxy.logError(invocation, e);
            failedInvoke(invocation, e, status);
            throw e;
        } finally {
            endTime = System.currentTimeMillis();
            if (logProxy.isInvokeLogAllowed()) {
                String invokeLog = createInvokeLog(invocation, response, startTime, endTime, status);
                logProxy.logInvoke(null);
            }
        }
    }

    /**
     * 调用异常发生了，进行处理，这里抛出的异常将直接抛出到调用处
     * 
     * @param invocation invocation
     * @param exception exception
     * @param statusCode statusCode
     */
    protected void failedInvoke(MethodInvocation invocation, Exception exception, int statusCode) {
    }

    /**
     * 
     * 创建invoke 日志
     * 
     * @param invocation invocation
     * @param response response
     * @param startTime startTime
     * @param endTime endTime
     * @param status status
     * @return invoke log
     */
    protected String createInvokeLog(MethodInvocation invocation, Object response, long startTime, long endTime,
            int status) {
        String majorTag = getMajorTag();
        String minorTag = getMinorTagCreator().createMinorTag(invocation);
        Object[] args = invocation.getArguments();
        Long accountid = getAccountid(args);
        StringBuilder sb = new StringBuilder();
        sb.append("[MAJOR=").append(majorTag);
        sb.append("][MINOR=").append(minorTag);
        sb.append("][ACCOUNTID=").append(accountid);
        sb.append("][REQUEST=").append(getRequestJson(args));
        sb.append("][RESPONSE=").append(toJson(response));
        sb.append("][START=").append(startTime);
        sb.append("][END=").append(endTime);
        sb.append("][TIMECOST=").append(endTime - startTime);
        sb.append("][STATUS=").append(status).append("]");
        return sb.toString();
    }

    /**
     * 如果调用参数第一个值是long类型，则当作accountid, 否则返回默认accountid
     * 
     * @param args args
     * @return accountid
     */
    protected Long getAccountid(Object[] args) {
        if (args.length > 0) {
            Object arg0 = args[0];
            return (arg0 instanceof Long) ? (Long) arg0 : DEFAULT_ACCOUNTID;
        } else {
            return DEFAULT_ACCOUNTID;
        }
    }

    /**
     * 
     * 返回invoke日志中request的json串
     * 
     * @param args args
     * @return json
     */
    protected String getRequestJson(Object[] args) {
        List<Object> argsList = new ArrayList<Object>(Arrays.asList(args));
        if (argsList.size() > 0 && (argsList.get(0) instanceof Long)) { // remove userid if exist
            argsList.remove(0);
        }
        if (argsList.size() == 1) {
            return toJson(argsList.get(0));
        } else {
            return toJson(argsList.toArray());
        }
    }

    private String toJson(Object obj) {
        ObjectMapper om = new ObjectMapper();
        try {
            return om.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return "object to json error";
        }
    }

}
