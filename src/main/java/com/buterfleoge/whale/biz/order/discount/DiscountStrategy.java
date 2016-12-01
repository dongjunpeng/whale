package com.buterfleoge.whale.biz.order.discount;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.buterfleoge.whale.dao.DiscountRepository;
import com.buterfleoge.whale.dao.TravelGroupRepository;
import com.buterfleoge.whale.dao.TravelRouteRepository;
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

    @Autowired
    protected TravelRouteRepository travelRouteRepository;

    @Autowired
    protected TravelGroupRepository travelGroupRepository;

    public abstract void handleDiscount(Long accountid, GetDiscountRequest request, GetDiscountResponse response);

}
