package com.buterfleoge.whale.type;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 账户类型
 *
 * @author xiezhenzong
 *
 */
public enum AccountType {

    /**
     * 普通用户
     */
    user(0),

    /**
     * 普通客户
     */
    custom(1),

    ;

    private int type;

    private AccountType(int type) {
        this.type = type;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
