package com.buterfleoge.whale.type.protocol.order.object;

import java.util.Set;

import com.buterfleoge.whale.type.OrderStatus;
import com.buterfleoge.whale.type.entity.AccountInfo;
import com.buterfleoge.whale.type.entity.OrderDiscount;
import com.buterfleoge.whale.type.entity.OrderInfo;
import com.buterfleoge.whale.type.entity.OrderRefund;
import com.buterfleoge.whale.type.entity.TravelGroup;
import com.buterfleoge.whale.type.entity.TravelRoute;

/**
 * @author Brent24
 *
 */
public class BriefOrder implements Comparable<BriefOrder> {

    private OrderInfo orderInfo;
    private TravelGroup travelGroup;
    private TravelRoute travelRoute;
    private OrderDiscount policy;
    private OrderDiscount code;
    private OrderDiscount student;
    private OrderRefund orderRefound;
    private Long timeLeft;
    private Long dayLeft;
    private Set<String> travellerNames;
    private Set<AccountInfo> otherTravellers;

    /**
     * {@inheritDoc}
     *
     * @see OrderStatus
     */
    @Override
    public int compareTo(BriefOrder arg0) {
        int status = getOrderInfo().getStatus();
        int status0 = arg0.getOrderInfo().getStatus();
        return status != status0 ? status - status0 : (int) (arg0.getOrderInfo().getOrderid() - getOrderInfo().getOrderid());
    }

    /**
     * @return the orderInfo
     */
    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    /**
     * @param orderInfo
     *            the orderInfo to set
     */
    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    /**
     * @return the travelGroup
     */
    public TravelGroup getTravelGroup() {
        return travelGroup;
    }

    /**
     * @param travelGroup
     *            the travelGroup to set
     */
    public void setTravelGroup(TravelGroup travelGroup) {
        this.travelGroup = travelGroup;
    }

    /**
     * @return the travelRoute
     */
    public TravelRoute getTravelRoute() {
        return travelRoute;
    }

    /**
     * @param travelRoute
     *            the travelRoute to set
     */
    public void setTravelRoute(TravelRoute travelRoute) {
        this.travelRoute = travelRoute;
    }

    /**
     * @return the policy
     */
    public OrderDiscount getPolicy() {
        return policy;
    }

    /**
     * @param policy
     *            the policy to set
     */
    public void setPolicy(OrderDiscount policy) {
        this.policy = policy;
    }

    /**
     * @return the code
     */
    public OrderDiscount getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(OrderDiscount code) {
        this.code = code;
    }

    /**
     * @return the student
     */
    public OrderDiscount getStudent() {
        return student;
    }

    /**
     * @param student
     *            the student to set
     */
    public void setStudent(OrderDiscount student) {
        this.student = student;
    }

    /**
     * @return the orderRefound
     */
    public OrderRefund getOrderRefound() {
        return orderRefound;
    }

    /**
     * @param orderRefound
     *            the orderRefound to set
     */
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

    /**
     * @return the travellerNames
     */
    public Set<String> getTravellerNames() {
        return travellerNames;
    }

    /**
     * @param travellerNames
     *            the travellerNames to set
     */
    public void setTravellerNames(Set<String> travellerNames) {
        this.travellerNames = travellerNames;
    }

    /**
     * @return the otherTravellers
     */
    public Set<AccountInfo> getOtherTravellers() {
        return otherTravellers;
    }

    /**
     * @param otherTravellers
     *            the otherTravellers to set
     */
    public void setOtherTravellers(Set<AccountInfo> otherTravellers) {
        this.otherTravellers = otherTravellers;
    }

}
