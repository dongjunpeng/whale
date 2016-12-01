package com.buterfleoge.whale.biz.order.refund;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Service;

import com.buterfleoge.whale.type.RefundType;
import com.buterfleoge.whale.type.entity.OrderInfo;
import com.buterfleoge.whale.type.entity.TravelGroup;
import com.buterfleoge.whale.type.entity.TravelRoute;

/**
 * @author xiezhenzong
 *
 */
@Service("shortTravelTimeStrategy")
public class ShortTravelTimeStrategy implements RefundStrategy {

    @Override
    public RefundType getRefundType(OrderInfo orderInfo, TravelRoute travelRoute, TravelGroup travelGroup) {
        long timeLeft = travelGroup.getStartDate().getTime() - System.currentTimeMillis();
        if (timeLeft >= DateUtils.MILLIS_PER_DAY * 7) {
            return RefundType.SHORT_PCT_100;
        } else if (timeLeft >= DateUtils.MILLIS_PER_DAY * 3) {
            return RefundType.SHORT_PCT_80;
        } else if (timeLeft >= 0) {
            return RefundType.SHORT_PCT_50;
        } else {
            return RefundType.SHORT_PCT_20;
        }
    }

}
