/**
 * 
 */
package com.buterfleoge.whale.biz.order;

import com.buterfleoge.whale.type.protocol.Response;
import com.buterfleoge.whale.type.protocol.order.AlipayRequest;
import com.buterfleoge.whale.type.protocol.order.CancelOrderRequest;
import com.buterfleoge.whale.type.protocol.order.CreateOrderRequest;
import com.buterfleoge.whale.type.protocol.order.CreateOrderResponse;
import com.buterfleoge.whale.type.protocol.order.GetBriefOrdersRequest;
import com.buterfleoge.whale.type.protocol.order.GetBriefOrdersResponse;
import com.buterfleoge.whale.type.protocol.order.GetDiscountRequest;
import com.buterfleoge.whale.type.protocol.order.GetDiscountResponse;
import com.buterfleoge.whale.type.protocol.order.GetOrderRequest;
import com.buterfleoge.whale.type.protocol.order.GetOrderResponse;
import com.buterfleoge.whale.type.protocol.order.NewOrderRequest;
import com.buterfleoge.whale.type.protocol.order.NewOrderResponse;
import com.buterfleoge.whale.type.protocol.order.RefoundRequest;
import com.buterfleoge.whale.type.protocol.order.RefoundResponse;
import com.buterfleoge.whale.type.protocol.order.ValidateCodeRequest;
import com.buterfleoge.whale.type.protocol.order.ValidateCodeResponse;

/**
 * @author Brent24
 *
 */
public interface OrderBiz {

    void newOrder(Long accountid, NewOrderRequest request, NewOrderResponse response) throws Exception;

    void createOrder(Long accountid, CreateOrderRequest request, CreateOrderResponse response) throws Exception;

    void getOrder(Long accountid, GetOrderRequest request, GetOrderResponse response) throws Exception;

    void cancelOrder(Long accountid, CancelOrderRequest request, Response response) throws Exception;

    void getBriefOrders(Long accountid, GetBriefOrdersRequest request, GetBriefOrdersResponse response) throws Exception;

    void alipay(Long accountid, AlipayRequest requst, Response response) throws Exception;

    void getRefoundInfo(Long accountid, RefoundRequest request, RefoundResponse response) throws Exception;

    void getDiscount(Long accountid, GetDiscountRequest request, GetDiscountResponse response) throws Exception;

    void validateDiscountCode(Long accountid, ValidateCodeRequest request, ValidateCodeResponse response)
            throws Exception;

}
