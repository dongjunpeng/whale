/**
 * 
 */
package com.buterfleoge.whale.biz.order;

import com.buterfleoge.whale.type.protocol.order.GetBriefRequest;
import com.buterfleoge.whale.type.protocol.order.GetBriefResponse;
import com.buterfleoge.whale.type.protocol.order.GetDiscountRequest;
import com.buterfleoge.whale.type.protocol.order.GetDiscountResponse;
import com.buterfleoge.whale.type.protocol.order.GetOrdersRequest;
import com.buterfleoge.whale.type.protocol.order.GetOrdersResponse;
import com.buterfleoge.whale.type.protocol.order.ValidateCodeRequest;
import com.buterfleoge.whale.type.protocol.order.ValidateCodeResponse;

/**
 * @author Brent24
 *
 */
public interface OrderBiz {

    void getOrders(GetOrdersRequest request, GetOrdersResponse response) throws Exception;

    void getBriefOrders(GetBriefRequest request, GetBriefResponse response) throws Exception;

    void getDiscount(GetDiscountRequest request, GetDiscountResponse response) throws Exception;

    void validateDiscountCode(ValidateCodeRequest request, ValidateCodeResponse response) throws Exception;

}
