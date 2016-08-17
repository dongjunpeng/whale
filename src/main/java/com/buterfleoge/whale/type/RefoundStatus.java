package com.buterfleoge.whale.type;

import com.buterfleoge.whale.Utils;

/**
 * 退款原因
 *
 * @author Brent24
 *
 */
public enum RefoundStatus {

    /**
     * 退款产生
     */
    CREATED(0),

    /**
     * 确认退款
     */
    CONFIRMED(1),

    /**
     * 完成退款
     */
    REFOUNDED(2)

    ;

    private int status;

    private RefoundStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public static final RefoundStatus valueOf(int status) {
        for (RefoundStatus as : values()) {
            if (as.getStatus() == status) {
                return as;
            }
        }
        throw new IllegalArgumentException("Can't find RefoundStatus, status: " + status);
    }

    @Override
    public String toString() {
        return Utils.enumToString(this);
    }
}
