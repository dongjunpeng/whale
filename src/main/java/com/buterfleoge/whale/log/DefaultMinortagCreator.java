package com.buterfleoge.whale.log;

import org.aopalliance.intercept.MethodInvocation;

/**
 * 
 * 使用被调用的方法名作为minor tag
 * 
 * @author xiezhenzong
 * 
 */
public class DefaultMinortagCreator implements MinorTagCreator {

    @Override
    public String createMinorTag(MethodInvocation invocation) {
        return invocation.getMethod().getName();
    }

}
