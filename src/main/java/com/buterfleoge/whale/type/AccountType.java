package com.buterfleoge.whale.type;

import com.buterfleoge.whale.EnumObject;
import com.buterfleoge.whale.EnumObject.EnumObjectHelper;

/**
 * 账户类型
 *
 * @author xiezhenzong
 *
 */
public interface AccountType {

    /**
     * 普通账户
     */
    EnumObject USER = new EnumObject(0);

    /**
     * 领队
     */
    EnumObject LEADER = new EnumObject(1);

    /**
     * 管理员
     */
    EnumObject MANAGER = new EnumObject(2);

    /**
     * 合作伙伴
     */
    EnumObject PARTNER = new EnumObject(3);

    /**
     * 代理商
     */
    EnumObject AGENT = new EnumObject(4);

    /**
     * 超级管理员
     */
    EnumObject ROOT = new EnumObject(5);

    EnumObjectHelper<EnumObject> helper = EnumObjectHelper.create(USER, LEADER, MANAGER, PARTNER, AGENT, ROOT);
}
