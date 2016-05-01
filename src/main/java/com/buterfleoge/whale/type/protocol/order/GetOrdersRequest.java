package com.buterfleoge.whale.type.protocol.order;

import com.buterfleoge.whale.type.protocol.Request;

/**
 * @author Brent24
 *
 */
public class GetOrdersRequest extends Request {

    private Long accoutid;
    private String orderType;

    public Long getAccoutid() {
        return accoutid;
    }

    public void setAccoutid(Long accoutid) {
        this.accoutid = accoutid;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

}
