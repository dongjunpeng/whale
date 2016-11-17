package com.buterfleoge.whale.type;

import com.buterfleoge.whale.EnumObject;

/**
 * 订单状态，几种流程：有可能需要更加细化一点
 *
 * <ol>
 * <li>NEW -> WATING
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

    public static final OrderStatus NEW = new OrderStatus(0, "点击报名");

    public static final OrderStatus WAITING = new OrderStatus(1, "新建订单");

    public static final OrderStatus PAYING = new OrderStatus(2, "等待支付");

    public static final OrderStatus PAID = new OrderStatus(3, "支付成功");

    public static final OrderStatus FINISH = new OrderStatus(4, "开始旅行");

    public static final OrderStatus REFUNDING = new OrderStatus(5, "申请退款");

    public static final OrderStatus REFUNDED = new OrderStatus(6, "退款成功");

    public static final OrderStatus CANCEL = new OrderStatus(7, "订单取消");

    public static final OrderStatus TIMEOUT = new OrderStatus(8, "订单超时");

    public static final OrderStatus CLOSED = new OrderStatus(9, "行程取消");

    public static final EnumObjectHelper<OrderStatus> HELPER = EnumObjectHelper.create(NEW, WAITING, PAYING, PAID, FINISH,
            REFUNDING, REFUNDED, CANCEL, TIMEOUT, CLOSED);

    public final String desc;

    private OrderStatus(int value, String desc) {
        super(value);
        this.desc = desc;
    }

}
