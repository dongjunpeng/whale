package com.buterfleoge.whale.type.protocol.order;

import com.buterfleoge.whale.type.OrderStatusCategory;
import com.buterfleoge.whale.type.protocol.Request;

/**
 * @author Brent24
 *
 */
public class GetBriefOrdersRequest extends Request {

    private OrderStatusCategory orderType;

    /**
     * @return the orderType
     */
    public OrderStatusCategory getOrderType() {
        return orderType;
    }

    /**
     * @param orderType the orderType to set
     */
    public void setOrderType(OrderStatusCategory orderType) {
        this.orderType = orderType;
    }

}
