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
     * 普通账户
     */
    USER(0),

    /**
     * 领队
     */
    LEADER(1),

    /**
     * 管理员
     */
    MANAGER(2),

    /**
     * 合作伙伴
     */
    PARTNER(3),

    /**
     * 代理商
     */
    AGENT(4),

    /**
     * 超级管理员
     */
    ROOT(5)

    ;

    private int type;

    private AccountType(int type) {
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
