package com.buterfleoge.whale.type;

import com.buterfleoge.whale.EnumObject;
import com.buterfleoge.whale.EnumObject.EnumObjectHelper;

/**
 * 优惠码类型
 *
 * @author xiezhenzong
 *
 */
public interface CouponType {

    /**
     * 新人优惠
     */
    EnumObject NEW = new EnumObject(0);

    /**
     * 周围好友
     */
    EnumObject FRIEND = new EnumObject(1);

    EnumObjectHelper<EnumObject> helper = EnumObjectHelper.create(NEW, FRIEND);
}
