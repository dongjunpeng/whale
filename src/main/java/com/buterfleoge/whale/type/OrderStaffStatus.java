package com.buterfleoge.whale.type;

import com.buterfleoge.whale.Utils;

/**
 * 订单中人员的状态
 *
 * @author xiezhenzong
 *
 */
public enum OrderStaffStatus {

    OK(0),

    /**
     * 删除了这个人
     */
    CANCEL(1)

    ;

    private int status;

    private OrderStaffStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public static final OrderStaffStatus valueOf(int status) {
        for (OrderStaffStatus as : values()) {
            if (as.getStatus() == status) {
                return as;
            }
        }
        throw new IllegalArgumentException("Can't find OrderStaffStatus, status: " + status);
    }

    @Override
    public String toString() {
        return Utils.enumToString(this);
    }

}
