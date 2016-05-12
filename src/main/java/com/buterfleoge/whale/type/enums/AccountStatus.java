package com.buterfleoge.whale.type.enums;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 账户状态
 *
 * @author xiezhenzong
 *
 */
public enum AccountStatus {

    /**
     * 刚注册
     */
    WAIT_COMPLETE_INFO(0),

    /**
     * 账户正常
     */
    OK(1),

    /**
     * 账户注销
     */
    DELETE(2)

    ;

    private int status;

    private AccountStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
