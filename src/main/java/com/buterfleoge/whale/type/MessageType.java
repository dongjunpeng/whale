package com.buterfleoge.whale.type;

import com.buterfleoge.whale.EnumObject;
import com.buterfleoge.whale.EnumObject.EnumObjectHelper;

/**
 * 消息类型
 *
 * @author xiezhenzong
 *
 */
public interface MessageType {

    /**
     * 不重要的消息
     */
    EnumObject UNIMPORTANT = new EnumObject(0);

    /**
     * 浮层消息
     */
    EnumObject TOAST = new EnumObject(1);

    /**
     * 弹窗消息
     */
    EnumObject MODAL = new EnumObject(2);

    /**
     * 手机消息
     */
    EnumObject IPHONE = new EnumObject(3);

    EnumObjectHelper<EnumObject> helper = EnumObjectHelper.create(UNIMPORTANT, TOAST, MODAL, IPHONE);
}
