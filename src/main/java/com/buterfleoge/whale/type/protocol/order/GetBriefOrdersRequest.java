package com.buterfleoge.whale.type.protocol.order;

import com.buterfleoge.whale.type.enums.OrderStatusType;
import com.buterfleoge.whale.type.protocol.Request;

/**
 * @author Brent24
 *
 */
public class GetBriefOrdersRequest extends Request {

    private OrderStatusType orderType;

    /**
     * @return the orderType
     */
    public OrderStatusType getOrderType() {
        return orderType;
    }

    /**
     * @param orderType the orderType to set
     */
    public void setOrderType(OrderStatusType orderType) {
        this.orderType = orderType;
    }

}
