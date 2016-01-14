package com.buterfleoge.whale.util;

import java.util.regex.Pattern;

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

    public static final void rejectIfNotEmail(String email) {
        rejectIfEmpty(email, null);
    }

    public static final void rejectIfNotEmail(String email, String errMsg) {
        rejectIfEmpty(email, "email argument can't be empty.");

        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        if (!pattern.matcher(email).matches()) {
            if (StringUtils.isEmpty(email)) {
                throw new IllegalArgumentException("email is invalid, email: " + email);
            } else {
                throw new IllegalArgumentException(errMsg);
            }
        }
    }

}
