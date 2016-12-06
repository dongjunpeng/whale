package com.buterfleoge.whale.biz.order;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.stereotype.Service;

import com.buterfleoge.whale.Constants.Status;
import com.buterfleoge.whale.biz.OrderDiscountBiz;
import com.buterfleoge.whale.biz.order.discount.DiscountStrategy;
import com.buterfleoge.whale.dao.TravelGroupRepository;
import com.buterfleoge.whale.type.entity.OrderDiscount;
import com.buterfleoge.whale.type.entity.TravelGroup;
import com.buterfleoge.whale.type.protocol.order.GetDiscountRequest;
import com.buterfleoge.whale.type.protocol.order.GetDiscountResponse;

/**
 * @author xiezhenzong
 *
 */
@Service("orderDiscountBiz")
public class OrderDiscountBizImpl extends ApplicationObjectSupport implements OrderDiscountBiz, InitializingBean {

    private static final Logger LOG = LoggerFactory.getLogger(OrderDiscountBizImpl.class);

    @Autowired
    private TravelGroupRepository travelGroupRepository;

    private List<DiscountStrategy> discountStrategies = new CopyOnWriteArrayList<DiscountStrategy>();

    @Override
    public void afterPropertiesSet() throws Exception {
        Collection<DiscountStrategy> strategies = getApplicationContext().getBeansOfType(DiscountStrategy.class)
                .values();
        discountStrategies.addAll(strategies);
        AnnotationAwareOrderComparator.sort(discountStrategies);
    }

    @Override
    public void getDiscount(Long accountid, GetDiscountRequest request, GetDiscountResponse response) throws Exception {
        try {
            TravelGroup group = travelGroupRepository.findOne(request.getGroupid());
            if (group == null) {
                return;
            }
        } catch (Exception e) {
            LOG.error("find group failed, reqid: " + request.getReqid(), e);
            response.setStatus(Status.DB_ERROR);
            return;
        }
        for (DiscountStrategy discountStrategy : discountStrategies) {
            discountStrategy.handleDiscount(accountid, request, response);
        }
    }

    @Override
    public OrderDiscount filterDiscounts(List<OrderDiscount> orderDiscounts, int type) {
        for (OrderDiscount orderDiscount : orderDiscounts) {
            if (orderDiscount.getType() == type) {
                return orderDiscount;
            }
        }
        return null;
    }

    @Override
    public OrderDiscount filterDiscounts(List<OrderDiscount> orderDiscounts, Long orderid, int type) {
        for (OrderDiscount orderDiscount : orderDiscounts) {
            if (orderid.equals(orderDiscount.getOrderid()) && orderDiscount.getType() == type) {
                return orderDiscount;
            }
        }
        return null;
    }

    @Override
    public OrderDiscount filterDiscounts(List<OrderDiscount> orderDiscounts, Set<Integer> types) {
        for (OrderDiscount orderDiscount : orderDiscounts) {
            if (types.contains(orderDiscount.getType())) {
                return orderDiscount;
            }
        }
        return null;
    }

    @Override
    public OrderDiscount filterDiscounts(List<OrderDiscount> orderDiscounts, Long orderid, Set<Integer> types) {
        for (OrderDiscount orderDiscount : orderDiscounts) {
            if (orderid.equals(orderDiscount.getOrderid()) && types.contains(orderDiscount.getType())) {
                return orderDiscount;
            }
        }
        return null;
    }

}
