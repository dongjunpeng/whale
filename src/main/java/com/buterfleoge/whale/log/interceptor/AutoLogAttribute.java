package com.buterfleoge.whale.log.interceptor;

import com.buterfleoge.whale.StatusRegistry;
import com.buterfleoge.whale.log.LogProxy;
import com.buterfleoge.whale.log.MinorTagCreator;

/**
 * 默认的日志切面配置
 * 
 * @author xiezhenzong
 * 
 * @see LogProxyCreator
 * 
 */
public class AutoLogAttribute {

    private String majorTag;

    private MinorTagCreator minorTagCreator;

    private boolean isResponseNullable;

    private StatusRegistry statusRegistry;

    private LogProxy logProxy;

    /**
     * @return the majorTag
     */
    public String getMajorTag() {
        return majorTag;
    }

    /**
     * @param majorTag the majorTag to set
     */
    public void setMajorTag(String majorTag) {
        this.majorTag = majorTag;
    }

    /**
     * @return the minorTagCreator
     */
    public MinorTagCreator getMinorTagCreator() {
        return minorTagCreator;
    }

    /**
     * @param minorTagCreator the minorTagCreator to set
     */
    public void setMinorTagCreator(MinorTagCreator minorTagCreator) {
        this.minorTagCreator = minorTagCreator;
    }

    /**
     * @return the isResponseNullable
     */
    public boolean isResponseNullable() {
        return isResponseNullable;
    }

    /**
     * @param isResponseNullable the isResponseNullable to set
     */
    public void setResponseNullable(boolean isResponseNullable) {
        this.isResponseNullable = isResponseNullable;
    }

    /**
     * @return the statusRegistry
     */
    public StatusRegistry getStatusRegistry() {
        return statusRegistry;
    }

    /**
     * @param statusRegistry the statusRegistry to set
     */
    public void setStatusRegistry(StatusRegistry statusRegistry) {
        this.statusRegistry = statusRegistry;
    }

    /**
     * @return the logger
     */
    public LogProxy getLogger() {
        return logProxy;
    }

    /**
     * @param logProxy the logger to set
     */
    public void setLogger(LogProxy logProxy) {
        this.logProxy = logProxy;
    }

}
