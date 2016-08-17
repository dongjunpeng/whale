package com.buterfleoge.whale.type.protocol.order;

import com.buterfleoge.whale.type.PayType;

/**
 * @author Brent24
 *
 */
public class PayOrderRequest extends OrderRequest {

    private Integer payType = PayType.ALIPAY.value;

    /**
     * @return the payType
     */
    public Integer getPayType() {
        return payType;
    }

    /**
     * @param payType
     *            the payType to set
     */
    public void setPayType(Integer payType) {
        this.payType = payType;
    }

}
