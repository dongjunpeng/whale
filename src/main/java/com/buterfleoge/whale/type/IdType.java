package com.buterfleoge.whale.type;

import com.buterfleoge.whale.Utils;

/**
 * 证件类型
 *
 * @author Brent24
 *
 */
public enum IdType {

    /**
     * 身份证
     */
    IDENTIFICATION(0),

    /**
     * 护照
     */
    PASSPORT(1),

    /**
     * 港澳通行证
     */
    H_PASSER(2),

    /**
     * 台胞证
     */
    T_PASSER(3)

    ;

    private int type;

    private IdType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static final IdType valueOf(int type) {
        for (IdType at : values()) {
            if (at.getType() == type) {
                return at;
            }
        }
        throw new IllegalArgumentException("Can't find IdType, type: " + type);
    }

    @Override
    public String toString() {
        return Utils.enumToString(this);
    }
}
