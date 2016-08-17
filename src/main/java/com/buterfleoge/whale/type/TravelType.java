package com.buterfleoge.whale.type;

import com.buterfleoge.whale.EnumObject;

/**
 * 旅行类型
 *
 * @author Brent24
 *
 */
public class TravelType extends EnumObject {

    /**
     * 长途
     */
    public static final TravelType LONG_TRIP = new TravelType(0);

    /**
     * 短途
     */
    public static final TravelType SHORT_TRIP = new TravelType(1);

    /**
     * 周末
     */
    public static final TravelType WEEKEND = new TravelType(2);

    /**
     * 轰趴
     */
    public static final TravelType PARTY = new TravelType(3);

    /**
     * 城市
     */
    public static final TravelType CITY_WALK = new TravelType(4);

    /**
     * 国际
     */
    public static final TravelType INTERNATIONAL = new TravelType(5);
    
    public static final EnumObjectHelper<TravelType> HELPER = EnumObjectHelper.create(LONG_TRIP, SHORT_TRIP, WEEKEND, PARTY, CITY_WALK, INTERNATIONAL);

    private TravelType(int value) {
        super(value);
    }

}
