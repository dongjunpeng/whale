/**
 *
 */
package com.buterfleoge.whale.type.protocol.order;

import com.buterfleoge.whale.type.protocol.Request;
import com.buterfleoge.whale.validator.IdExist;
import com.buterfleoge.whale.validator.IdExist.IdType;

/**
 * @author xiezhenzong
 *
 */
public class OrderRequest extends Request {

    @IdExist(type = IdType.ORDER_ID, nullable = false, message = "")
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
