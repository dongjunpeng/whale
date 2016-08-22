package com.buterfleoge.whale.biz.order.impl.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.buterfleoge.whale.type.entity.Discount;
import com.buterfleoge.whale.type.entity.DiscountCode;
import com.buterfleoge.whale.type.entity.OrderInfo;
import com.buterfleoge.whale.type.entity.TravelGroup;
import com.buterfleoge.whale.type.protocol.order.CreateOrderRequest;
import com.buterfleoge.whale.type.protocol.order.CreateOrderResponse;

/**
 *
 * @author xiezhenzong
 *
 */
@Deprecated
public abstract class CreateOrderProcessor {

    protected static final Logger LOG = LoggerFactory.getLogger(CreateOrderProcessor.class);

    public abstract void doCreate(Long accountid, CreateOrderRequest request, CreateOrderResponse response,
            CreateOrderContext context) throws Exception;

    public static class CreateOrderContext {

        private OrderInfo orderInfo;
        private TravelGroup group;
        private Discount policyDiscount;
        private Discount studentDiscount;
        private DiscountCode discountCode;

        /**
         * @param orderInfo
         * @param group
         */
        public CreateOrderContext(OrderInfo orderInfo, TravelGroup group) {
            this.orderInfo = orderInfo;
            this.group = group;
        }

        /**
         * @return the orderInfo
         */
        public OrderInfo getOrderInfo() {
            return orderInfo;
        }

        /**
         * @param orderInfo the orderInfo to set
         */
        public void setOrderInfo(OrderInfo orderInfo) {
            this.orderInfo = orderInfo;
        }

        /**
         * @return the group
         */
        public TravelGroup getGroup() {
            return group;
        }

        /**
         * @param group the group to set
         */
        public void setGroup(TravelGroup group) {
            this.group = group;
        }

        /**
         * @return the policyDiscount
         */
        public Discount getPolicyDiscount() {
            return policyDiscount;
        }

        /**
         * @param policyDiscount the policyDiscount to set
         */
        public void setPolicyDiscount(Discount policyDiscount) {
            this.policyDiscount = policyDiscount;
        }

        /**
         * @return the studentDiscount
         */
        public Discount getStudentDiscount() {
            return studentDiscount;
        }

        /**
         * @param studentDiscount the studentDiscount to set
         */
        public void setStudentDiscount(Discount studentDiscount) {
            this.studentDiscount = studentDiscount;
        }

        /**
         * @return the discountCode
         */
        public DiscountCode getDiscountCode() {
            return discountCode;
        }

        /**
         * @param discountCode the discountCode to set
         */
        public void setDiscountCode(DiscountCode discountCode) {
            this.discountCode = discountCode;
        }

    }

}
