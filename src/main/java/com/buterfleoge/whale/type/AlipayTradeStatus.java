package com.buterfleoge.whale.type;

import com.buterfleoge.whale.Utils;

/**
 * @author xiezhenzong
 *
 */
public enum AlipayTradeStatus {

    /**
     * 交易成功，可以退款
     */
    TRADE_SUCCESS(0),

    /**
     * 交易完成，这个时候是不可以退款的
     */
    TRADE_FINISHED(1),

    /**
     * 交易关闭，支付宝默认是不发送该状态的通知的
     */
    TRADE_CLOSED(2);

    private int value;

    private AlipayTradeStatus(int value) {
        this.value = value;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    public static AlipayTradeStatus value(int value) {
        for (AlipayTradeStatus status : AlipayTradeStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Can't find this AlipayTradeStatus, value: " + value);
    }

    @Override
    public String toString() {
        return Utils.enumToString(this);
    }

}
