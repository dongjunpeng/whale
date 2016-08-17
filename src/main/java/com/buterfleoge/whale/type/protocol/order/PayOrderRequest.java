package com.buterfleoge.whale.type.protocol.order;

import com.buterfleoge.whale.type.PayType;

/**
 * @author Brent24
 *
 */
public class PayOrderRequest extends OrderRequest {

    private PayType payType = PayType.ALIPAY;

    /**
     * @return the payType
     */
    public PayType getPayType() {
        return payType;
    }

    /**
     * @param payType
     *            the payType to set
     */
    public void setPayType(PayType payType) {
        this.payType = payType;
    }

}
