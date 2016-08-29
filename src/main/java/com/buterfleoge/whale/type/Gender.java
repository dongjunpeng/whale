package com.buterfleoge.whale.type;

import com.buterfleoge.whale.EnumObject;

/**
 * 性别
 *
 * @author Brent24
 *
 */
public class Gender extends EnumObject {
    /**
     * 未知
     */
    public static final Gender UNKNOW = new Gender(0);

    /**
     * 男
     */
    public static final Gender MALE = new Gender(1);

    /**
     * 女
     */
    public static final Gender FEMALE = new Gender(2);
    
    public static final EnumObjectHelper<Gender> HELPER = EnumObjectHelper.create(UNKNOW, MALE, FEMALE);

    private Gender(int value) {
        super(value);
    }
    
}
