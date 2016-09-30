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
public class DiscountCodeStatus extends EnumObject {

    /**
     * 已生成，等待验证
     */
    public static final DiscountCodeStatus CREATED = new DiscountCodeStatus(0);

    /**
     * 已过期
     */
    public static final DiscountCodeStatus TIMEOUT = new DiscountCodeStatus(1);

    /**
     * 用户提交，验证通过
     */
    public static final DiscountCodeStatus VERIFIED = new DiscountCodeStatus(2);

    /**
     * 用户下单，占用中
     */
    @Deprecated
    public static final DiscountCodeStatus OCCUPIED = new DiscountCodeStatus(3);

    /**
     * 已经被占用
     */
    public static final DiscountCodeStatus USED = new DiscountCodeStatus(4);

    public static final EnumObjectHelper<DiscountCodeStatus> HELPER = EnumObjectHelper.create(CREATED, TIMEOUT,
            VERIFIED, OCCUPIED, USED);

    private DiscountCodeStatus(int value) {
        super(value);
    }

}
