/**
 * 
 */
package com.buterfleoge.whale.biz.order;

import com.buterfleoge.whale.type.protocol.order.GetCurrentOrderRequest;
import com.buterfleoge.whale.type.protocol.order.GetCurrentOrderResponse;

/**
 * @author Brent24
 *
 */
public interface OrderBiz {

    void getCurrentOrder(GetCurrentOrderRequest request, GetCurrentOrderResponse response) throws Exception;

}
