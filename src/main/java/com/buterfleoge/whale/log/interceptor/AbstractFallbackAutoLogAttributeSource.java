package com.buterfleoge.whale.log.interceptor;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import org.springframework.aop.support.AopUtils;
import org.springframework.util.ClassUtils;

/**
 * 
 *
 * @author xiezhenzong
 *
 */
public abstract class AbstractFallbackAutoLogAttributeSource implements AutoLogAttributeSource {

    /**
     * 内部使用，表示方法或者类没有找到AutoLogConfig
     */
    private static final AutoLogAttribute NULL_AUTO_LOG_CONFIG = new AutoLogAttribute();

    final Map<Class<?>, AutoLogAttribute> classConfigCache = new HashMap<Class<?>, AutoLogAttribute>(32);
    final Map<Method, AutoLogAttribute> methodConfigCache = new HashMap<Method, AutoLogAttribute>(256);

    /**
     * {@inheritDoc}
     * 
     * 从method，targetClass上查找AutoLogConfig,如果没有查找到，则返回null。 本方法，首先会获取真正非代理的类，然后获取真正的方法，（See，
     * {@link ClassUtils#getUserClass(Class)} and {@link AopUtils#getMostSpecificMethod(Method, Class)}）,
     * 如果在真正方法上没有找到的话，则查找它的声明类，如果它的声明类也没有找到，则查找原始方法，如果还没有找到，则查找原始方法的声明类
     * 
     */
    @Override
    public AutoLogAttribute getLogEntrySource(Method method, Class<?> targetClass) {
        AutoLogAttribute config = methodConfigCache.get(method);
        if (config == null) {
            config = computeAutoLogConfig(targetClass, method);
            methodConfigCache.put(method, config != null ? config : NULL_AUTO_LOG_CONFIG);
        }
        return config == NULL_AUTO_LOG_CONFIG ? null : config;
    }

    private AutoLogAttribute getAutoLogConfig(Class<?> targetClass) {
        AutoLogAttribute config = classConfigCache.get(targetClass);
        if (config == null) {
            config = findAutoLogConfig(targetClass);
            classConfigCache.put(targetClass, config != null ? config : NULL_AUTO_LOG_CONFIG);
        }
        return config == NULL_AUTO_LOG_CONFIG ? null : config;
    }

    /**
     * 计算method对应的AutoLogConfig，如果没有找到，则返回null
     * 
     * @param targetClass
     * @param method
     * @return
     */
    private AutoLogAttribute computeAutoLogConfig(Class<?> targetClass, Method method) {
        if (allowPublicMethodsOnly() && !Modifier.isPublic(method.getModifiers())) {
            return null;
        }

        Class<?> userClass = ClassUtils.getUserClass(targetClass);
        Method specificMethod = AopUtils.getMostSpecificMethod(method, userClass);

        AutoLogAttribute config = findAutoLogConfig(specificMethod);
        if (config != null) {
            return config;
        }

        config = getAutoLogConfig(specificMethod.getDeclaringClass());
        if (config != null) {
            return config;
        }

        if (specificMethod != method) {
            config = findAutoLogConfig(method);
            if (config != null) {
                return config;
            }
            config = getAutoLogConfig(method.getDeclaringClass());
            if (config != null) {
                return config;
            }
        }
        return config;
    }

    /**
     * 从方法上查找AutoLogConfig，如果没有找到，则返回null
     * 
     * @param specificMethod
     * @return
     */
    protected abstract AutoLogAttribute findAutoLogConfig(Method specificMethod);

    /**
     * 从类上查找AutoLogConfig，如果没有找到，则返回null
     * 
     * @param declaringClass
     * @return
     */
    protected abstract AutoLogAttribute findAutoLogConfig(Class<?> declaringClass);

    /**
     * 是否只允许公有方法，默认不是
     * 
     * @return
     */
    protected boolean allowPublicMethodsOnly() {
        return false;
    }

}
