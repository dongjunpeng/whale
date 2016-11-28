/**
 *
 */
package com.buterfleoge.whale.type;

import com.buterfleoge.whale.EnumObject;

/**
 *
 * <ol>
 * <li>CREATED -> TIMEOUT
 * <li>CREATED -> VERIFIED -> TIMEOUT
 * <li>CREATED -> VERIFIED -> OCCUPIED -> USED
 * <li>CREATED -> VERIFIED -> OCCUPIED -> VERIFIED
 * </ol>
 *
 * @author Brent24
 *
 */
public class CouponStatus extends EnumObject {

    /**
     * 已生成，等待验证
     */
    public static final CouponStatus CREATED = new CouponStatus(0);

    /**
     * 已过期
     */
    public static final CouponStatus TIMEOUT = new CouponStatus(1);

    /**
     * 用户提交，验证通过
     */
    public static final CouponStatus VERIFIED = new CouponStatus(2);

    /**
     * 用户下单，占用中
     */
    @Deprecated
    public static final CouponStatus OCCUPIED = new CouponStatus(3);

    /**
     * 已经被占用
     */
    public static final CouponStatus USED = new CouponStatus(4);

    public static final EnumObjectHelper<CouponStatus> HELPER = EnumObjectHelper.create(CREATED, TIMEOUT,
            VERIFIED, OCCUPIED, USED);

    private CouponStatus(int value) {
        super(value);
    }

}
