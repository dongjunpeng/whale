package com.buterfleoge.whale.biz.order.impl;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.stereotype.Service;

import com.buterfleoge.whale.Constants.BizCode;
import com.buterfleoge.whale.Constants.ErrorMsg;
import com.buterfleoge.whale.Constants.Status;
import com.buterfleoge.whale.biz.order.OrderDiscountBiz;
import com.buterfleoge.whale.biz.order.impl.discount.DiscountStrategy;
import com.buterfleoge.whale.dao.DiscountCodeRepository;
import com.buterfleoge.whale.dao.TravelGroupRepository;
import com.buterfleoge.whale.type.entity.Discount;
import com.buterfleoge.whale.type.entity.DiscountCode;
import com.buterfleoge.whale.type.entity.TravelGroup;
import com.buterfleoge.whale.type.protocol.Error;
import com.buterfleoge.whale.type.protocol.order.GetDiscountRequest;
import com.buterfleoge.whale.type.protocol.order.GetDiscountResponse;
import com.buterfleoge.whale.type.protocol.order.ValidateCodeRequest;
import com.buterfleoge.whale.type.protocol.order.ValidateCodeResponse;

/**
 * @author xiezhenzong
 *
 */
@Service("orderDiscountBiz")
public class OrderDiscountBizImpl extends ApplicationObjectSupport implements OrderDiscountBiz, InitializingBean {

    private static final Logger LOG = LoggerFactory.getLogger(OrderDiscountBizImpl.class);

    @Autowired
    private TravelGroupRepository travelGroupRepository;

    @Autowired
    private DiscountCodeRepository discountCodeRepository;

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

    @Override
    public void validateDiscountCode(Long accountid, ValidateCodeRequest request, ValidateCodeResponse response)
            throws Exception {
        String code = request.getCode();
        DiscountCode discountCode = discountCodeRepository.findByDiscountCode(code);
        if (discountCode == null) {
            response.setStatus(Status.BIZ_ERROR);
            response.addError(new Error(BizCode.DISCOUNT_CODE_NOT_EXIST, ErrorMsg.DISCOUNT_CODE_NOT_EXIST));
            return;
        }
        // FIXME
        // switch (discountCode.getStatus().getValue()) {
        // case CREATED:
        // discountCode.setStatus(DiscountCodeStatus.VERIFIED);
        // discountCodeRepository.save(discountCode);
        // response.setValue(discountCode.getValue());
        // break;
        // case VERIFIED:
        // response.setValue(discountCode.getValue());
        // break;
        // case OCCUPIED:
        // response.setStatus(Status.BIZ_ERROR);
        // response.addError(new Error(ErrorMsg.DISCOUNT_CODE_OCCUPIED));
        // break;
        // case TIMEOUT:
        // response.setStatus(Status.BIZ_ERROR);
        // response.addError(new Error(ErrorMsg.DISCOUNT_CODE_TIMEOUT));
        // break;
        // case USED:
        // response.setStatus(Status.BIZ_ERROR);
        // response.addError(new Error(ErrorMsg.DISCOUNT_CODE_USED));
        // break;
        // }
    }

}
