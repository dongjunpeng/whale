package com.buterfleoge.whale.biz.order.impl.create;

import com.buterfleoge.whale.type.entity.OrderTravellers;
import com.buterfleoge.whale.type.protocol.order.CreateOrderRequest;
import com.buterfleoge.whale.type.protocol.order.CreateOrderResponse;

/**
 *
 * @author xiezhenzong
 *
 */
public class FillOrderidProcessor extends CreateOrderProcessor {

    @Override
    public void doCreate(Long accountid, CreateOrderRequest request, CreateOrderResponse response, CreateOrderContext context)
            throws Exception {
        for (OrderTravellers traveller : request.getTravellers()) {
            traveller.setOrderid(request.getOrderid());
        }
    }

}
