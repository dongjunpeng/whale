package com.buterfleoge.whale.biz.order.impl.discount;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.buterfleoge.whale.type.DiscountType;
import com.buterfleoge.whale.type.entity.TravelGroup;
import com.buterfleoge.whale.type.protocol.order.GetDiscountRequest;
import com.buterfleoge.whale.type.protocol.order.GetDiscountResponse;

/**
 *
 * @author xiezhenzong
 *
 */
@Service("studentDiscountStrategy")
public class StudentDiscountStrategy extends DiscountStrategy {

    @Override
    public void handleDiscount(Long accountid, GetDiscountRequest request, GetDiscountResponse response) {
        try {
            Long routeid = request.getRouteid();
            TravelGroup group = travelGroupRepository.findOne(routeid);
            Date now = group.getStartDate();
            response.setStudentDiscount(
                    discountRepository.findByTypeAndRouteidAndStartTimeLessThanAndEndTimeGreaterThan(
                            DiscountType.STUDENT.value, routeid, now, now));
        } catch (Exception e) {
            LOG.error("find student discount type failed, reqid: " + request.getReqid(), e);
        }
    }

}
