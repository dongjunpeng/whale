package com.buterfleoge.whale.type;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.buterfleoge.whale.Utils;

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

    public static final DiscountType valueOf(int type) {
        for (DiscountType at : values()) {
            if (at.getType() == type) {
                return at;
            }
        }
        throw new IllegalArgumentException("Can't find DiscountType, type: " + type);
    }

    public static DiscountType getDiscountType(int discountType) {
        for (DiscountType type : values()) {
            if (type.getType() == discountType) {
                return type;
            }
        }
        throw new RuntimeException("Not found discount, discountType: " + discountType);
    }

    private static final Set<DiscountType> POLICY = new HashSet<DiscountType>();

    static {
        // 优惠策略
        POLICY.add(DiscountType.COUNT_1);
        POLICY.add(DiscountType.COUNT_2);
        POLICY.add(DiscountType.COUNT_3);
        POLICY.add(DiscountType.COUNT_4);
        POLICY.add(DiscountType.COUNT_5);
        POLICY.add(DiscountType.ROUTE);
        POLICY.add(DiscountType.TIME_ORDER);
        POLICY.add(DiscountType.TIME_TRAVEL);
    }

    public static Set<DiscountType> getDiscountPolicy() {
        return Collections.unmodifiableSet(POLICY);
    }

    @Override
    public String toString() {
        return Utils.enumToString(this);
    }

}
