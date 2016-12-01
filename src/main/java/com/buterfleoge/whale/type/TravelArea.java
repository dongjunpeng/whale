package com.buterfleoge.whale.type;

import com.buterfleoge.whale.EnumObject;
import com.buterfleoge.whale.EnumObject.EnumObjectHelper;

/**
 * 旅行地区
 *
 * @author Brent24
 *
 */
public interface TravelArea {

    /**
     * 未知
     */
    EnumObject UNKNOW = new EnumObject(0);

    /**
     * 西北
     */
    EnumObject NORTHWEST = new EnumObject(1);

    /**
     * 东北
     */
    EnumObject NORTHEAST = new EnumObject(2);

    /**
     * 西南
     */
    EnumObject SOUTHWEST = new EnumObject(3);

    /**
     * 东南
     */
    EnumObject SOUTHEST = new EnumObject(4);

    /**
     * 沿海
     */
    EnumObject COAST = new EnumObject(5);

    /**
     * 西藏
     */
    EnumObject TIBET = new EnumObject(6);

    EnumObjectHelper<EnumObject> helper = EnumObjectHelper.create(UNKNOW, NORTHWEST, NORTHEAST, SOUTHWEST, SOUTHEST, COAST, TIBET);
}
