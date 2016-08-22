package com.buterfleoge.whale.type;

import com.buterfleoge.whale.EnumObject;

/**
 * 退款原因
 *
 * @author Brent24
 *
 */
public class RefundStatus extends EnumObject {

    /**
     * 退款产生
     */
    public static final RefundStatus CREATED = new RefundStatus(0);

    /**
     * 确认退款
     */
    public static final RefundStatus CONFIRMED = new RefundStatus(1);

    /**
     * 完成退款
     */
    public static final RefundStatus REFOUNDED = new RefundStatus(2);

    public static final EnumObjectHelper<RefundStatus> HELPER = EnumObjectHelper.create(CREATED, CONFIRMED, REFOUNDED);

    private RefundStatus(int value) {
        super(value);
    }

}
