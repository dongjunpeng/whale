package com.buterfleoge.whale.type;

import com.buterfleoge.whale.EnumObject;

/**
 * 优惠码类型
 *
 * @author xiezhenzong
 *
 */
public class CouponType extends EnumObject {

    /**
     * 新人优惠
     */
    public static final CouponType NEW = new CouponType(0);

    private CouponType(int value) {
        super(value);
    }

}
