package com.buterfleoge.whale.type;

import com.buterfleoge.whale.Utils;

/**
 * @author xiezhenzong
 *
 */
public enum AlipayRefundStatus {

    /**
     * 退款成功
     *
     * 全额退款情况：trade_status= TRADE_CLOSED，而refund_status=REFUND_SUCCESS
     * 非全额退款情况：trade_status= TRADE_SUCCESS，而refund_status=REFUND_SUCCESS
     */
    REFUND_SUCCESS(0),

    /**
     * 退款关闭
     */
    REFUND_CLOSED(1);

    private int value;

    private AlipayRefundStatus(int value) {
        this.value = value;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    public static AlipayRefundStatus value(int value) {
        for (AlipayRefundStatus status : AlipayRefundStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Can't find this AlipayRefundStatus, value: " + value);
    }

    @Override
    public String toString() {
        return Utils.enumToString(this);
    }

}
