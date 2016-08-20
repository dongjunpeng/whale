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
public class CountDiscountStrategy extends DiscountStrategy {

    @Override
    public void handleDiscount(Long accountid, GetDiscountRequest request, GetDiscountResponse response,
            DiscountStrategyContext context) {
        try {
            // FIXME
            DiscountType countDiscountType = DiscountType.HELPER.valueOf(request.getCount());
            Date now = new Date();
            Discount countDiscount =
                    discountRepository.findByTypeAndStartTimeLessThanAndEndTimeGreaterThan(countDiscountType.value, now,
                            now);
            if (countDiscount != null) {
                response.getPolicy().add(countDiscount);
            }
        } catch (Exception e) {
            LOG.error("find count discount type failed, reqid: " + request.getReqid(), e);
        }
    }

}
