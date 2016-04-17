package com.buterfleoge.whale.type;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 优惠类型
 * 
 * @author Brent24
 *
 */
public enum DiscountType {

    /**
     * 一次性优惠
     */
    DISPOSABLE(0),

    /**
     * 优惠券
     */
    COUPON(1),

    /**
     * 多人优惠
     */
    MULTIPLE(2)

    ;

    private int type;

    private DiscountType(int type) {
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
