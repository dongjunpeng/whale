package com.buterfleoge.whale.type;

import com.buterfleoge.whale.EnumObject;
import com.buterfleoge.whale.EnumObject.EnumObjectHelper;

/**
 * 消息状态
 *
 * @author xiezhenzong
 *
 */
public interface MessageStatus {

    /**
     * 新添加
     */
    EnumObject NEW = new EnumObject(0);

    /**
     * 已读
     */
    EnumObject READ = new EnumObject(1);

    /**
     * 删除
     */
    EnumObject DELETE = new EnumObject(2);

    EnumObjectHelper<EnumObject> helper = EnumObjectHelper.create(NEW, READ, DELETE);

}
