/**
 *
 */
package com.buterfleoge.whale.type;

import com.buterfleoge.whale.Utils;

/**
 * @author xiezhenzong
 *
 */
public enum PayType {

    ALIPAY(0);

    private int type;

    private PayType(int type) {
        this.type = type;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    public static final PayType valueOf(int type) {
        for (PayType at : values()) {
            if (at.getType() == type) {
                return at;
            }
        }
        throw new IllegalArgumentException("Can't find PayType, type: " + type);
    }

    @Override
    public String toString() {
        return Utils.enumToString(this);
    }
}
