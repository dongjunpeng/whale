package com.buterfleoge.whale.type.protocol.order;

import java.util.ArrayList;
import java.util.List;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.type.protocol.Response;

/**
 * @author xiezhenzong
 *
 */
public class GetOrderHistoryResponse extends Response {

    public static class OrderHistoryItem extends BaseObject {

        /**
         * 已经过去的状态
         */
        public static final Integer STATE_HISTORY = Integer.valueOf(0);

        /**
         * 还未到达的状态
         */
        public static final Integer STATE_NEXT = Integer.valueOf(1);

        /**
         * 状态的描述
         */
        private String desc;

        /**
         * 订单流程中的状态, 前端依赖这个字段设置样式
         */
        private Integer state;

        public static final OrderHistoryItem newInstance(String desc, Integer state) {
            OrderHistoryItem item = new OrderHistoryItem();
            item.desc = desc;
            item.state = state;
            return item;
        }

        /**
         * @return the desc
         */
        public String getDesc() {
            return desc;
        }

        /**
         * @param desc
         *            the desc to set
         */
        public void setDesc(String desc) {
            this.desc = desc;
        }

        /**
         * @return the state
         */
        public Integer getState() {
            return state;
        }

        /**
         * @param state
         *            the state to set
         */
        public void setState(Integer state) {
            this.state = state;
        }

    }

    private List<OrderHistoryItem> history = new ArrayList<OrderHistoryItem>(5);

    private GetOrderHistoryResponse addHistoryItem(String desc, Integer state) {
        OrderHistoryItem item = OrderHistoryItem.newInstance(desc, state);
        history.add(item);
        return this;
    }

    public GetOrderHistoryResponse addHistoryItem(String desc) {
        return addHistoryItem(desc, OrderHistoryItem.STATE_HISTORY);
    }

    public GetOrderHistoryResponse addNextItem(String desc) {
        return addHistoryItem(desc, OrderHistoryItem.STATE_NEXT);
    }

    /**
     * @return the history
     */
    public List<OrderHistoryItem> getHistory() {
        return history;
    }

    /**
     * @param history
     *            the history to set
     */
    public void setHistory(List<OrderHistoryItem> history) {
        this.history = history;
    }

}
