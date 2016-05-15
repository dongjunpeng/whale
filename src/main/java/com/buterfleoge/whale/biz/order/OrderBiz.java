/**
 * 
 */
package com.buterfleoge.whale.biz.order;

import com.buterfleoge.whale.type.protocol.Response;
import com.buterfleoge.whale.type.protocol.order.AlipayRequest;
import com.buterfleoge.whale.type.protocol.order.CancelOrderRequest;
import com.buterfleoge.whale.type.protocol.order.CreateOrderRequest;
import com.buterfleoge.whale.type.protocol.order.GetBriefRequest;
import com.buterfleoge.whale.type.protocol.order.GetBriefResponse;
import com.buterfleoge.whale.type.protocol.order.GetDiscountRequest;
import com.buterfleoge.whale.type.protocol.order.GetDiscountResponse;
import com.buterfleoge.whale.type.protocol.order.GetOrderDetailRequest;
import com.buterfleoge.whale.type.protocol.order.GetOrderDetailResponse;
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

    void getOrderDetail(GetOrderDetailRequest request, GetOrderDetailResponse response) throws Exception;

    void getBriefOrders(GetBriefRequest request, GetBriefResponse response) throws Exception;

    void createOrder(CreateOrderRequest request, Response response) throws Exception;

    void getDiscount(GetDiscountRequest request, GetDiscountResponse response) throws Exception;

    void validateDiscountCode(ValidateCodeRequest request, ValidateCodeResponse response) throws Exception;

    void cancelOrder(CancelOrderRequest request, Response response) throws Exception;

    void alipay(AlipayRequest requst, Response response) throws Exception;

    void getRefoundInfo(RefoundRequest request, RefoundResponse response) throws Exception;

}
