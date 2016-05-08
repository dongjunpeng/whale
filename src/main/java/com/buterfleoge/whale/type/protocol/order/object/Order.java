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
    private OrderDiscount policy;
    private OrderDiscount code;
    private OrderDiscount student;
    private OrderRefound orderRefound;

    public Order(OrderInfo orderInfo, TravelRoute TravelRoute, TravelGroup travelGroup,
            List<OrderTravellers> orderTravellers, OrderDiscount policy, OrderDiscount code, OrderDiscount student,
            OrderRefound orderRefound) {
        this.orderInfo = orderInfo;
        this.TravelRoute = TravelRoute;
        this.travelGroup = travelGroup;
        this.orderTravellers = orderTravellers;
        this.policy = policy;
        this.code = code;
        this.student = student;
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

    public OrderDiscount getPolicy() {
        return policy;
    }

    public void setPolicy(OrderDiscount policy) {
        this.policy = policy;
    }

    public OrderDiscount getCode() {
        return code;
    }

    public void setCode(OrderDiscount code) {
        this.code = code;
    }

    public OrderDiscount getStudent() {
        return student;
    }

    public void setStudent(OrderDiscount student) {
        this.student = student;
    }

    public OrderRefound getOrderRefound() {
        return orderRefound;
    }

    public void setOrderRefound(OrderRefound orderRefound) {
        this.orderRefound = orderRefound;
    }

}
