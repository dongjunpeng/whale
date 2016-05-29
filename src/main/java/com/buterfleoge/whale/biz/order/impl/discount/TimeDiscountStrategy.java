package com.buterfleoge.whale.biz.order.impl.discount;

import java.util.Date;

import com.buterfleoge.whale.type.enums.DiscountType;
import com.buterfleoge.whale.type.protocol.order.GetDiscountRequest;
import com.buterfleoge.whale.type.protocol.order.GetDiscountResponse;

/**
 *
 * @author xiezhenzong
 *
 */
public class TimeDiscountStrategy extends DiscountStrategy {

    public void handleDiscount(Long accountid, GetDiscountRequest request, GetDiscountResponse response,
            DiscountStrategyContext context) {
        Date now = new Date();
        try {
            response.getPolicy().add(discountRepository
                    .findByTypeAndStartTimeLessThanAndEndTimeGreaterThan(DiscountType.TIME_ORDER, now, now));
        } catch (Exception e) {
            LOG.error("find time_order discount type failed, reqid: " + request.getReqid(), e);
        }

        try {
            now = context.getGroup().getStartDate();
            response.getPolicy().add(discountRepository
                    .findByTypeAndStartTimeLessThanAndEndTimeGreaterThan(DiscountType.TIME_TRAVEL, now, now));
        } catch (Exception e) {
            LOG.error("find time_travel discount type failed, reqid: " + request.getReqid(), e);
        }

    }

}
