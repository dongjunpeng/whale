package com.buterfleoge.whale.type;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.buterfleoge.whale.Utils;

/**
 *
 * @author xiezhenzong
 *
 */
public enum OrderStatusCategory {

    /**
     * 当前订单{创建等待付款,付款中,付款完成到账,退款中}
     */
    CURRENT(0, OrderStatus.WAITING, OrderStatus.PAYING, OrderStatus.PAID, OrderStatus.REFOUNDING),

    /**
     * 历史订单{退款完成,已出行}
     */
    HISTORY(1, OrderStatus.REFOUNDED, OrderStatus.FINISH),

    /**
     * 可见订单
     */
    VISIBLE(2, OrderStatus.WAITING, OrderStatus.PAYING, OrderStatus.PAID, OrderStatus.REFOUNDING, OrderStatus.REFOUNDED,
            OrderStatus.FINISH),

    /**
     * 不允许新建订单的
     */
    NO_ALLOW_NEW(3, OrderStatus.NEW, OrderStatus.WAITING, OrderStatus.PAYING, OrderStatus.REFOUNDING),

    /**
     * 全部订单
     */
    ALL(4, OrderStatus.values());

    private int type;
    private OrderStatus[] orderStatuses;

    private OrderStatusCategory(int type, OrderStatus... orderStatuses) {
        this.type = type;
        this.orderStatuses = orderStatuses;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    public static final OrderStatusCategory valueOf(int category) {
        for (OrderStatusCategory c : values()) {
            if (c.getType() == category) {
                return c;
            }
        }
        throw new IllegalArgumentException("Can't find OrderStatusCategory, status: " + category);
    }

    /**
     * @return the orderStatuses
     */
    public Set<OrderStatus> getOrderStatuses() {
        return Collections.unmodifiableSet(new HashSet<OrderStatus>(Arrays.asList(orderStatuses)));
    }

    @Override
    public String toString() {
        return Utils.enumToString(this);
    }

}
