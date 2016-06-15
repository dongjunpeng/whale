package com.buterfleoge.whale.type;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 退款原因
 * 
 * @author Brent24
 *
 */
public enum RefoundStatus {

    /**
     * 退款产生
     */
    CREATED(0),

    /**
     * 确认退款
     */
    CONFIRMED(1),

    /**
     * 完成退款
     */
    REFOUNDED(2)

    ;

    private int status;

    private RefoundStatus(int status) {
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
