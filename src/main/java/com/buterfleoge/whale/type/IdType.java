package com.buterfleoge.whale.type;

import com.buterfleoge.whale.EnumObject;

/**
 * 证件类型
 *
 * @author Brent24
 *
 */
public class IdType extends EnumObject {

    /**
     * 身份证
     */
    public static final IdType IDENTIFICATION = new IdType(0);

    /**
     * 护照
     */
    public static final IdType PASSPORT = new IdType(1);

    /**
     * 港澳通行证
     */
    public static final IdType H_PASSER = new IdType(2);

    /**
     * 台胞证
     */
    public static final IdType T_PASSER = new IdType(3);

    public static final EnumObjectHelper<IdType> HELPER = EnumObjectHelper.create(IDENTIFICATION, PASSPORT, H_PASSER, T_PASSER);
    
    private IdType(int value) {
        super(value);
    }

}
