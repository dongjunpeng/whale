package com.buterfleoge.whale.biz.order.discount;

import org.springframework.stereotype.Service;

import com.buterfleoge.whale.type.DiscountType;
import com.buterfleoge.whale.type.entity.TravelRoute;
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
            TravelRoute travelRoute = travelRouteRepository.findByRouteidAndVisibleTrue(routeid);
            if (travelRoute != null) {
                response.setStudentDiscount(
                        discountRepository.findByTypeAndRouteid(DiscountType.STUDENT.value, routeid));
            }
        } catch (Exception e) {
            LOG.error("find student discount type failed, reqid: " + request.getReqid(), e);
        }
    }

}
