package com.buterfleoge.whale.log.interceptor;

import java.util.Set;

import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.buterfleoge.whale.StatusRegistry;
import com.buterfleoge.whale.log.LogProxy;
import com.buterfleoge.whale.log.MinorTagCreator;

/**
 * 创建log proxy
 * 
 * 
 * @author xiezhenzong
 * 
 * @see LogProxyCreator
 * @see AutoLogAttribute
 */
public class LogProxyCreator extends AbstractAutoProxyCreator implements InitializingBean {

    /**
     * serial version uid
     */
    private static final long serialVersionUID = -1339134239404232406L;

    /**
     * 如果你想通过LogProxyCreator来配置自己的logInterceptor，则需要把logInterceptor属性先配置好
     */
    private LogInterceptor logInterceptor = new LogInterceptor();

    private String targetName;

    private String service;

    private Set<String> serviceMethodSet;

    private String serviceMethods;

    @Override
    public void afterPropertiesSet() {
        Assert.notNull(logInterceptor, "logINterceptor can't be null");
        if (StringUtils.isEmpty(targetName)) {
            throw new IllegalArgumentException("targetName can't be null");
        }
    }

    @Override
    protected Object[] getAdvicesAndAdvisorsForBean(Class<?> beanClass, String beanName,
            TargetSource customTargetSource) throws BeansException {
        if (!targetName.equals(beanName)) {
            return DO_NOT_PROXY;
        }

        return new Object[] { new DefaultPointcutAdvisor(new LogPointcut(service, serviceMethodSet, serviceMethods),
                logInterceptor) };
    }

    // aop 相关配置的getter&setter
    /**
     * @return the targetName
     */
    public String getTargetName() {
        return targetName;
    }

    /**
     * @param targetName the targetName to set
     */
    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    /**
     * @return the service
     */
    public String getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(String service) {
        this.service = service;
    }

    /**
     * @return the serviceMethodSet
     */
    public Set<String> getServiceMethodSet() {
        return serviceMethodSet;
    }

    /**
     * @param serviceMethodSet the serviceMethodSet to set
     */
    public void setServiceMethodSet(Set<String> serviceMethodSet) {
        this.serviceMethodSet = serviceMethodSet;
    }

    /**
     * @return the serviceMethods
     */
    public String getServiceMethods() {
        return serviceMethods;
    }

    /**
     * @param serviceMethods the serviceMethods to set
     */
    public void setServiceMethods(String serviceMethods) {
        this.serviceMethods = serviceMethods;
    }

    // 方便log proxy 配置
    /**
     * @return the majorTag
     */
    public String getMajorTag() {
        return logInterceptor.getMajorTag();
    }

    /**
     * @param majorTag the majorTag to set
     */
    public void setMajorTag(String majorTag) {
        logInterceptor.setMajorTag(majorTag);
    }

    /**
     * @return the minorTagCreator
     */
    public MinorTagCreator getMinorTagCreator() {
        return logInterceptor.getMinorTagCreator();
    }

    /**
     * @param minorTagCreator the minorTagCreator to set
     */
    public void setMinorTagCreator(MinorTagCreator minorTagCreator) {
        logInterceptor.setMinorTagCreator(minorTagCreator);
    }

    /**
     * @return the isResponseNullable
     */
    public boolean isResponseNullable() {
        return logInterceptor.isResponseNullable();
    }

    /**
     * @param isResponseNullable the isResponseNullable to set
     */
    public void setResponseNullable(boolean isResponseNullable) {
        logInterceptor.setResponseNullable(isResponseNullable);
    }

    /**
     * @return the statusRegistry
     */
    public StatusRegistry getStatusRegistry() {
        return logInterceptor.getStatusRegistry();
    }

    /**
     * @param statusRegistry the statusRegistry to set
     */
    public void setStatusRegistry(StatusRegistry statusRegistry) {
        logInterceptor.setStatusRegistry(statusRegistry);
    }

    /**
     * @return the logger
     */
    public LogProxy getLogger() {
        return logInterceptor.getLogger();
    }

    /**
     * @param logProxy the logger to set
     */
    public void setLogger(LogProxy logProxy) {
        logInterceptor.setLogger(logProxy);
    }

}
