package com.buterfleoge.whale.type.protocol.order.object;

import java.util.List;

import com.buterfleoge.whale.type.entity.OrderDiscount;
import com.buterfleoge.whale.type.entity.OrderInfo;
import com.buterfleoge.whale.type.entity.OrderRefound;
import com.buterfleoge.whale.type.entity.OrderTravellers;
import com.buterfleoge.whale.type.entity.TravelGroup;
import com.buterfleoge.whale.type.entity.TravelRoute;

/**
 * @author Brent24
 *
 */
public class Order {
    private OrderInfo orderInfo;
    private TravelRoute TravelRoute;
    private TravelGroup travelGroup;
    private List<OrderTravellers> orderTravellers;
    private OrderDiscount discountPolicy;
    private OrderDiscount discountCode;
    private OrderRefound orderRefound;

    public Order(OrderInfo orderInfo, TravelRoute TravelRoute, TravelGroup travelGroup,
            List<OrderTravellers> orderTravellers, OrderDiscount discountPolicy, OrderDiscount discountCode,
            OrderRefound orderRefound) {
        this.orderInfo = orderInfo;
        this.TravelRoute = TravelRoute;
        this.travelGroup = travelGroup;
        this.orderTravellers = orderTravellers;
        this.discountPolicy = discountPolicy;
        this.discountCode = discountCode;
        this.orderRefound = orderRefound;
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public List<OrderTravellers> getOrderTravellers() {
        return orderTravellers;
    }

    public void setOrderTravellers(List<OrderTravellers> orderTravellers) {
        this.orderTravellers = orderTravellers;
    }

    public TravelGroup getTravelGroup() {
        return travelGroup;
    }

    public void setTravelGroup(TravelGroup travelGroup) {
        this.travelGroup = travelGroup;
    }

    public TravelRoute getTravelRoute() {
        return TravelRoute;
    }

    public void setTravelRoute(TravelRoute travelRoute) {
        TravelRoute = travelRoute;
    }

    public OrderDiscount getDiscountPolicy() {
        return discountPolicy;
    }

    public void setDiscountPolicy(OrderDiscount discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    public OrderDiscount getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(OrderDiscount discountCode) {
        this.discountCode = discountCode;
    }

    public OrderRefound getOrderRefound() {
        return orderRefound;
    }

    public void setOrderRefound(OrderRefound orderRefound) {
        this.orderRefound = orderRefound;
    }

}
