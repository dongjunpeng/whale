package com.buterfleoge.whale.type;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 退款原因
 * 
 * @author Brent24
 *
 */
public enum RefoundReason {

    /**
     * 个人原因
     */
    PERSONAL(0),

    /**
     * 行程取消
     */
    CANCEL(1)

    ;

    private int calculation;

    private RefoundReason(int calculation) {
        this.calculation = calculation;
    }

    public int getCalculation() {
        return calculation;
    }

    public void setCalculation(int calculation) {
        this.calculation = calculation;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
