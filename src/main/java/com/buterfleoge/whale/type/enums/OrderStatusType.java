package com.buterfleoge.whale.type.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author xiezhenzong
 *
 */
public enum OrderStatusType {

    /**
     * 当前订单{创建等待付款,付款中,付款完成到账,退款中}
     */
    CURRENT(0, OrderStatus.WAITING, OrderStatus.PAYING, OrderStatus.PAID, OrderStatus.REFOUNDING),

    /**
     * 历史订单{退款完成,已出行}
     */
    HISTORY(1,OrderStatus.REFOUNDED, OrderStatus.FINISH),

    /**
     * 可见订单
     */
    VISIBLE(2, OrderStatus.WAITING, OrderStatus.PAYING, OrderStatus.PAID, OrderStatus.REFOUNDING, OrderStatus.REFOUNDED,
            OrderStatus.FINISH),

    /**
     * 全部订单
     */
    ALL(3, OrderStatus.values());

    private int type;
    private OrderStatus[] orderStatuses;

    private OrderStatusType(int type, OrderStatus...orderStatuses) {
        this.type = type;
        this.orderStatuses = orderStatuses;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @return the orderStatuses
     */
    public Set<OrderStatus> getOrderStatuses() {
        return Collections.unmodifiableSet(new HashSet<OrderStatus>(Arrays.asList(orderStatuses)));
    }

}
