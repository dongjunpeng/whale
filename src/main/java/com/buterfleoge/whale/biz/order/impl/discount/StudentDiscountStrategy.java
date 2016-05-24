package com.buterfleoge.whale.biz.order.impl.discount;

import com.buterfleoge.whale.type.enums.DiscountType;
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
            Long now = context.getGroup().getStartDate().getTime();
            response.setStudentDiscount(
                    discountRepository.findByTypeAndRouteidAndStartTimeLessThanAndEndTimeGreaterThan(
                            DiscountType.STUDENT, routeid, now, now));
        } catch (Exception e) {
            LOG.error("find student discount type failed, reqid: " + request.getReqid(), e);
        }
    }

}
