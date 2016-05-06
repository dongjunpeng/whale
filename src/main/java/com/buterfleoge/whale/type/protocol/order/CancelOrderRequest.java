package com.buterfleoge.whale.type.protocol.order;

import com.buterfleoge.whale.type.protocol.Response;

/**
 * @author Brent24
 *
 */
public class CancelOrderRequest extends Response {
    private Long orderid;

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

}
