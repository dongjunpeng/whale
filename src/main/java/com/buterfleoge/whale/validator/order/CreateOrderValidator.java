package com.buterfleoge.whale.validator.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.buterfleoge.whale.biz.travel.TravelBiz;
import com.buterfleoge.whale.type.protocol.order.CreateOrderRequest;

/**
 * @author xiezhenzong
 *
 */
@Service("createOrderValidator")
public class CreateOrderValidator implements Validator {

    @Autowired
    private TravelBiz travelBiz;

    @Override
    public boolean supports(Class<?> clazz) {
        return CreateOrderRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }

    private void validateTravellerCount(CreateOrderRequest request, Errors errors) {
        int count = request.getTravellers().size();

        // int quota = travelBiz.getQuota(context.getOrderInfo().getGroupid(),
        // request, response);
        // if (response.hasError()) {
        // return;
        // }
        // if (quota < count) {
        // response.setStatus(Status.BIZ_ERROR);
        // response.addError(new Error(BizCode.GROUP_QUOTA_FULL,
        // ErrorMsg.GROUP_QUOTA_FULL));
        // }
    }

}
