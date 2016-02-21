package com.buterfleoge.whale.type;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 优惠计算方式
 * 
 * @author Brent24
 *
 */
public enum DiscountCalculation {
    // 价格单位为分，存储类型为long，尽可能不用百分比优惠，否则计算问题处理非常麻烦。

    /**
     * 减少(付款=原价-n)
     */
    MINUS(0),

    /**
     * 百分比(付款=原价*n%)
     */
    PERCENTAGE(1),

    /**
     * 减百分比(付款=原价*(1-n%)),
     */
    PERCENTAGE_OFF(2),

    ;

    private int calculation;

    private DiscountCalculation(int calculation) {
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
