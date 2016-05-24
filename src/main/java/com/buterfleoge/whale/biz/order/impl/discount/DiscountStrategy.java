package com.buterfleoge.whale.biz.order.impl.discount;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.dao.DiscountRepository;
import com.buterfleoge.whale.type.entity.TravelGroup;
import com.buterfleoge.whale.type.protocol.order.GetDiscountRequest;
import com.buterfleoge.whale.type.protocol.order.GetDiscountResponse;

/**
 *
 * @author xiezhenzong
 *
 */
public abstract class DiscountStrategy {

    protected static final Logger LOG = LoggerFactory.getLogger(DiscountStrategy.class);

    @Autowired
    protected DiscountRepository discountRepository;

    public abstract void handleDiscount(Long accountid, GetDiscountRequest request, GetDiscountResponse response,
            DiscountStrategyContext context);

    public static class DiscountStrategyContext extends BaseObject {

        private TravelGroup group;

        /**
         * @param request
         * @param response
         * @param group
         */
        public DiscountStrategyContext(TravelGroup group) {
            this.group = group;
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

    }

}
