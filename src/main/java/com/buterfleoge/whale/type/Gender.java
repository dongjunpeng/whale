package com.buterfleoge.whale.type;

import com.buterfleoge.whale.Utils;

/**
 * 性别
 *
 * @author Brent24
 *
 */
public enum Gender {
    /**
     * 未知
     */
    UNKNOW(0),

    /**
     * 男
     */
    MALE(1),

    /**
     * 女
     */
    FEMALE(2)

    ;

    private int gender;

    private Gender(int gender) {
        this.gender = gender;
    }

    public int getGender() {
        return gender;
    }

    public static final Gender valueOf(int gender) {
        for (Gender g : values()) {
            if (g.getGender() == gender) {
                return g;
            }
        }
        throw new IllegalArgumentException("Can't find Gender, type: " + gender);
    }

    @Override
    public String toString() {
        return Utils.enumToString(this);
    }
}
