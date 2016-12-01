package com.buterfleoge.whale.type;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
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
     * 当前订单{创建等待付款,付款中,付款完成到账}
     */
    public static final OrderStatusCategory CURRENT = new OrderStatusCategory(0, OrderStatus.WAITING.value,
            OrderStatus.PAYING.value, OrderStatus.PAID.value, OrderStatus.REFUNDING.value);

    /**
     * 历史订单{退款完成,已出行}
     */
    public static final OrderStatusCategory HISTORY = new OrderStatusCategory(1, OrderStatus.REFUNDED.value,
            OrderStatus.FINISH.value);

    /**
     * 可见订单
     */
    public static final OrderStatusCategory VISIBLE = new OrderStatusCategory(2, OrderStatus.WAITING.value,
            OrderStatus.PAYING.value, OrderStatus.PAID.value, OrderStatus.FINISH.value, OrderStatus.REFUNDING.value,
            OrderStatus.REFUNDED.value, OrderStatus.CANCEL.value, OrderStatus.TIMEOUT.value, OrderStatus.CLOSED.value);

    /**
     * 不允许新建订单的
     */
    public static final OrderStatusCategory NO_ALLOW_NEW = new OrderStatusCategory(3, OrderStatus.NEW.value,
            OrderStatus.WAITING.value, OrderStatus.PAYING.value);

    /**
     * 全部订单
     */
    public static final OrderStatusCategory ALL = new OrderStatusCategory(4, OrderStatus.helper.intValues());

    public static final EnumObjectHelper<OrderStatusCategory> helper = EnumObjectHelper.create(CURRENT, HISTORY, VISIBLE, NO_ALLOW_NEW,
            ALL);

    private Set<Integer> orderStatuses;

    private OrderStatusCategory(int value, Integer... orderStatuses) {
        super(value);
        this.orderStatuses = new HashSet<Integer>(Arrays.asList(orderStatuses));
    }

    private OrderStatusCategory(int value, Set<Integer> orderStatuses) {
        super(value);
        this.orderStatuses = new HashSet<Integer>(orderStatuses);
    }

    /**
     * @return the orderStatuses
     */
    public Set<Integer> getOrderStatuses() {
        return Collections.unmodifiableSet(orderStatuses);
    }

}
