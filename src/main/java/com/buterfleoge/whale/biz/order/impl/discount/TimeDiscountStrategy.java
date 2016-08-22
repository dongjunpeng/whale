package com.buterfleoge.whale.biz.order.impl.discount;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.buterfleoge.whale.type.DiscountType;
import com.buterfleoge.whale.type.entity.Discount;
import com.buterfleoge.whale.type.entity.TravelGroup;
import com.buterfleoge.whale.type.protocol.order.GetDiscountRequest;
import com.buterfleoge.whale.type.protocol.order.GetDiscountResponse;

/**
 *
 * @author xiezhenzong
 *
 */
@Service("timeDiscountStrategy")
public class TimeDiscountStrategy extends DiscountStrategy {

    @Override
    public void handleDiscount(Long accountid, GetDiscountRequest request, GetDiscountResponse response) {
        Date now = new Date();
        try {
            Discount timeOrderDiscount = discountRepository
                    .findByTypeAndStartTimeLessThanAndEndTimeGreaterThan(DiscountType.TIME_ORDER.value, now, now);
            if (timeOrderDiscount != null) {
                response.getPolicy().add(timeOrderDiscount);
            }
        } catch (Exception e) {
            LOG.error("find time_order discount type failed, reqid: " + request.getReqid(), e);
        }

        try {
            TravelGroup group = travelGroupRepository.findOne(request.getGroupid());
            now = group.getStartDate();
            Discount timeTravelDiscount = discountRepository
                    .findByTypeAndStartTimeLessThanAndEndTimeGreaterThan(DiscountType.TIME_TRAVEL.value, now, now);
            if (timeTravelDiscount != null) {
                response.getPolicy().add(timeTravelDiscount);
            }
        } catch (Exception e) {
            LOG.error("find time_travel discount type failed, reqid: " + request.getReqid(), e);
        }
    }

}
