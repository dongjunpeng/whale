package com.buterfleoge.whale.log.annotation;

import java.lang.reflect.AnnotatedElement;

import com.buterfleoge.whale.log.interceptor.AutoLogAttribute;

/**
 * 
 *
 * @author xiezhenzong
 *
 */
public interface AutoLogAnnotationParser {

    AutoLogAttribute parseAutoLogAnnotation(AnnotatedElement ae);

}
