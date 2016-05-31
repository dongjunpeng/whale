package com.buterfleoge.whale.biz.order.impl.create;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.buterfleoge.whale.Constants;
import com.buterfleoge.whale.Constants.BizCode;
import com.buterfleoge.whale.Constants.ErrorMsg;
import com.buterfleoge.whale.Constants.Status;
import com.buterfleoge.whale.biz.order.impl.discount.DiscountHandler;
import com.buterfleoge.whale.biz.travel.TravelBiz;
import com.buterfleoge.whale.dao.DiscountCodeRepository;
import com.buterfleoge.whale.type.entity.Discount;
import com.buterfleoge.whale.type.entity.DiscountCode;
import com.buterfleoge.whale.type.entity.OrderInfo;
import com.buterfleoge.whale.type.entity.OrderTravellers;
import com.buterfleoge.whale.type.entity.converter.PriceConverter;
import com.buterfleoge.whale.type.protocol.Error;
import com.buterfleoge.whale.type.protocol.order.CreateOrderRequest;
import com.buterfleoge.whale.type.protocol.order.CreateOrderResponse;
import com.buterfleoge.whale.type.protocol.order.GetDiscountRequest;
import com.buterfleoge.whale.type.protocol.order.GetDiscountResponse;

/**
 *
 * @author xiezhenzong
 *
 */
public class ValidateProcessor extends CreateOrderProcessor {

    @Autowired
    private DiscountCodeRepository discountCodeRepository;

    @Autowired
    private TravelBiz travelBiz;

    @Autowired
    private DiscountHandler discountHandler;

    @Override
    public void doCreate(Long accountid, CreateOrderRequest request, CreateOrderResponse response, CreateOrderContext context)
            throws Exception {
        doValidate(accountid, request, response, context);
    }

    private void doValidate(Long accountid, CreateOrderRequest request, CreateOrderResponse response,
            CreateOrderContext context) {
        validateTravellerCount(accountid, request, response, context);
        if (response.hasError()) {
            return;
        }

        GetDiscountResponse getDiscountResponse = getAllowedDiscount(accountid, request, context.getOrderInfo());
        if (getDiscountResponse.hasError()) {
            response.setStatus(getDiscountResponse.getStatus());
            response.getErrors().addAll(getDiscountResponse.getErrors());
            return;
        }
        List<Discount> policy = getDiscountResponse.getPolicy();
        BigDecimal discountPolicy = validateDiscountPolicy(accountid, request, response, policy, context);
        if (response.hasError()) {
            return;
        }
        Discount student = getDiscountResponse.getStudentDiscount();
        BigDecimal studentDiscount = validateStudentDiscount(accountid, request, response, student, context);
        if (response.hasError()) {
            return;
        }
        BigDecimal discountCode = validateDiscountCode(accountid, request, response, context);
        if (response.hasError()) {
            return;
        }

        int count = request.getTravellers().size();
        BigDecimal price = context.getGroup().getPrice().multiply(BigDecimal.valueOf(count));
        context.getOrderInfo().setPrice(price); // 设置这个订单原始价格应该多少钱，直接使用price这个结果，省的再算一遍
        if (discountPolicy != null) {
            price = price.subtract(discountPolicy);
        }
        if (studentDiscount != null) {
            price = price.subtract(studentDiscount);
        }
        if (discountCode != null) {
            price = price.subtract(discountCode);
        }
        if (price.multiply(PriceConverter.PRICE_FACTOR_BIGDECIMAL).longValue() != 
                request.getActualPrice().multiply(PriceConverter.PRICE_FACTOR_BIGDECIMAL).longValue()) {
            response.addError(new Error());
            response.setStatus(Status.BIZ_ERROR);
        }
    }

    private void validateTravellerCount(Long accountid, CreateOrderRequest request, CreateOrderResponse response,
            CreateOrderContext context) {
        List<OrderTravellers> travellers = request.getTravellers();
        int count = travellers.size();
        if (count > Constants.DefaultValue.MAX_ORDER_TRAVELLER_COUNT) {
            response.setStatus(Status.BIZ_ERROR);
            response.addError(new Error(BizCode.EXCEED_MAX_ORDER_TRAVELLER_COUNT, ErrorMsg.EXCEED_MAX_ORDER_TRAVELLER_COUNT));
            return;
        }
        int quota = travelBiz.getQuota(context.getOrderInfo().getGroupid(), request, response);
        if (response.hasError()) {
            return;
        }
        if (quota < count) {
            response.setStatus(Status.BIZ_ERROR);
            response.addError(new Error(BizCode.GROUP_QUOTA_FULL, ErrorMsg.GROUP_QUOTA_FULL));
        }
    }

    private GetDiscountResponse getAllowedDiscount(Long accountid, CreateOrderRequest request, OrderInfo orderInfo) {
        GetDiscountRequest getDiscountRequest = new GetDiscountRequest();
        getDiscountRequest.setReqid(request.getReqid());
        getDiscountRequest.setRouteid(orderInfo.getRouteid());
        getDiscountRequest.setGroupid(orderInfo.getGroupid());
        getDiscountRequest.setCount(request.getTravellers().size());
        GetDiscountResponse getDiscountResponse = new GetDiscountResponse();
        discountHandler.getDiscount(accountid, getDiscountRequest, getDiscountResponse);
        return getDiscountResponse;
    }

    private BigDecimal validateDiscountPolicy(Long accountid, CreateOrderRequest request, CreateOrderResponse response,
            List<Discount> policy, CreateOrderContext context) {
        Long policyDiscountid = request.getPolicyDiscountid();
        if (policyDiscountid != null && policyDiscountid > 0) {
            for (Discount discount : policy) { // 如果policy为空的话，则不会进入这个循环
                if (policyDiscountid.equals(discount.getDiscountid())) {
                    context.setPolicyDiscount(discount); // 设置策略优惠
                    return discount.getValue();
                }
            } // 没有在for内退出，则表示前端传过来的discountid不是正常可选的discountid
            response.addError(new Error()); // XieZhenzong:
            response.setStatus(Status.BIZ_ERROR);
        }
        return null;
    }

    private BigDecimal validateStudentDiscount(Long accountid, CreateOrderRequest request, CreateOrderResponse response,
            Discount studentDiscount, CreateOrderContext context) {
        Long studentDiscountid = request.getStudentDiscountid();
        int studentCount = request.getStudentCount();
        if (studentDiscountid != null && studentCount > 0) {
            if (studentDiscount != null && studentDiscountid.equals(studentDiscount.getDiscountid())) {
                context.setStudentDiscount(studentDiscount); // 设置学生优惠
                return studentDiscount.getValue().multiply(BigDecimal.valueOf(studentCount));
            } else {
                response.addError(new Error()); // XieZhenzong:
                response.setStatus(Status.BIZ_ERROR);
            }
        }
        return null;
    }

    private BigDecimal validateDiscountCode(Long accountid, CreateOrderRequest request, CreateOrderResponse response,
            CreateOrderContext context) {
        String code = request.getDiscountCode();
        if (StringUtils.hasText(code)) {
            try {
                DiscountCode discountCode = discountCodeRepository.findByAccountidAndDiscountCode(accountid, code);
                if (discountCode != null) {
                    context.setDiscountCode(discountCode); // 设置优惠码对象
                    return discountCode.getValue();
                } else {
                    response.addError(new Error()); // XieZhenzong:
                    response.setStatus(Status.BIZ_ERROR);
                }
            } catch (Exception e) {
                LOG.error("find discount code failed, reqid: " + request.getReqid(), e);
                response.setStatus(Status.DB_ERROR);
            }
        }
        return null;
    }

}
