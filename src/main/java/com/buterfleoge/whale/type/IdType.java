package com.buterfleoge.whale.type;

import com.buterfleoge.whale.EnumObject;
import com.buterfleoge.whale.EnumObject.EnumObjectHelper;

/**
 * 证件类型
 *
 * @author Brent24
 *
 */
public interface IdType {

    /**
     * 身份证
     */
    EnumObject IDENTIFICATION = new EnumObject(0);

    /**
     * 护照
     */
    EnumObject PASSPORT = new EnumObject(1);

    /**
     * 港澳通行证
     */
    EnumObject H_PASSER = new EnumObject(2);

    /**
     * 台胞证
     */
    EnumObject T_PASSER = new EnumObject(3);

    EnumObjectHelper<EnumObject> helper = EnumObjectHelper.create(IDENTIFICATION, PASSPORT, H_PASSER, T_PASSER);
}
