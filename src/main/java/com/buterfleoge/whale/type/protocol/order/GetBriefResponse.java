package com.buterfleoge.whale.type.protocol.order;

import java.util.List;

import com.buterfleoge.whale.type.protocol.Response;
import com.buterfleoge.whale.type.protocol.order.object.BriefOrder;

/**
 * @author Brent24
 *
 */
public class GetBriefResponse extends Response {

    private List<BriefOrder> briefOrders;
    private Long currentOrders;
    private Long historyOrders;

    public List<BriefOrder> getBriefOrders() {
        return briefOrders;
    }

    public void setBriefOrders(List<BriefOrder> briefOrders) {
        this.briefOrders = briefOrders;
    }

    public Long getCurrentOrders() {
        return currentOrders;
    }

    public void setCurrentOrders(Long currentOrders) {
        this.currentOrders = currentOrders;
    }

    public Long getHistoryOrders() {
        return historyOrders;
    }

    public void setHistoryOrders(Long historyOrders) {
        this.historyOrders = historyOrders;
    }

}
