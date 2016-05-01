package com.buterfleoge.whale.type.enums;

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
     * 优惠券
     */
    COUPON(0),

    /**
     * 优惠政策
     */
    POLICY(1),

    /**
     * 双人同行
     */
    POLICY_DOUBLE(2);

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
