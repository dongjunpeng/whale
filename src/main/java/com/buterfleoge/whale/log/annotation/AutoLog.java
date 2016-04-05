package com.buterfleoge.whale.log.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 
 *
 * @author xiezhenzong
 *
 */
@Documented
@Target({ TYPE, METHOD })
@Retention(RUNTIME)
public @interface AutoLog {

    /**
     * alias for {@code majorTag}
     */
    String value() default "";

    /**
     * major tag
     */
    String majorTag() default "";

    /**
     * Whether the return value of method invocation can be null or not. Default is false
     */
    boolean isResponseNullable() default false;

    /**
     * 
     * 
     * @author xiezhenzong
     *
     */
    @Documented
    @Target({ TYPE, METHOD })
    @Retention(RUNTIME)
    public @interface Exclude {
        String[] value() default {};
    }

}
