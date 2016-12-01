package com.buterfleoge.whale.type;

import com.buterfleoge.whale.EnumObject;
import com.buterfleoge.whale.EnumObject.EnumObjectHelper;

/**
 * 性别
 *
 * @author Brent24
 *
 */
public interface Gender {
    /**
     * 未知
     */
    EnumObject UNKNOW = new EnumObject(0);

    /**
     * 男
     */
    EnumObject MALE = new EnumObject(1);

    /**
     * 女
     */
    EnumObject FEMALE = new EnumObject(2);

    EnumObjectHelper<EnumObject> helper = EnumObjectHelper.create(UNKNOW, MALE, FEMALE);

}
