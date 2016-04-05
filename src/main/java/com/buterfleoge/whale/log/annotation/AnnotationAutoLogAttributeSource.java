package com.buterfleoge.whale.log.annotation;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.util.Assert;

import com.buterfleoge.whale.log.interceptor.AbstractFallbackAutoLogAttributeSource;
import com.buterfleoge.whale.log.interceptor.AutoLogAttribute;

/**
 * 
 *
 * @author xiezhenzong
 *
 */
public class AnnotationAutoLogAttributeSource extends AbstractFallbackAutoLogAttributeSource {

    private final boolean publicMethodsOnly;

    private final Set<AutoLogAnnotationParser> annotationParsers;

    public AnnotationAutoLogAttributeSource() {
        this(true);
    }

    public AnnotationAutoLogAttributeSource(boolean publicMethodsOnly) {
        this.publicMethodsOnly = publicMethodsOnly;
        this.annotationParsers = new LinkedHashSet<AutoLogAnnotationParser>(2);
        this.annotationParsers.add(new SpringAutoLogAnnotationParser());
    }

    public AnnotationAutoLogAttributeSource(AutoLogAnnotationParser annotationParser) {
        this.publicMethodsOnly = true;
        Assert.notNull(annotationParser, "CacheAnnotationParser must not be null");
        this.annotationParsers = Collections.singleton(annotationParser);
    }

    public AnnotationAutoLogAttributeSource(AutoLogAnnotationParser...annotationParsers) {
        this.publicMethodsOnly = true;
        Assert.notEmpty(annotationParsers, "At least one CacheAnnotationParser needs to be specified");
        Set<AutoLogAnnotationParser> parsers = new LinkedHashSet<AutoLogAnnotationParser>(annotationParsers.length);
        Collections.addAll(parsers, annotationParsers);
        this.annotationParsers = parsers;
    }

    @Override
    protected AutoLogAttribute findAutoLogConfig(Method specificMethod) {
        return determineCacheOperations(specificMethod);
    }

    @Override
    protected AutoLogAttribute findAutoLogConfig(Class<?> declaringClass) {
        return determineCacheOperations(declaringClass);
    }

    @Override
    protected boolean allowPublicMethodsOnly() {
        return this.publicMethodsOnly;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AnnotationAutoLogAttributeSource)) {
            return false;
        }
        AnnotationAutoLogAttributeSource otherCos = (AnnotationAutoLogAttributeSource) other;
        return (this.annotationParsers.equals(otherCos.annotationParsers)
                && this.publicMethodsOnly == otherCos.publicMethodsOnly);
    }

    @Override
    public int hashCode() {
        return this.annotationParsers.hashCode();
    }

    protected AutoLogAttribute determineCacheOperations(AnnotatedElement ae) {
        if (ae.getAnnotations().length > 0) {
            for (AutoLogAnnotationParser annotationParser : this.annotationParsers) {
                AutoLogAttribute attr = annotationParser.parseAutoLogAnnotation(ae);
                if (attr != null) {
                    return attr;
                }
            }
        }
        return null;
    }
}
