package com.buterfleoge.whale.type;

import com.buterfleoge.whale.EnumObject;
import com.buterfleoge.whale.EnumObject.EnumObjectHelper;

/**
 * 发团状态
 *
 * @author Brent24
 *
 */
public interface GroupStatus {

    /**
     * 未发布
     */
    EnumObject UNPUBLISHED = new EnumObject(0);

    /**
     * 招募中
     */
    EnumObject OPEN = new EnumObject(1);

    /**
     * 暂停
     */
    EnumObject STOP = new EnumObject(2);

    /**
     * 满员
     */
    EnumObject FULL = new EnumObject(3);

    /**
     * 出团中
     */
    EnumObject TRAVELLING = new EnumObject(4);

    /**
     * 结束
     */
    EnumObject FINISHED = new EnumObject(5);

    EnumObjectHelper<EnumObject> helper = EnumObjectHelper.create(UNPUBLISHED, OPEN, STOP, FULL, TRAVELLING, FINISHED);
}
