package com.buterfleoge.whale.biz.order.refund;

import com.buterfleoge.whale.type.RefundType;
import com.buterfleoge.whale.type.entity.OrderInfo;
import com.buterfleoge.whale.type.entity.TravelGroup;
import com.buterfleoge.whale.type.entity.TravelRoute;

/**
 * @author xiezhenzong
 *
 */
public interface RefundStrategy {

    RefundType getRefundType(OrderInfo orderInfo, TravelRoute travelRoute, TravelGroup travelGroup);

}
