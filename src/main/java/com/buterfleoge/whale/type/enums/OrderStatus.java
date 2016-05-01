package com.buterfleoge.whale.type.enums;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 订单状态，几种流程：有可能需要更加细化一点
 * 
 * <ol>
 * <li>CREATE -> CANCEL
 * <li>CREATE -> PAID -> FINISH
 * <li>CREATE -> PAID -> REFOUNDING -> REFOUNDED
 * <li>
 * </ol>
 * 
 * @author Brent24
 *
 */
public enum OrderStatus {

    /**
     * 生成等待付款
     */
    WATING(0),

    /**
     * 取消
     */
    CANCEl(11),

    /**
     * 超时
     */
    TIMEOUT(12),

    /**
     * 取消支付
     */
    CANCELPAID(13),

    /**
     * 取消支付
     */
    REFOUNDED(14),

    /**
     * 付款中
     */
    PAYING(21),

    /**
     * 付款到账
     */
    PAID(22),

    /**
     * 退款中
     */
    REFOUNDING(23),

    /**
     * 退款中
     */
    FINISH(30)

    ;

    private int status;

    private OrderStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
