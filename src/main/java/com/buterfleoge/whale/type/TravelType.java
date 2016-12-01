package com.buterfleoge.whale.type;

import com.buterfleoge.whale.EnumObject;
import com.buterfleoge.whale.EnumObject.EnumObjectHelper;

/**
 * 旅行类型
 *
 * @author Brent24
 *
 */
public interface TravelType {

    /**
     * 长途
     */
    EnumObject LONG_TRIP = new EnumObject(0);

    /**
     * 短途
     */
    EnumObject SHORT_TRIP = new EnumObject(1);

    /**
     * 周末
     */
    EnumObject WEEKEND = new EnumObject(2);

    /**
     * 轰趴
     */
    EnumObject PARTY = new EnumObject(3);

    /**
     * 城市
     */
    EnumObject CITY_WALK = new EnumObject(4);

    /**
     * 国际
     */
    EnumObject INTERNATIONAL = new EnumObject(5);

    EnumObjectHelper<EnumObject> helper = EnumObjectHelper.create(LONG_TRIP, SHORT_TRIP, WEEKEND, PARTY, CITY_WALK, INTERNATIONAL);
}
