package com.buterfleoge.whale.type;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.buterfleoge.whale.EnumObject;

/**
 * 优惠类型
 *
 * @author Brent24
 *
 */
public class DiscountType extends EnumObject {

    /**
     * 优惠券
     */
    public static final DiscountType COUPON = new DiscountType(0);

    /**
     * 单人
     */
    public static final DiscountType COUNT_1 = new DiscountType(1);

    /**
     * 2人同行
     */
    public static final DiscountType COUNT_2 = new DiscountType(2);

    /**
     * 3人同行
     */
    public static final DiscountType COUNT_3 = new DiscountType(3);
    /**
     * 4人同行
     */
    public static final DiscountType COUNT_4 = new DiscountType(4);

    /**
     * 5人同行
     */
    public static final DiscountType COUNT_5 = new DiscountType(5);

    /**
     * 下单时间优惠
     */
    public static final DiscountType TIME_ORDER = new DiscountType(6);

    /**
     * 出行时间优惠
     */
    public static final DiscountType TIME_TRAVEL = new DiscountType(7);

    /**
     * 路线优惠
     */
    public static final DiscountType ROUTE = new DiscountType(8);

    /**
     * 学生证优惠
     */
    public static final DiscountType STUDENT = new DiscountType(9);

    public static final EnumObjectHelper<DiscountType> HELPER = EnumObjectHelper.create(COUPON, COUNT_1, COUNT_2,
            COUNT_3, COUNT_4, COUNT_5, TIME_ORDER, TIME_TRAVEL, ROUTE, STUDENT);

    private DiscountType(int value) {
        super(value);
    }

    private static final Set<Integer> POLICY = new HashSet<Integer>();

    static {
        // 优惠策略
        POLICY.add(DiscountType.COUNT_1.value);
        POLICY.add(DiscountType.COUNT_2.value);
        POLICY.add(DiscountType.COUNT_3.value);
        POLICY.add(DiscountType.COUNT_4.value);
        POLICY.add(DiscountType.COUNT_5.value);
        POLICY.add(DiscountType.ROUTE.value);
        POLICY.add(DiscountType.TIME_ORDER.value);
        POLICY.add(DiscountType.TIME_TRAVEL.value);
    }

    public static Set<Integer> getDiscountPolicy() {
        return Collections.unmodifiableSet(POLICY);
    }

}
