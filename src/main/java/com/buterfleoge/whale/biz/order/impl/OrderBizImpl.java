package com.buterfleoge.whale.biz.order.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buterfleoge.whale.Constants.BizCode;
import com.buterfleoge.whale.Constants.ErrorMsg;
import com.buterfleoge.whale.Constants.Status;
import com.buterfleoge.whale.biz.order.OrderBiz;
import com.buterfleoge.whale.biz.order.impl.create.CreateOrderHandler;
import com.buterfleoge.whale.biz.order.impl.discount.DiscountHandler;
import com.buterfleoge.whale.biz.travel.TravelBiz;
import com.buterfleoge.whale.dao.AccountSettingRepository;
import com.buterfleoge.whale.dao.DiscountCodeRepository;
import com.buterfleoge.whale.dao.OrderDiscountRepository;
import com.buterfleoge.whale.dao.OrderInfoRepository;
import com.buterfleoge.whale.dao.OrderRefoundRepository;
import com.buterfleoge.whale.dao.OrderTravellersRepository;
import com.buterfleoge.whale.dao.TravelGroupRepository;
import com.buterfleoge.whale.dao.TravelRouteRepository;
import com.buterfleoge.whale.type.entity.DiscountCode;
import com.buterfleoge.whale.type.entity.OrderDiscount;
import com.buterfleoge.whale.type.entity.OrderInfo;
import com.buterfleoge.whale.type.entity.OrderRefound;
import com.buterfleoge.whale.type.entity.OrderTravellers;
import com.buterfleoge.whale.type.entity.TravelGroup;
import com.buterfleoge.whale.type.entity.TravelRoute;
import com.buterfleoge.whale.type.enums.DiscountCodeStatus;
import com.buterfleoge.whale.type.enums.DiscountType;
import com.buterfleoge.whale.type.enums.GroupStatus;
import com.buterfleoge.whale.type.enums.OrderStatus;
import com.buterfleoge.whale.type.enums.RefoundStatus;
import com.buterfleoge.whale.type.enums.RefoundType;
import com.buterfleoge.whale.type.enums.TravelType;
import com.buterfleoge.whale.type.protocol.Error;
import com.buterfleoge.whale.type.protocol.Response;
import com.buterfleoge.whale.type.protocol.order.AlipayRequest;
import com.buterfleoge.whale.type.protocol.order.CancelOrderRequest;
import com.buterfleoge.whale.type.protocol.order.CreateOrderRequest;
import com.buterfleoge.whale.type.protocol.order.CreateOrderResponse;
import com.buterfleoge.whale.type.protocol.order.GetBriefOrdersRequest;
import com.buterfleoge.whale.type.protocol.order.GetBriefOrdersResponse;
import com.buterfleoge.whale.type.protocol.order.GetDiscountRequest;
import com.buterfleoge.whale.type.protocol.order.GetDiscountResponse;
import com.buterfleoge.whale.type.protocol.order.GetOrderRequest;
import com.buterfleoge.whale.type.protocol.order.GetOrderResponse;
import com.buterfleoge.whale.type.protocol.order.NewOrderRequest;
import com.buterfleoge.whale.type.protocol.order.NewOrderResponse;
import com.buterfleoge.whale.type.protocol.order.RefoundRequest;
import com.buterfleoge.whale.type.protocol.order.RefoundResponse;
import com.buterfleoge.whale.type.protocol.order.ValidateCodeRequest;
import com.buterfleoge.whale.type.protocol.order.ValidateCodeResponse;

/**
 * @author Brent24
 *
 */
@Service("orderBiz")
public class OrderBizImpl implements OrderBiz {

    private static final Logger LOG = LoggerFactory.getLogger(OrderBizImpl.class);

    // 领队头像
    private static final Set<String> LEADERS = new HashSet<String>();

    // 退款状态
    private static final Set<RefoundStatus> CONFIRM = new HashSet<RefoundStatus>();

    static {
        // 领队头像
        LEADERS.add("/imgs/1.jpg");
        LEADERS.add("/imgs/2.jpg");

        // 退款已确认状态
        CONFIRM.add(RefoundStatus.CONFIRMED);
        CONFIRM.add(RefoundStatus.REFOUNDED);
    }

    @Autowired
    private AccountSettingRepository accountSettingRepository;

    @Autowired
    private TravelRouteRepository travelRouteRepository;

    @Autowired
    private TravelGroupRepository travelGroupRepository;

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Autowired
    private OrderTravellersRepository orderTravellersRepository;

    @Autowired
    private DiscountCodeRepository discountCodeRepository;

    @Autowired
    private OrderDiscountRepository orderDiscountRepository;

    @Autowired
    private OrderRefoundRepository orderRefoundRepository;

    @Autowired
    private TravelBiz travelBiz;

    @Autowired
    private DiscountHandler discountHandler;

    @Autowired
    private CreateOrderHandler createOrderHandler;

    @Autowired
    private BriefOrderHandler briefOrderHandler;

    @Override
    public void newOrder(Long accountid, NewOrderRequest request, NewOrderResponse response) throws Exception {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setAccountid(accountid);
        orderInfo.setRouteid(request.getRouteid());
        orderInfo.setGroupid(request.getGroupid());
        orderInfo.setStatus(OrderStatus.NEW);
        orderInfo.setIsAgreed(Boolean.FALSE);
        orderInfo.setAddTime(new Date());
        try {
            orderInfo = orderInfoRepository.save(orderInfo);
            response.setOrderid(orderInfo.getOrderid());
        } catch (Exception e) {
            LOG.error("new order info failed, reqid: " + request.getReqid(), e);
            response.setStatus(Status.DB_ERROR);
        }
    }

    @Override
    public void createOrder(Long accountid, CreateOrderRequest request, CreateOrderResponse response) throws Exception {
        createOrderHandler.createOrder(accountid, request, response);
    }

    @Override
    public void getOrder(Long accountid, GetOrderRequest request, GetOrderResponse response) throws Exception {
        Long orderid = request.getOrderid();
        OrderInfo orderInfo = null;
        try {
            orderInfo = orderInfoRepository.findOne(orderid);
            if (orderInfo != null) {
                response.setOrderInfo(orderInfo);
            } else {
                response.setStatus(Status.BIZ_ERROR);
                return;
            }
        } catch (Exception e) {
            LOG.error("find order info failed, reqid: " + request.getReqid(), e);
            response.setStatus(Status.DB_ERROR);
            return;
        }

        Long routeid = orderInfo.getRouteid();
        Long groupid = orderInfo.getGroupid();
        try {
            response.setTravelRoute(travelRouteRepository.findOne(routeid));
            response.setTravelGroup(travelGroupRepository.findOne(groupid));
            response.setOrderTravellers(orderTravellersRepository.findByOrderidAndAccountid(orderid, accountid));
            response.setPolicy(orderDiscountRepository.findByOrderidAndTypeIn(orderid, DiscountType.getDiscountPolicy()));
            response.setCode(orderDiscountRepository.findByOrderidAndType(orderid, DiscountType.COUPON));
            response.setStudent(orderDiscountRepository.findByOrderidAndType(orderid, DiscountType.STUDENT));
            response.setOrderRefound(orderRefoundRepository.findByOrderidAndStatusIn(orderid, CONFIRM));
        } catch (Exception e) {
            LOG.error("find order info detail failed, reqid: " + request.getReqid(), e);
            response.setStatus(Status.DB_ERROR);
            return;
        }
        int quota = travelBiz.getQuota(groupid, request, response);
        response.setStatus(Status.OK); // 团的剩余人数即使错误了，也没关系，大不了显示0
        response.setQuota(quota);
    }

    @Override
    public void getBriefOrders(Long accountid, GetBriefOrdersRequest request, GetBriefOrdersResponse response) throws Exception {
        briefOrderHandler.getBriefOrders(accountid, request, response);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelOrder(Long accountid, CancelOrderRequest request, Response response) throws Exception {
        Long orderid = request.getOrderid();
        OrderInfo orderInfo = orderInfoRepository.findOne(orderid);
        orderInfo.setStatus(OrderStatus.TIMEOUT);
        orderInfoRepository.save(orderInfo);

        Long groupid = orderInfo.getGroupid();
        int orderCount = orderInfo.getCount();
        TravelGroup group = travelGroupRepository.findOne(groupid);
        group.setStatus(GroupStatus.OPEN);
        group.setActualCount(group.getActualCount() - orderCount);
        travelGroupRepository.save(group);

        OrderDiscount orderDiscount = orderDiscountRepository.findByOrderidAndType(orderid, DiscountType.COUPON);
        if (orderDiscount != null) {
            String code = orderDiscount.getDiscountCode();
            DiscountCode discountCode = discountCodeRepository.findByDiscountCode(code);
            discountCode.setStatus(DiscountCodeStatus.VERIFIED);
            discountCodeRepository.save(discountCode);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void alipay(Long accountid, AlipayRequest request, Response response) {
        Long orderid = request.getOrderid();
        OrderInfo orderInfo = orderInfoRepository.findOne(orderid);
        OrderStatus status = orderInfo.getStatus();

        switch (status) {
            case WAITING:
                // TODO 支付宝相关跳转
                response.setStatus(Status.OK);
                break;
            case CANCEl:
                response.setErrors(Arrays.asList(new Error("订单已取消")));
                response.setStatus(Status.PARAM_ERROR);
                break;
            case TIMEOUT:
                response.setErrors(Arrays.asList(new Error("订单已超时")));
                response.setStatus(Status.PARAM_ERROR);
                break;
            // case PAYING:
            // response.setErrors(Arrays.asList(new Error("订单正在支付中")));
            // response.setStatus(Status.PARAM_ERROR);
            // break;
            case PAID:
                response.setErrors(Arrays.asList(new Error("订单已支付成功")));
                response.setStatus(Status.PARAM_ERROR);
                break;
            case CANCELPAYMENT:
                break;
            case CLOSED:
                break;
            case FINISH:
                break;
            case PAYING:
                break;
            case REFOUNDED:
                break;
            case REFOUNDING:
                break;
            default:
                break;
        }
    }

    @Override
    public void getRefoundInfo(Long accountid, RefoundRequest request, RefoundResponse response) {
        Long orderid = request.getOrderid();

        OrderInfo orderInfo = new OrderInfo();
        TravelGroup travelGroup = new TravelGroup();
        TravelRoute travelRoute = new TravelRoute();
        List<OrderTravellers> orderTravellers = new ArrayList<OrderTravellers>();
        OrderDiscount policy = new OrderDiscount();
        OrderDiscount code = new OrderDiscount();
        OrderDiscount student = new OrderDiscount();
        OrderRefound orderRefound = new OrderRefound();

        // setOrderObjects(orderid, orderInfo, travelRoute, travelGroup, orderTravellers, policy, code, student,
        // orderRefound);

        if (orderRefound.getOrderid() != null) {
            response.setStatus(Status.PARAM_ERROR);
            return;
        }

        // Long actualPrice = orderInfo.getActualPrice();
        TravelType travelType = travelRoute.getType();
        Long startDate = travelGroup.getStartDate().getTime();
        Long now = System.currentTimeMillis();
        Long leftMinutes = (startDate - now) / 1000 / 60;

        if (leftMinutes <= 0) {
            response.setStatus(Status.PARAM_ERROR);
            return;
        }

        orderRefound.setOrderid(orderid);
        // orderRefound.setAddTime(now);
        orderRefound.setStatus(RefoundStatus.CREATED);

        switch (travelType) {
            case LONG_TRIP:
                if (leftMinutes >= 60 * 24 * 21) {
                    orderRefound.setType(RefoundType.LONG_PCT_95);
                    // orderRefound.setRefound((long) (actualPrice * 0.95));
                } else {
                    if (leftMinutes >= 60 * 24 * 14) {
                        orderRefound.setType(RefoundType.LONG_PCT_80);
                        // orderRefound.setRefound((long) (actualPrice * 0.80));
                    } else {
                        if (leftMinutes >= 60 * 24 * 7) {
                            orderRefound.setType(RefoundType.LONG_PCT_50);
                            // orderRefound.setRefound((long) (actualPrice * 0.50));
                        } else {
                            orderRefound.setType(RefoundType.LONG_PCT_20);
                            // orderRefound.setRefound((long) (actualPrice * 0.20));
                        }
                    }
                }
                break;
            case SHORT_TRIP:
                if (leftMinutes >= 60 * 24 * 7) {
                    orderRefound.setType(RefoundType.SHORT_PCT_100);
                    // orderRefound.setRefound((long) (actualPrice));
                } else {
                    if (leftMinutes >= 60 * 24 * 4) {
                        orderRefound.setType(RefoundType.LONG_PCT_80);
                        // orderRefound.setRefound((long) (actualPrice * 0.80));
                    } else {
                        if (leftMinutes >= 60 * 24 * 1) {
                            orderRefound.setType(RefoundType.LONG_PCT_50);
                            // orderRefound.setRefound((long) (actualPrice * 0.50));
                        } else {
                            orderRefound.setType(RefoundType.LONG_PCT_20);
                            // orderRefound.setRefound((long) (actualPrice * 0.20));
                        }
                    }
                }
                break;
            case WEEKEND:
                break;
            case PARTY:
                break;
            case CITY_WALK:
                break;
            case INTERNATIONAL:
                break;
        }
        orderRefound = orderRefoundRepository.save(orderRefound);
        String leftTime = "剩余时间： " + leftMinutes / 60 / 24 + " 天 " + leftMinutes / 60 % 24 + " 小时 " + leftMinutes % 60 + " 分";

        response.setAll(orderInfo, travelGroup, travelRoute, orderTravellers, policy, code, student, leftTime, orderRefound);
        response.setStatus(Status.OK);

    }

    @Override
    public void getDiscount(Long accountid, GetDiscountRequest request, GetDiscountResponse response) throws Exception {
        discountHandler.getDiscount(accountid, request, response);
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
        switch (discountCode.getStatus()) {
            case CREATED:
                discountCode.setStatus(DiscountCodeStatus.VERIFIED);
                discountCodeRepository.save(discountCode);
                response.setValue(discountCode.getValue());
                break;
            case VERIFIED:
                response.setValue(discountCode.getValue());
                break;
            case OCCUPIED:
                response.setMessage("优惠码已使用，请取消订单后重新验证");
                response.setStatus(Status.BIZ_ERROR);
                break;
            case TIMEOUT:
                response.setMessage("优惠码已过期");
                response.setStatus(Status.BIZ_ERROR);
                break;
            case USED:
                response.setMessage("优惠码已被使用");
                response.setStatus(Status.BIZ_ERROR);
                break;
        }
    }

}
