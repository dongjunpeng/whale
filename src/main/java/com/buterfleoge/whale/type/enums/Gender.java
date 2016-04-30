package com.buterfleoge.whale.type.enums;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 性别
 * 
 * @author Brent24
 *
 */
public enum Gender {
    /**
     * 未知
     */
    UNKNOW(0),

    /**
     * 男
     */
    MALE(1),

    /**
     * 女
     */
    FEMALE(2)

    ;

    private int gender;

    private Gender(int gender) {
        this.gender = gender;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
