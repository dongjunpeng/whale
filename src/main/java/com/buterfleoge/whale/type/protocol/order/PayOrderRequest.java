package com.buterfleoge.whale.type.protocol.order;

import com.buterfleoge.whale.type.PayType;

/**
 * @author Brent24
 *
 */
public class PayOrderRequest extends OrderRequest {

    private int payType = PayType.ALIPAY.value;
    private String ip;

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

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip
     *            the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

}
