/**
 *
 */
package com.buterfleoge.whale.type;

import com.buterfleoge.whale.Utils;

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
public enum DiscountCodeStatus {

    /**
     * 已生成，等待验证
     */
    CREATED(0),

    /**
     * 已过期
     */
    TIMEOUT(1),

    /**
     * 用户提交，验证通过
     */
    VERIFIED(2),

    /**
     * 用户下单，占用中
     */
    OCCUPIED(3),

    /**
     * 付款完成，优惠券不再有效
     */
    USED(4);

    private int status;

    private DiscountCodeStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public static final DiscountCodeStatus valueOf(int status) {
        for (DiscountCodeStatus as : values()) {
            if (as.getStatus() == status) {
                return as;
            }
        }
        throw new IllegalArgumentException("Can't find DiscountCodeStatus, status: " + status);
    }

    @Override
    public String toString() {
        return Utils.enumToString(this);
    }
}
