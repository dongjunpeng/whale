/**
 * 
 */
package com.buterfleoge.whale.biz.order;

import com.buterfleoge.whale.type.protocol.order.GetOrdersRequest;
import com.buterfleoge.whale.type.protocol.order.GetOrdersResponse;

/**
 * @author Brent24
 *
 */
public interface OrderBiz {

    void getOrder(GetOrdersRequest request, GetOrdersResponse response) throws Exception;

}
