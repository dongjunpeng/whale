/**
 *
 */
package com.buterfleoge.whale.type;

import com.buterfleoge.whale.Utils;

/**
 * 退款类型
 *
 * @author Brent24
 *
 */
public enum RefoundType {

    /**
     * 21天以上退还95%
     */
    LONG_PCT_95(0),

    /**
     * 21天-15天退还80%
     */
    LONG_PCT_80(1),

    /**
     * 14天-8天退还80%
     */
    LONG_PCT_50(2),

    /**
     * 7天至未出行
     */
    LONG_PCT_20(3),

    /**
     * 8天以上退100%
     */
    SHORT_PCT_100(4),

    /**
     * 7天-5天退80%
     */
    SHORT_PCT_80(5),

    /**
     * 4天-2天退50%
     */
    SHORT_PCT_50(6),

    /**
     * 1天至未出行退20%
     */
    SHORT_PCT_20(7)

    ;

    private int type;

    private RefoundType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static final RefoundType valueOf(int type) {
        for (RefoundType at : values()) {
            if (at.getType() == type) {
                return at;
            }
        }
        throw new IllegalArgumentException("Can't find RefoundType, type: " + type);
    }

    @Override
    public String toString() {
        return Utils.enumToString(this);
    }

}
