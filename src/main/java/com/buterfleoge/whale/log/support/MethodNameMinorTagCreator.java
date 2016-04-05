package com.buterfleoge.whale.log.support;

import org.aopalliance.intercept.MethodInvocation;

import com.buterfleoge.whale.log.MinorTagCreator;

/**
 * 
 * 使用被调用的方法名作为minor tag
 * 
 * @author xiezhenzong
 * 
 */
public class MethodNameMinorTagCreator implements MinorTagCreator {

    private static class MethodnameMinorTagCreatorHolder {
        public static final MethodNameMinorTagCreator INSTANCE = new MethodNameMinorTagCreator();
    }

    public static MethodNameMinorTagCreator instance() {
        return MethodnameMinorTagCreatorHolder.INSTANCE;
    }

    @Override
    public String createMinorTag(MethodInvocation invocation) {
        return invocation.getMethod().getName();
    }

}
