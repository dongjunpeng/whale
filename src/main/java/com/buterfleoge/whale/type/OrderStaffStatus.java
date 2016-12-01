package com.buterfleoge.whale.type;

import com.buterfleoge.whale.EnumObject;
import com.buterfleoge.whale.EnumObject.EnumObjectHelper;

/**
 * 订单中人员的状态
 *
 * @author xiezhenzong
 *
 */
public interface OrderStaffStatus {

    EnumObject OK = new EnumObject(0);

    /**
     * 删除了这个人
     */
    EnumObject CANCEL = new EnumObject(1);

    EnumObjectHelper<EnumObject> helper = EnumObjectHelper.create(OK, CANCEL);
}
