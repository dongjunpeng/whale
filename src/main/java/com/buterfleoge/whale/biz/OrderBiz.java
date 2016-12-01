/**
 *
 */
package com.buterfleoge.whale.biz;

import com.buterfleoge.whale.type.entity.OrderInfo;
import com.buterfleoge.whale.type.protocol.order.GetBriefOrdersRequest;
import com.buterfleoge.whale.type.protocol.order.GetBriefOrdersResponse;
import com.buterfleoge.whale.type.protocol.order.GetOrderHistoryResponse;
import com.buterfleoge.whale.type.protocol.order.GetOrderResponse;
import com.buterfleoge.whale.type.protocol.order.OrderRequest;

/**
 * @author Brent24
 *
 */
public interface OrderBiz {

    void getOrder(Long accountid, OrderRequest request, GetOrderResponse response) throws Exception;

    void getBriefOrders(Long accountid, GetBriefOrdersRequest request, GetBriefOrdersResponse response) throws Exception;

    void getOrderHistory(Long accountid, OrderRequest request, GetOrderHistoryResponse response) throws Exception;

    OrderInfo changeOrderInfoStatusIfTimeout(OrderInfo orderInfo) throws Exception;

}
