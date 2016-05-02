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
     * 单人
     */
    COUNT_1(1),

    /**
     * 2人同行
     */
    COUNT_2(2),

    /**
     * 3人同行
     */
    COUNT_3(3),
    /**
     * 4人同行
     */
    COUNT_4(4),

    /**
     * 5人同行
     */
    COUNT_5(5),

    /**
     * 下单时间优惠
     */
    TIME_ORDER(6),

    /**
     * 出行时间优惠
     */
    TIME_TRAVEL(7),

    /**
     * 路线优惠
     */
    ROUTE(8),

    /**
     * 学生证优惠
     */
    STUDENT(9);

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
