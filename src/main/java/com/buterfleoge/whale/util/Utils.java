package com.buterfleoge.whale.util;

import org.apache.commons.lang.StringUtils;

/**
 * 
 *
 * @author xiezhenzong
 *
 */
public abstract class Utils {

    public static final void rejectIfNull(Object value) {
        rejectIfNull(value, null);
    }

    public static final void rejectIfNull(Object value, String errMsg) {
        if (value == null) {
            if (StringUtils.isEmpty(errMsg)) {
                throw new IllegalArgumentException("The argument value is empty");
            } else {
                throw new IllegalArgumentException(errMsg);
            }
        }
    }

    public static final void rejectIfEmpty(String value) {
        rejectIfEmpty(value, null);
    }

    public static final void rejectIfEmpty(String value, String errMsg) {
        if (StringUtils.isEmpty(value)) {
            if (StringUtils.isEmpty(errMsg)) {
                throw new IllegalArgumentException("The argument value is empty");
            } else {
                throw new IllegalArgumentException(errMsg);
            }
        }
    }

}
