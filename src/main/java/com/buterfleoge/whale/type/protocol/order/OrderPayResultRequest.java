package com.buterfleoge.whale.type.protocol.order;

import com.buterfleoge.whale.type.PayType;

/**
 * @author xiezhenzong
 *
 */
public class OrderPayResultRequest extends OrderRequest {

    private int payType = PayType.ALIPAY.value;

    /**
     * @return the payType
     */
    public int getPayType() {
        return payType;
    }

    /**
     * @param payType
     *            the payType to set
     */
    public void setPayType(int payType) {
        this.payType = payType;
    }

}
