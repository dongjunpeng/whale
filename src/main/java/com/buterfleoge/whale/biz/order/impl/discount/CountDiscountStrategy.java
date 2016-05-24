package com.buterfleoge.whale.biz.order.impl.discount;

import com.buterfleoge.whale.type.enums.DiscountType;
import com.buterfleoge.whale.type.protocol.order.GetDiscountRequest;
import com.buterfleoge.whale.type.protocol.order.GetDiscountResponse;

/**
 *
 * @author xiezhenzong
 *
 */
public class CountDiscountStrategy extends DiscountStrategy {

    public void handleDiscount(Long accountid, GetDiscountRequest request, GetDiscountResponse response,
            DiscountStrategyContext context) {
        try {
            DiscountType countDiscountType = DiscountType.getDiscountType(request.getCount());
            long now = System.currentTimeMillis();
            response.getPolicy().add(discountRepository
                    .findByTypeAndStartTimeLessThanAndEndTimeGreaterThan(countDiscountType, now, now));
        } catch (Exception e) {
            LOG.error("find count discount type failed, reqid: " + request.getReqid(), e);
        }
    }

}
