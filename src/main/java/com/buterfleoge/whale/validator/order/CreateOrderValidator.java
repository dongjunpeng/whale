package com.buterfleoge.whale.validator.order;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.buterfleoge.whale.Constants.ErrorMsg;
import com.buterfleoge.whale.biz.order.OrderDiscountBiz;
import com.buterfleoge.whale.biz.travel.TravelBiz;
import com.buterfleoge.whale.dao.DiscountCodeRepository;
import com.buterfleoge.whale.dao.OrderInfoRepository;
import com.buterfleoge.whale.dao.TravelGroupRepository;
import com.buterfleoge.whale.type.entity.Discount;
import com.buterfleoge.whale.type.entity.DiscountCode;
import com.buterfleoge.whale.type.entity.OrderInfo;
import com.buterfleoge.whale.type.entity.TravelGroup;
import com.buterfleoge.whale.type.entity.converter.PriceConverter;
import com.buterfleoge.whale.type.protocol.Response;
import com.buterfleoge.whale.type.protocol.order.CreateOrderRequest;
import com.buterfleoge.whale.type.protocol.order.GetDiscountRequest;
import com.buterfleoge.whale.type.protocol.order.GetDiscountResponse;

/**
 * @author xiezhenzong
 *
 */
@Service("createOrderValidator")
public class CreateOrderValidator implements Validator {

    private static final Logger LOG = LoggerFactory.getLogger(CreateOrderValidator.class);

    @Autowired
    private TravelBiz travelBiz;

    @Autowired
    private OrderDiscountBiz orderDiscountBiz;

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Autowired
    private TravelGroupRepository travelGroupRepository;

    @Autowired
    private DiscountCodeRepository discountCodeRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return CreateOrderRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CreateOrderRequest request = (CreateOrderRequest) target;
        try {
            OrderInfo orderInfo = orderInfoRepository.findByOrderidAndAccountid(request.getOrderid(), request.getAccountid());
            if (orderInfo == null) {
                throw new Exception("Can't find this order, orderid: " + request.getOrderid());
            }
            validateTravellerCount(orderInfo, request, errors);
            validateDiscountPolicy(orderInfo, request, errors);
        } catch (Exception e) {
            LOG.error("validate discount failed, reqid: " + request.getReqid(), e);
            if (e.getMessage() != null) {
                errors.reject("校验失败，因为： " + e.getMessage());
            } else {
                errors.reject(ErrorMsg.CREATE_ORDER_VALIDATE_FAILED);
            }
        }
    }

    private void validateTravellerCount(OrderInfo orderInfo, CreateOrderRequest request, Errors errors) throws Exception {
        int count = request.getTravellers().size();
        Response response = new Response();
        int quota = travelBiz.getQuota(orderInfo.getGroupid(), request, response);
        if (response.hasError() || quota < 0) {
            errors.reject("人数错误");
            return;
        }
        if (quota < count) {
            errors.reject(ErrorMsg.GROUP_QUOTA_FULL);
        }
    }

    private void validateDiscountPolicy(OrderInfo orderInfo, CreateOrderRequest request, Errors errors) throws Exception {
        Long accountid = request.getAccountid();
        GetDiscountResponse getDiscountResponse = getAllowedDiscount(accountid, request, orderInfo);
        if (getDiscountResponse.hasError()) {
            errors.reject(ErrorMsg.CREATE_ORDER_VALIDATE_FAILED);
            return;
        }
        Discount discountPolicy = findDiscountPolicy(accountid, request, getDiscountResponse.getPolicy());
        Discount studentDiscount = getDiscountResponse.getStudentDiscount();
        DiscountCode discountCode = findDiscountCode(accountid, request);

        // 校验学生优惠
        Long studentDiscountid = request.getStudentDiscountid();
        int studentCount = request.getStudentCount();
        if (studentDiscountid != null && studentCount > 0) { // 前端有传过来才需要校验
            if (studentDiscount == null || !studentDiscountid.equals(studentDiscount.getDiscountid())) {
                throw new Exception("Invalidate student discount.");
            }
        }

        // 校验价格
        TravelGroup group = travelGroupRepository.findOne(orderInfo.getGroupid());
        int count = request.getTravellers().size();
        BigDecimal price = group.getPrice().multiply(BigDecimal.valueOf(count));
        if (discountPolicy != null) {
            price = price.subtract(discountPolicy.getValue());
        }
        if (studentDiscount != null) {
            price = price.subtract(studentDiscount.getValue().multiply(BigDecimal.valueOf(studentCount)));
        }
        if (discountCode != null) {
            price = price.subtract(discountCode.getValue());
        }
        if (price.multiply(PriceConverter.PRICE_FACTOR_BIGDECIMAL).longValue() != request.getActualPrice()
                .multiply(PriceConverter.PRICE_FACTOR_BIGDECIMAL).longValue()) { // 转化为整数来比较
            errors.reject("实付金额有误");
        }
    }

    private GetDiscountResponse getAllowedDiscount(Long accountid, CreateOrderRequest request, OrderInfo orderInfo) throws Exception {
        GetDiscountRequest getDiscountRequest = new GetDiscountRequest();
        getDiscountRequest.setReqid(request.getReqid());
        getDiscountRequest.setRouteid(orderInfo.getRouteid());
        getDiscountRequest.setGroupid(orderInfo.getGroupid());
        getDiscountRequest.setCount(request.getTravellers().size());
        GetDiscountResponse getDiscountResponse = new GetDiscountResponse();
        orderDiscountBiz.getDiscount(accountid, getDiscountRequest, getDiscountResponse);
        return getDiscountResponse;
    }

    private Discount findDiscountPolicy(Long accountid, CreateOrderRequest request, List<Discount> policy) throws Exception {
        Long policyDiscountid = request.getPolicyDiscountid();
        if (policyDiscountid != null && policyDiscountid > 0) {
            for (Discount discount : policy) { // 如果policy为空的话，则不会进入这个循环
                if (policyDiscountid.equals(discount.getDiscountid())) {
                    return discount;
                }
            } // 没有在for内退出，则表示前端传过来的discountid不是正常可选的discountid
            throw new Exception("Invalid discount policy.");
        }
        return null;
    }

    private DiscountCode findDiscountCode(Long accountid, CreateOrderRequest request) throws Exception {
        String code = request.getDiscountCode();
        if (StringUtils.hasText(code)) {
            DiscountCode discountCode = discountCodeRepository.findByDiscountCode(code);
            if (discountCode == null) {
                throw new Exception("Invalid discount code.");
            }
            return discountCode;
        }
        return null;
    }

}
