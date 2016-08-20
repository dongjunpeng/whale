package com.buterfleoge.whale.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author xiezhenzong
 *
 */
@Documented
@Target({ METHOD, FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = IdExistConstraintValidator.class)
public @interface IdExist {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    IdType type();

    boolean nullable() default true;

    /**
     *
     * @author xiezhenzong
     *
     */
    @Documented
    @Target({ METHOD, FIELD })
    @Retention(RUNTIME)
    public @interface List {

        IdExist[] value();

    }

    /**
     *
     * @author xiezhenzong
     *
     */
    public enum IdType {

        ROUTE_ID,

        GROUP_ID,

        ORDER_ID;

    }

}
