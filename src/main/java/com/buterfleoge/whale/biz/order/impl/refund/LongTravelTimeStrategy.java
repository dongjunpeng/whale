package com.buterfleoge.whale.biz.order.impl.refund;

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
@Service("longTravelTimeStrategy")
public class LongTravelTimeStrategy implements RefundStrategy {

    @Override
    public RefundType getRefundType(OrderInfo orderInfo, TravelRoute travelRoute, TravelGroup travelGroup) {
        long timeLeft = travelGroup.getEndDate().getTime() - System.currentTimeMillis();
        if (timeLeft >= DateUtils.MILLIS_PER_DAY * 21) {
            return RefundType.LONG_PCT_95;
        } else if (timeLeft >= DateUtils.MILLIS_PER_DAY * 14) {
            return RefundType.LONG_PCT_80;
        } else if (timeLeft >= DateUtils.MILLIS_PER_DAY * 7) {
            return RefundType.LONG_PCT_50;
        } else {
            return RefundType.LONG_PCT_20;
        }
    }

}
