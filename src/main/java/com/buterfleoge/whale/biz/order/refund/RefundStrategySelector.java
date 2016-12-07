package com.buterfleoge.whale.biz.order.refund;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buterfleoge.whale.type.RefundType;
import com.buterfleoge.whale.type.TravelType;
import com.buterfleoge.whale.type.entity.OrderInfo;
import com.buterfleoge.whale.type.entity.TravelGroup;
import com.buterfleoge.whale.type.entity.TravelRoute;

/**
 * @author xiezhenzong
 *
 */
@Service("refundStrategySelector")
public class RefundStrategySelector {

    @Autowired
    private LongTravelTimeStrategy longTravelTimeStrategy;

    @Autowired
    private ShortTravelTimeStrategy shortTravelTimeStrategy;

    public RefundStrategy getRefundStrategy(OrderInfo orderInfo, TravelRoute travelRoute, TravelGroup trvelGroup) {
        int travelType = travelRoute.getType();
        if (travelType == TravelType.LONG_TRIP.value) {
            return longTravelTimeStrategy;
        } else if (travelType == TravelType.SHORT_TRIP.value) {
            return shortTravelTimeStrategy;
        } else {
            return null;
        }
    }

    public RefundType getRefundType(OrderInfo orderInfo, TravelRoute travelRoute, TravelGroup travelGroup) {
        RefundStrategy strategy = getRefundStrategy(orderInfo, travelRoute, travelGroup);
        return strategy == null ? null : strategy.getRefundType(orderInfo, travelRoute, travelGroup);
    }

}
