package com.buterfleoge.whale.biz.order.impl.discount;

import java.util.Date;

import com.buterfleoge.whale.type.DiscountType;
import com.buterfleoge.whale.type.protocol.order.GetDiscountRequest;
import com.buterfleoge.whale.type.protocol.order.GetDiscountResponse;

/**
 *
 * @author xiezhenzong
 *
 */
public class StudentDiscountStrategy extends DiscountStrategy {

    public void handleDiscount(Long accountid, GetDiscountRequest request, GetDiscountResponse response,
            DiscountStrategyContext context) {
        try {
            Long routeid = request.getRouteid();
            Date now = context.getGroup().getStartDate();
            response.setStudentDiscount(
                    discountRepository.findByTypeAndRouteidAndStartTimeLessThanAndEndTimeGreaterThan(
                            DiscountType.STUDENT, routeid, now, now));
        } catch (Exception e) {
            LOG.error("find student discount type failed, reqid: " + request.getReqid(), e);
        }
    }

}
