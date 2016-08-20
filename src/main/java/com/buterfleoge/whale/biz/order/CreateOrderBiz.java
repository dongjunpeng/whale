/**
 *
 */
package com.buterfleoge.whale.biz.order;

import com.buterfleoge.whale.type.protocol.order.CreateOrderRequest;
import com.buterfleoge.whale.type.protocol.order.CreateOrderResponse;
import com.buterfleoge.whale.type.protocol.order.NewOrderRequest;
import com.buterfleoge.whale.type.protocol.order.NewOrderResponse;

/**
 * @author xiezhenzong
 *
 */
public interface CreateOrderBiz {

    void newOrder(Long accountid, NewOrderRequest request, NewOrderResponse response) throws Exception;

    void createOrder(Long accountid, CreateOrderRequest request, CreateOrderResponse response) throws Exception;

}
