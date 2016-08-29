package com.buterfleoge.whale.type.protocol.order;

import java.util.List;

import com.buterfleoge.whale.type.protocol.Response;
import com.buterfleoge.whale.type.protocol.order.object.BriefOrder;

/**
 * @author Brent24
 *
 */
public class GetBriefOrdersResponse extends Response {

    private List<BriefOrder> briefOrders;
    private Integer currentOrderCount;
    private Integer historyOrderCount;
    private Integer allOrderCount;

    /**
     * @return the briefOrders
     */
    public List<BriefOrder> getBriefOrders() {
        return briefOrders;
    }

    /**
     * @param briefOrders the briefOrders to set
     */
    public void setBriefOrders(List<BriefOrder> briefOrders) {
        this.briefOrders = briefOrders;
    }

    /**
     * @return the currentOrderCount
     */
    public Integer getCurrentOrderCount() {
        return currentOrderCount;
    }

    /**
     * @param currentOrderCount the currentOrderCount to set
     */
    public void setCurrentOrderCount(Integer currentOrderCount) {
        this.currentOrderCount = currentOrderCount;
    }

    /**
     * @return the historyOrderCount
     */
    public Integer getHistoryOrderCount() {
        return historyOrderCount;
    }

    /**
     * @param historyOrderCount the historyOrderCount to set
     */
    public void setHistoryOrderCount(Integer historyOrderCount) {
        this.historyOrderCount = historyOrderCount;
    }

    /**
     * @return the allOrderCount
     */
    public Integer getAllOrderCount() {
        return allOrderCount;
    }

    /**
     * @param allOrderCount
     *            the allOrderCount to set
     */
    public void setAllOrderCount(Integer allOrderCount) {
        this.allOrderCount = allOrderCount;
    }

}
