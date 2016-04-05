package com.buterfleoge.whale.log.annotation;

import java.lang.reflect.AnnotatedElement;

import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.transaction.annotation.SpringTransactionAnnotationParser;

import com.buterfleoge.whale.log.annotation.AutoLog.Exclude;
import com.buterfleoge.whale.log.interceptor.AutoLogAttribute;

/**
 * 
 *
 * @author xiezhenzong
 *
 */
public class SpringAutoLogAnnotationParser implements AutoLogAnnotationParser {

    @Override
    public AutoLogAttribute parseAutoLogAnnotation(AnnotatedElement ae) {
        AnnotationAttributes autoLogAttributes = AnnotatedElementUtils.getMergedAnnotationAttributes(ae, AutoLog.class);
        AnnotationAttributes excludeAttributes = AnnotatedElementUtils.getMergedAnnotationAttributes(ae, Exclude.class);
        if (autoLogAttributes != null || excludeAttributes != null) {
            return parseAutoLogAnnotation(autoLogAttributes, excludeAttributes);
        } else {
            return null;
        }
    }

    protected AutoLogAttribute parseAutoLogAnnotation(AnnotationAttributes autoLogAttributes,
            AnnotationAttributes excludeAttributes) {
        AutoLogAttribute config = new AutoLogAttribute();
        return config;
    }

    @Override
    public boolean equals(Object other) {
        return (this == other || other instanceof SpringTransactionAnnotationParser);
    }

    @Override
    public int hashCode() {
        return SpringTransactionAnnotationParser.class.hashCode();
    }

}
