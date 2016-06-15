package com.buterfleoge.whale.biz.order.impl.discount;

import java.util.Date;

import com.buterfleoge.whale.type.DiscountType;
import com.buterfleoge.whale.type.entity.Discount;
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
            Discount timeOrderDiscount =
                    discountRepository.findByTypeAndStartTimeLessThanAndEndTimeGreaterThan(DiscountType.TIME_ORDER, now, now);
            if (timeOrderDiscount != null) {
                response.getPolicy().add(timeOrderDiscount);
            }
        } catch (Exception e) {
            LOG.error("find time_order discount type failed, reqid: " + request.getReqid(), e);
        }

        try {
            now = context.getGroup().getStartDate();
            Discount timeTravelDiscount =
                    discountRepository.findByTypeAndStartTimeLessThanAndEndTimeGreaterThan(DiscountType.TIME_TRAVEL, now, now);
            if (timeTravelDiscount != null) {
                response.getPolicy().add(timeTravelDiscount);
            }
        } catch (Exception e) {
            LOG.error("find time_travel discount type failed, reqid: " + request.getReqid(), e);
        }

    }

}
