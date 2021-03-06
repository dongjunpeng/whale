package com.buterfleoge.whale.biz.order.discount;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.buterfleoge.whale.type.DiscountType;
import com.buterfleoge.whale.type.entity.Discount;
import com.buterfleoge.whale.type.protocol.order.GetDiscountRequest;
import com.buterfleoge.whale.type.protocol.order.GetDiscountResponse;

/**
 *
 * @author xiezhenzong
 *
 */
@Service("routeDiscountStrategy")
public class RouteDiscountStrategy extends DiscountStrategy {

    @Override
    public void handleDiscount(Long accountid, GetDiscountRequest request, GetDiscountResponse response) {
        try {
            Long routeid = request.getRouteid();
            Date now = new Date();
            Discount routeDiscount = discountRepository
                    .findByTypeAndRouteidAndStartTimeLessThanAndEndTimeGreaterThan(DiscountType.ROUTE.value, routeid, now, now);
            if (routeDiscount != null) {
                response.getPolicy().add(routeDiscount);
            }
        } catch (Exception e) {
            LOG.error("find route discount type failed, reqid: " + request.getReqid(), e);
        }
    }

}
