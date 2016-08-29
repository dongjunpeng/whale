package com.buterfleoge.whale.type;

import com.buterfleoge.whale.EnumObject;

/**
 * 订单中人员的状态
 *
 * @author xiezhenzong
 *
 */
public class OrderStaffStatus extends EnumObject {

    public static final OrderStaffStatus OK = new OrderStaffStatus(0);

    /**
     * 删除了这个人
     */
    public static final OrderStaffStatus CANCEL = new OrderStaffStatus(1);
    
    public static final EnumObjectHelper<OrderStaffStatus> HELPER = EnumObjectHelper.create(OK, CANCEL);

    private OrderStaffStatus(int value) {
        super(value);
    }

}
