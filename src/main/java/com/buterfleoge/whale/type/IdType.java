package com.buterfleoge.whale.type;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 证件类型
 * 
 * @author Brent24
 *
 */
public enum IdType {

    /**
     * 身份证
     */
    IDENTIFICATION(0),

    /**
     * 护照
     */
    PASSPORT(1),

    /**
     * 港澳通行证
     */
    H_PASSER(2),

    /**
     * 台胞证
     */
    T_PASSER(3)

    ;

    private int type;

    private IdType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
