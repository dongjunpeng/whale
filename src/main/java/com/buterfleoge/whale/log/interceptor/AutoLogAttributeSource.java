package com.buterfleoge.whale.log.interceptor;

import java.lang.reflect.Method;

/**
 * 
 *
 * @author xiezhenzong
 *
 */
public interface AutoLogAttributeSource {

    AutoLogAttribute getLogEntrySource(Method method, Class<?> targetClass);

}
