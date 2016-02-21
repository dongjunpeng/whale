package com.buterfleoge.whale.log;

/**
 * 默认的日志切面配置
 * 
 * @author xiezhenzong
 * 
 * @see LogProxyCreator
 * 
 */
public class LogProxyConfig {

    private String majorTag;

    private MinorTagCreator minorTagCreator = new DefaultMinortagCreator();

    private boolean isResponseNullable = false;

    private StatusRegistry statusRegistry = new DefaultStatusRegistry();

    private Logger logger = new DefaultLogger();

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
    public Logger getLogger() {
        return logger;
    }

    /**
     * @param logger the logger to set
     */
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

}
