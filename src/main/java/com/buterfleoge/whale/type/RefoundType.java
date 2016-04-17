/**
 * 
 */
package com.buterfleoge.whale.type;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 退款类型
 * 
 * @author Brent24
 *
 */
public enum RefoundType {

    /**
     * 扣除后退款(退款=付款-n)
     */
    MINUS(0),

    /**
     * 百分比后退还(退款=付款*n%)
     */
    PERCENTAGE(1),

    /**
     * 扣除百分比后退还(退款=付款*(1-n%))
     */
    PERCENTAGE_OFF(2)

    ;

    private int type;

    private RefoundType(int type) {
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
