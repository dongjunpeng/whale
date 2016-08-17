package com.buterfleoge.whale.type;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.buterfleoge.whale.EnumObject;

/**
 * 订单状态的分类
 *
 * @author xiezhenzong
 *
 */
public class OrderStatusCategory extends EnumObject {

    /**
     * 当前订单{创建等待付款,付款中,付款完成到账,退款中}
     */
    public static final OrderStatusCategory CURRENT = new OrderStatusCategory(0, OrderStatus.WAITING,
            OrderStatus.PAYING, OrderStatus.PAID);

    /**
     * 历史订单{退款完成,已出行}
     */
    public static final OrderStatusCategory HISTORY = new OrderStatusCategory(1, OrderStatus.REFOUND,
            OrderStatus.FINISH);

    /**
     * 可见订单
     */
    public static final OrderStatusCategory VISIBLE = new OrderStatusCategory(2, OrderStatus.WAITING,
            OrderStatus.PAYING, OrderStatus.PAID, OrderStatus.REFOUND, OrderStatus.FINISH);

    /**
     * 不允许新建订单的
     */
    public static final OrderStatusCategory NO_ALLOW_NEW = new OrderStatusCategory(3, OrderStatus.NEW,
            OrderStatus.WAITING, OrderStatus.PAYING);

    /**
     * 全部订单
     */
    public static final OrderStatusCategory ALL = new OrderStatusCategory(4, OrderStatus.HELPER.values());

    public static final EnumObjectHelper<OrderStatusCategory> HELPER = EnumObjectHelper.create(CURRENT, HISTORY, VISIBLE, NO_ALLOW_NEW, ALL);
    
    private Set<OrderStatus> orderStatuses;

    private OrderStatusCategory(int value, OrderStatus... orderStatuses) {
        this(value, Arrays.asList(orderStatuses));
    }

    private OrderStatusCategory(int value, List<OrderStatus> orderStatuses) {
        super(value);
        this.orderStatuses = new HashSet<OrderStatus>(orderStatuses);
    }

    /**
     * @return the orderStatuses
     */
    public Set<OrderStatus> getOrderStatuses() {
        return Collections.unmodifiableSet(orderStatuses);
    }

}
