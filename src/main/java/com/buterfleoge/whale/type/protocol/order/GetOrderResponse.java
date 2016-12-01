package com.buterfleoge.whale.type.protocol.order;

import java.util.List;

import com.buterfleoge.whale.type.entity.OrderDiscount;
import com.buterfleoge.whale.type.entity.OrderInfo;
import com.buterfleoge.whale.type.entity.OrderRefund;
import com.buterfleoge.whale.type.entity.OrderTraveller;
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
    private List<OrderTraveller> orderTravellers;
    private OrderDiscount policy;
    private OrderDiscount coupon;
    private OrderDiscount student;
    private OrderRefund orderRefound;
    private Long timeLeft;
    private Long dayLeft;

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

    public List<OrderTraveller> getOrderTravellers() {
        return orderTravellers;
    }

    public void setOrderTravellers(List<OrderTraveller> orderTravellers) {
        this.orderTravellers = orderTravellers;
    }

    public OrderDiscount getPolicy() {
        return policy;
    }

    public void setPolicy(OrderDiscount policy) {
        this.policy = policy;
    }

    /**
     * @return the coupon
     */
    public OrderDiscount getCoupon() {
        return coupon;
    }

    /**
     * @param coupon
     *            the coupon to set
     */
    public void setCoupon(OrderDiscount coupon) {
        this.coupon = coupon;
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

    /**
     * @return the timeLeft
     */
    public Long getTimeLeft() {
        return timeLeft;
    }

    /**
     * @param timeLeft
     *            the timeLeft to set
     */
    public void setTimeLeft(Long timeLeft) {
        this.timeLeft = timeLeft;
    }

    /**
     * @return the dayLeft
     */
    public Long getDayLeft() {
        return dayLeft;
    }

    /**
     * @param dayLeft
     *            the dayLeft to set
     */
    public void setDayLeft(Long dayLeft) {
        this.dayLeft = dayLeft;
    }

}
