package com.buterfleoge.whale.log;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * 
 * 外部服务代理切点定义
 * 
 * @author xiezhenzong
 * 
 */
public class LogPointcut extends StaticMethodMatcherPointcut {

    private static final String SPLITOR = ",";
    private static final Object OBJ = new Object();

    /**
     * 需要代理的方法集合，把map当作set用
     */
    private Map<String, Object> proxyMethods = new ConcurrentHashMap<String, Object>();

    /**
     * 构造代理的切面
     * 
     * @param service 需要代理的接口的方法
     * @param serviceMethodSet method set
     * @param serviceMethods method
     */
    public LogPointcut(String service, Set<String> serviceMethodSet, String serviceMethods) {
        addServiceMethod(service);
        addMethodSet(serviceMethodSet);
        addMethods(serviceMethods);

        if (proxyMethods.isEmpty()) {
            throw new IllegalArgumentException("No any proxy method!");
        }
    }

    /**
     * 从外部服务的接口中添加要代理的方法
     * 
     * @param service 外部服务的接口
     */
    private void addServiceMethod(String service) {
        try {
            Class<?> clazz = Class.forName(service);
            if (!clazz.isInterface()) {
                throw new IllegalArgumentException("Must be a interface, service: " + service);
            }
            Method[] methods = clazz.getMethods();
            if (methods.length > 0) {
                for (Method method : methods) {
                    proxyMethods.put(method.getName(), OBJ);
                }
            }
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Can't load class: " + service);
        }
    }

    /**
     * 
     * 从集合配置中添加要代理的方法
     * 
     * @param serviceMethodSet 需要代理的方法集合
     */
    private void addMethodSet(Set<String> serviceMethodSet) {
        if (!CollectionUtils.isEmpty(serviceMethodSet)) {
            for (String method : serviceMethodSet) {
                proxyMethods.put(method, OBJ);
            }
        }
    }

    /**
     * 从配置中添加需要代理的方法，可以用","来分隔
     * 
     * @param serviceMethods 需要代理的方法
     */
    private void addMethods(String serviceMethods) {
        if (!StringUtils.isEmpty(serviceMethods)) {
            String[] methods = serviceMethods.split(SPLITOR);
            for (String method : methods) {
                proxyMethods.put(method, OBJ);
            }
        }
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return proxyMethods.containsKey(method.getName());
    }

}
