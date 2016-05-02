package com.buterfleoge.whale.type.protocol.order;

import java.util.List;

import com.buterfleoge.whale.type.protocol.Response;
import com.buterfleoge.whale.type.protocol.order.object.Order;

/**
 * @author Brent24
 *
 */
public class GetOrdersResponse extends Response {
    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

}
