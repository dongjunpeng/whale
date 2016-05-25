package com.buterfleoge.whale.type.formatter;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author xiezhenzong
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
public @interface ImagePathFormat {

    Prefix prefix() default Prefix.DEFAULT;

    enum Prefix {
        
        DEFAULT(""),
        
        ROUTE("route");
        
        private String prefix;
        
        private Prefix(String prefix) {
            this.prefix = prefix;
        }
        
        public String getPrefix() {
            return prefix;
        }
    }

}
