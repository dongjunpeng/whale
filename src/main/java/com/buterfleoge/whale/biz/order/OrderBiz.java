/**
 *
 */
package com.buterfleoge.whale.biz.order;

import com.buterfleoge.whale.type.protocol.order.GetBriefOrdersRequest;
import com.buterfleoge.whale.type.protocol.order.GetBriefOrdersResponse;
import com.buterfleoge.whale.type.protocol.order.GetOrderResponse;
import com.buterfleoge.whale.type.protocol.order.OrderRequest;

/**
 * @author Brent24
 *
 */
public interface OrderBiz {

    void getOrder(Long accountid, OrderRequest request, GetOrderResponse response) throws Exception;

    void getBriefOrders(Long accountid, GetBriefOrdersRequest request, GetBriefOrdersResponse response) throws Exception;

}
