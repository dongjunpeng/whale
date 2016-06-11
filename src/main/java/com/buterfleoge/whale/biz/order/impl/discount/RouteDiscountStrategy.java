package com.buterfleoge.whale.biz.order.impl.discount;

import java.util.Date;

import com.buterfleoge.whale.type.entity.Discount;
import com.buterfleoge.whale.type.enums.DiscountType;
import com.buterfleoge.whale.type.protocol.order.GetDiscountRequest;
import com.buterfleoge.whale.type.protocol.order.GetDiscountResponse;

/**
 *
 * @author xiezhenzong
 *
 */
public class RouteDiscountStrategy extends DiscountStrategy {

    @Override
    public void handleDiscount(Long accountid, GetDiscountRequest request, GetDiscountResponse response,
            DiscountStrategyContext context) {
        try {
            Long routeid = request.getRouteid();
            Date now = new Date();
            Discount routeDiscount = discountRepository
                    .findByTypeAndRouteidAndStartTimeLessThanAndEndTimeGreaterThan(DiscountType.ROUTE, routeid, now, now);
            if (routeDiscount != null) {
                response.getPolicy().add(routeDiscount);
            }
        } catch (Exception e) {
            LOG.error("find route discount type failed, reqid: " + request.getReqid(), e);
        }

    }

}