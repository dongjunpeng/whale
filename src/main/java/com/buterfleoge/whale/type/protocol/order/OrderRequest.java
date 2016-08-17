/**
 *
 */
package com.buterfleoge.whale.type.protocol.order;

import com.buterfleoge.whale.type.protocol.Request;

/**
 * @author xiezhenzong
 *
 */
public class OrderRequest extends Request {

    private Long orderid;

    /**
     * @return the orderid
     */
    public Long getOrderid() {
        return orderid;
    }

    /**
     * @param orderid
     *            the orderid to set
     */
    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

}
