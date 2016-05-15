package com.buterfleoge.whale.type.enums;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 订单状态，几种流程：有可能需要更加细化一点
 * 
 * <ol>
 * <li>WATING -> CANCEL
 * <li>WATING -> TIMEOUT
 * <li>WATING -> PAYING -> CANCELPAYMENT
 * <li>WATING -> PAYING -> PAID -> REFOUNDING -> REFOUNDED
 * <li>WATING -> PAYING -> FINISH
 * </ol>
 * 
 * @author Brent24
 *
 */
public enum OrderStatus {

    /**
     * 
     */
    NEW(0),

    /**
     * 生成等待付款
     */
    WAITING(1),

    /**
     * 取消
     */
    CANCEl(2),

    /**
     * 超时
     */
    TIMEOUT(3),

    /**
     * 取消支付
     */
    CANCELPAYMENT(4),

    /**
     * 已退款
     */
    REFOUNDED(5),

    /**
     * 付款中
     */
    PAYING(6),

    /**
     * 付款到账
     */
    PAID(7),

    /**
     * 退款中
     */
    REFOUNDING(8),

    /**
     * 开始旅行
     */
    FINISH(9),

    /**
     * 行程取消
     */
    CLOSED(10);

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
