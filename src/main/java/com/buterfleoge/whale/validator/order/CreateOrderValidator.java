package com.buterfleoge.whale.validator.order;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.buterfleoge.whale.Constants.ErrorMsg;
import com.buterfleoge.whale.biz.OrderDiscountBiz;
import com.buterfleoge.whale.biz.TravelBiz;
import com.buterfleoge.whale.dao.CouponRepository;
import com.buterfleoge.whale.dao.OrderInfoRepository;
import com.buterfleoge.whale.dao.TravelGroupRepository;
import com.buterfleoge.whale.type.CouponStatus;
import com.buterfleoge.whale.type.OrderStatus;
import com.buterfleoge.whale.type.entity.Coupon;
import com.buterfleoge.whale.type.entity.Discount;
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
    private CouponRepository couponRepository;

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
            if (orderInfo.getStatus() != OrderStatus.NEW.value) {
                throw new IllegalStateException("order in't in new state, orderid: " + request.getOrderid());
            }
            validateTravellerCount(orderInfo, request, errors);
            validateActualPrice(orderInfo, request, errors);
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
        if (!travelBiz.isGroupAvailable(orderInfo.getGroupid(), count, request, response)) {
            errors.reject(ErrorMsg.GROUP_QUOTA_FULL);
        }
    }

    private void validateActualPrice(OrderInfo orderInfo, CreateOrderRequest request, Errors errors) throws Exception {
        if (request.getActualPrice() == null || BigDecimal.ZERO.compareTo(request.getActualPrice()) >= 0) { // 如果比0小
            errors.reject("金额有误！");
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
        Coupon coupon = findCoupon(accountid, request);

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
        if (studentDiscount != null && studentCount > 0) {
            price = price.subtract(studentDiscount.getValue().multiply(BigDecimal.valueOf(studentCount)));
        }
        if (coupon != null) {
            price = price.subtract(coupon.getValue());
        }
        if (PriceConverter.yuanToLi(price).longValue() != PriceConverter.yuanToLi(request.getActualPrice()).longValue()) { // 转化为整数来比较
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
            throw new Exception("错误的优惠策略");
        }
        return null;
    }

    private Coupon findCoupon(Long accountid, CreateOrderRequest request) throws Exception {
        Long couponid = request.getCouponid();
        if (couponid != null) {
            Coupon coupon = couponRepository.findByCouponidAndAccountid(couponid, accountid);
            if (coupon == null || (coupon.getAccountid() != null && !coupon.getAccountid().equals(accountid))) {
                throw new Exception("优惠券不存在");
            }
            if (coupon.getStatus() != CouponStatus.CREATED.value) {
                throw new Exception(Coupon.ERRMSG.get(coupon.getStatus()));
            }
            long now = System.currentTimeMillis();
            if (now < coupon.getStartTime().getTime() || now > coupon.getEndTime().getTime()) {
                throw new Exception("不在优惠券的使用时间内");
            }
            return coupon;
        }
        return null;
    }

}
