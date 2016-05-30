package com.buterfleoge.whale.biz.order.impl.discount;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.buterfleoge.whale.Constants.Status;
import com.buterfleoge.whale.biz.order.impl.discount.DiscountStrategy.DiscountStrategyContext;
import com.buterfleoge.whale.dao.TravelGroupRepository;
import com.buterfleoge.whale.type.entity.Discount;
import com.buterfleoge.whale.type.entity.TravelGroup;
import com.buterfleoge.whale.type.protocol.order.GetDiscountRequest;
import com.buterfleoge.whale.type.protocol.order.GetDiscountResponse;

/**
 *
 * @author xiezhenzong
 *
 */
public class DiscountHandler {

    private static final Logger LOG = LoggerFactory.getLogger(DiscountHandler.class);

    @Autowired
    private TravelGroupRepository travelGroupRepository;

    private List<DiscountStrategy> discountStrategies = new CopyOnWriteArrayList<DiscountStrategy>();

    public void getDiscount(Long accountid, GetDiscountRequest request, GetDiscountResponse response) {
        DiscountStrategyContext context = null;
        try {
            TravelGroup group = travelGroupRepository.findOne(request.getGroupid());
            if (group == null) {
                return ;
            }
            context = new DiscountStrategyContext(group);
        } catch (Exception e) {
            LOG.error("find group failed, reqid: " + request.getReqid(), e);
            response.setStatus(Status.DB_ERROR);
            return;
        }
        for (DiscountStrategy discountStrategy : discountStrategies) {
            discountStrategy.handleDiscount(accountid, request, response, context);
        }
        List<Discount> policy = response.getPolicy();
        if (policy.isEmpty()) {
            return;
        }
        Discount maxDiscount = policy.get(policy.size() - 1);
        for (int n = policy.size() - 2; n >= 0; n--) {
            Discount discount = policy.get(n);
            if (discount.getValue().compareTo(maxDiscount.getValue()) > 0) {
                maxDiscount = discount;
            }
        }
        response.setDefaultDiscountid(maxDiscount.getDiscountid());
    }

    public void addDiscountStategy(DiscountStrategy strategy) {
        discountStrategies.add(strategy);
    }

}
