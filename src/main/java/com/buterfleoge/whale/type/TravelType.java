package com.buterfleoge.whale.type;

import com.buterfleoge.whale.Utils;

/**
 * 旅行类型
 *
 * @author Brent24
 *
 */
public enum TravelType {

    /**
     * 长途
     */
    LONG_TRIP(0),

    /**
     * 短途
     */
    SHORT_TRIP(1),

    /**
     * 周末
     */
    WEEKEND(2),

    /**
     * 轰趴
     */
    PARTY(3),

    /**
     * 城市
     */
    CITY_WALK(4),

    /**
     * 国际
     */
    INTERNATIONAL(5)

    ;

    private int type;

    private TravelType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static final TravelType valueOf(int type) {
        for (TravelType at : values()) {
            if (at.getType() == type) {
                return at;
            }
        }
        throw new IllegalArgumentException("Can't find TravelType, type: " + type);
    }

    @Override
    public String toString() {
        return Utils.enumToString(this);
    }

}
