package com.buterfleoge.whale.log;

import org.aopalliance.intercept.MethodInvocation;

/**
 * minor tag产生器，根据调用信息来创建minor tag， 默认实现是{@code DefaultMinortagCreator}
 * 
 * @author xiezhenzong
 * 
 */
public interface MinorTagCreator {

    String DEFAULT_MINOR_TAG_CREATOR = "minorTagCreator";

    /**
     * 根据调用信息来创建minor tag
     * 
     * @param invocation invocation
     * @return minor tag
     */
    String createMinorTag(MethodInvocation invocation);

}
