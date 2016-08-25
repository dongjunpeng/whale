package com.buterfleoge.whale.type;

import com.buterfleoge.whale.EnumObject;

/**
 * 订单状态，几种流程：有可能需要更加细化一点
 *
 * <ol>
 * <li>WATING -> CANCEL
 * <li>WATING -> TIMEOUT
 * <li>WATING -> PAYING -> PAID -> REFOUND
 * <li>WATING -> PAYING -> PAID -> FINISH
 * <li>WATING -> CLOSED -> REFOUND
 * </ol>
 *
 * @author Brent24
 *
 */
public class OrderStatus  extends EnumObject{

    /**
     * 点击报名
     */
    public static final OrderStatus NEW = new OrderStatus(0);

    /**
     * 生成等待付款
     */
    public static final OrderStatus WAITING = new OrderStatus(1);

    /**
     * 取消
     */
    public static final OrderStatus CANCEL = new OrderStatus(2);

    /**
     * 超时
     */
    public static final OrderStatus TIMEOUT = new OrderStatus(3);

    /**
     * 退款
     */
    public static final OrderStatus REFOUND = new OrderStatus(4);

    /**
     * 付款中
     */
    public static final OrderStatus PAYING = new OrderStatus(5);

    /**
     * 付款到账
     */
    public static final OrderStatus PAID = new OrderStatus(6);

    /**
     * 开始旅行
     */
    public static final OrderStatus FINISH = new OrderStatus(7);

    /**
     * 行程取消
     */
    public static final OrderStatus CLOSED = new OrderStatus(8);

    public static final EnumObjectHelper<OrderStatus> HELPER = EnumObjectHelper.create(NEW, WAITING, CANCEL, TIMEOUT, REFOUND, PAYING, PAID,
            FINISH, CLOSED);

    private OrderStatus(int value) {
        super(value);
    }

}
