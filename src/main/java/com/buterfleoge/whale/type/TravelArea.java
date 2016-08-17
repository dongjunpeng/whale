package com.buterfleoge.whale.type;

import com.buterfleoge.whale.Utils;

/**
 * 旅行地区
 *
 * @author Brent24
 *
 */
public enum TravelArea {

    /**
     * 未知
     */
    UNKNOW(0),

    /**
     * 西北
     */
    NORTHWEST(1),

    /**
     * 东北
     */
    NORTHEAST(2),

    /**
     * 西南
     */
    SOUTHWEST(3),

    /**
     * 东南
     */
    SOUTHEST(4),

    /**
     * 沿海
     */
    COAST(5),

    /**
     * 西藏
     */
    TIBET(6)

    ;

    private int scope;

    private TravelArea(int scope) {
        this.scope = scope;
    }

    public int getScope() {
        return scope;
    }

    public static final TravelArea valueOf(int scope) {
        for (TravelArea ta : values()) {
            if (ta.getScope() == scope) {
                return ta;
            }
        }
        throw new IllegalArgumentException("Can't find TravelArea, scope: " + scope);
    }

    @Override
    public String toString() {
        return Utils.enumToString(this);
    }

}
