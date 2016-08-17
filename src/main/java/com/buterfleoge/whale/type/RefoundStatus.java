package com.buterfleoge.whale.type;

import com.buterfleoge.whale.EnumObject;
import com.buterfleoge.whale.EnumObject.EnumObjectHelper;

/**
 * 退款原因
 *
 * @author Brent24
 *
 */
public class RefoundStatus extends EnumObject {

    /**
     * 退款产生
     */
    public static final RefoundStatus CREATED = new RefoundStatus(0);

    /**
     * 确认退款
     */
    public static final RefoundStatus CONFIRMED = new RefoundStatus(1);

    /**
     * 完成退款
     */
    public static final RefoundStatus REFOUNDED = new RefoundStatus(2);

    public static final EnumObjectHelper<RefoundStatus> HELPER = EnumObjectHelper.create(CREATED, CONFIRMED, REFOUNDED);

    private RefoundStatus(int value) {
        super(value);
    }

}
