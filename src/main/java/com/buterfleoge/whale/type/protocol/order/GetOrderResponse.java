package com.buterfleoge.whale.type.protocol.order;

import java.util.List;

import com.buterfleoge.whale.type.entity.OrderDiscount;
import com.buterfleoge.whale.type.entity.OrderInfo;
import com.buterfleoge.whale.type.entity.OrderRefund;
import com.buterfleoge.whale.type.entity.OrderTravellers;
import com.buterfleoge.whale.type.entity.TravelGroup;
import com.buterfleoge.whale.type.entity.TravelRoute;
import com.buterfleoge.whale.type.protocol.Response;

/**
 * @author Brent24
 *
 */
public class GetOrderResponse extends Response {

    private OrderInfo orderInfo;
    private TravelGroup travelGroup;
    private TravelRoute travelRoute;
    private List<OrderTravellers> orderTravellers;
    private OrderDiscount policy;
    private OrderDiscount code;
    private OrderDiscount student;
    private OrderRefund orderRefound;
    private int quota;

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public TravelGroup getTravelGroup() {
        return travelGroup;
    }

    public void setTravelGroup(TravelGroup travelGroup) {
        this.travelGroup = travelGroup;
    }

    public TravelRoute getTravelRoute() {
        return travelRoute;
    }

    public void setTravelRoute(TravelRoute travelRoute) {
        this.travelRoute = travelRoute;
    }

    public List<OrderTravellers> getOrderTravellers() {
        return orderTravellers;
    }

    public void setOrderTravellers(List<OrderTravellers> orderTravellers) {
        this.orderTravellers = orderTravellers;
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

    public OrderRefund getOrderRefound() {
        return orderRefound;
    }

    public void setOrderRefound(OrderRefund orderRefound) {
        this.orderRefound = orderRefound;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

}
