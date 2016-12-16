package com.buterfleoge.whale.type;

import com.buterfleoge.whale.EnumObject;
import com.buterfleoge.whale.EnumObject.EnumObjectHelper;

/**
 * 设备
 *
 * @author xiezhenzong
 *
 */
public interface Device {

    EnumObject ALL = new EnumObject(0);

    EnumObject PC = new EnumObject(1);

    EnumObject PHONE = new EnumObject(2);

    EnumObject PAD = new EnumObject(3);

    EnumObject IPHONE = new EnumObject(4);

    EnumObject ANDROID = new EnumObject(5);

    EnumObject UNKNOWN = new EnumObject(6);

    EnumObjectHelper<EnumObject> helper = EnumObjectHelper.create(PC, PHONE, PAD, IPHONE, ANDROID, UNKNOWN);
}
