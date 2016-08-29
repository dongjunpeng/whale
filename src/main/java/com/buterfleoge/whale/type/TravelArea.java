package com.buterfleoge.whale.type;

import com.buterfleoge.whale.EnumObject;

/**
 * 旅行地区
 *
 * @author Brent24
 *
 */
public class TravelArea extends EnumObject {

    /**
     * 未知
     */
    public static final TravelArea UNKNOW = new TravelArea(0);

    /**
     * 西北
     */
    public static final TravelArea NORTHWEST = new TravelArea(1);

    /**
     * 东北
     */
    public static final TravelArea NORTHEAST = new TravelArea(2);

    /**
     * 西南
     */
    public static final TravelArea SOUTHWEST = new TravelArea(3);

    /**
     * 东南
     */
    public static final TravelArea SOUTHEST = new TravelArea(4);

    /**
     * 沿海
     */
    public static final TravelArea COAST = new TravelArea(5);

    /**
     * 西藏
     */
    public static final TravelArea TIBET = new TravelArea(6);

    public static final EnumObjectHelper<TravelArea> HELPER = EnumObjectHelper.create(UNKNOW, NORTHWEST, NORTHEAST, SOUTHWEST, SOUTHEST, COAST, TIBET);

            private TravelArea(int value) {
        super(value);
    }

}
