package com.buterfleoge.whale.type;

import com.buterfleoge.whale.EnumObject;
import com.buterfleoge.whale.EnumObject.EnumObjectHelper;

/**
 * 账户状态
 *
 * @author xiezhenzong
 *
 */
public interface AccountStatus {

    /**
     * 刚注册
     */
    EnumObject WAIT_COMPLETE_INFO = new EnumObject(0);

    /**
     * 账户正常
     */
    EnumObject OK = new EnumObject(1);

    /**
     * 账户注销
     */
    EnumObject DELETE = new EnumObject(2);

    EnumObjectHelper<EnumObject> helper = EnumObjectHelper.create(WAIT_COMPLETE_INFO, OK, DELETE);
}
