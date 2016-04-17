package com.buterfleoge.whale.type.protocol.account;

import java.util.List;
import java.util.Map;

import com.buterfleoge.whale.type.entity.OrderInfo;
import com.buterfleoge.whale.type.entity.OrderTravellers;
import com.buterfleoge.whale.type.protocol.Response;

/**
 *
 * @author xiezhenzong
 *
 */
public class GetOrdersResponse extends Response {

    private List<OrderInfo> orders;

    private Map<Long, OrderTravellers> staffs;

    /**
     * @return the orders
     */
    public List<OrderInfo> getOrders() {
        return orders;
    }

    /**
     * @param orders the orders to set
     */
    public void setOrders(List<OrderInfo> orders) {
        this.orders = orders;
    }

    /**
     * @return the staffs
     */
    public Map<Long, OrderTravellers> getStaffs() {
        return staffs;
    }

    /**
     * @param staffs the staffs to set
     */
    public void setStaffs(Map<Long, OrderTravellers> staffs) {
        this.staffs = staffs;
    }

}
