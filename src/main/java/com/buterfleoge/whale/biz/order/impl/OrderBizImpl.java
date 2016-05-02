package com.buterfleoge.whale.biz.order.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buterfleoge.whale.Constants.Status;
import com.buterfleoge.whale.biz.order.OrderBiz;
import com.buterfleoge.whale.dao.AccountSettingRepository;
import com.buterfleoge.whale.dao.DiscountCodeRepository;
import com.buterfleoge.whale.dao.DiscountRepository;
import com.buterfleoge.whale.dao.OrderDiscountRepository;
import com.buterfleoge.whale.dao.OrderInfoRepository;
import com.buterfleoge.whale.dao.OrderRefoundRepository;
import com.buterfleoge.whale.dao.OrderTravellersRepository;
import com.buterfleoge.whale.dao.TravelGroupRepository;
import com.buterfleoge.whale.dao.TravelRouteRepository;
import com.buterfleoge.whale.type.entity.Discount;
import com.buterfleoge.whale.type.entity.DiscountCode;
import com.buterfleoge.whale.type.entity.OrderDiscount;
import com.buterfleoge.whale.type.entity.OrderInfo;
import com.buterfleoge.whale.type.entity.OrderRefound;
import com.buterfleoge.whale.type.entity.OrderTravellers;
import com.buterfleoge.whale.type.entity.TravelGroup;
import com.buterfleoge.whale.type.entity.TravelRoute;
import com.buterfleoge.whale.type.enums.DiscountCodeStatus;
import com.buterfleoge.whale.type.enums.DiscountType;
import com.buterfleoge.whale.type.enums.OrderStatus;
import com.buterfleoge.whale.type.protocol.order.GetBriefRequest;
import com.buterfleoge.whale.type.protocol.order.GetBriefResponse;
import com.buterfleoge.whale.type.protocol.order.GetDiscountRequest;
import com.buterfleoge.whale.type.protocol.order.GetDiscountResponse;
import com.buterfleoge.whale.type.protocol.order.GetOrdersRequest;
import com.buterfleoge.whale.type.protocol.order.GetOrdersResponse;
import com.buterfleoge.whale.type.protocol.order.ValidateCodeRequest;
import com.buterfleoge.whale.type.protocol.order.ValidateCodeResponse;
import com.buterfleoge.whale.type.protocol.order.object.BriefOrder;
import com.buterfleoge.whale.type.protocol.order.object.DiscountObject;
import com.buterfleoge.whale.type.protocol.order.object.Order;

/**
 * @author Brent24
 *
 */

@Service("orderBiz")
public class OrderBizImpl implements OrderBiz {
    private static final Logger LOG = LoggerFactory.getLogger(OrderBizImpl.class);

    private static final Set<OrderStatus> CURRENT = new HashSet<OrderStatus>();
    private static final Set<OrderStatus> HISTORY = new HashSet<OrderStatus>();
    private static final Set<OrderStatus> ALL = new HashSet<OrderStatus>();
    // 领队头像
    private static final Set<String> LEADERS = new HashSet<String>();

    static {
        // 当前订单{创建等待付款,付款中,付款完成到账,退款中}
        CURRENT.add(OrderStatus.WATING);
        CURRENT.add(OrderStatus.PAYING);
        CURRENT.add(OrderStatus.PAID);
        CURRENT.add(OrderStatus.REFOUNDING);
        // 历史订单{退款完成,已出行}
        HISTORY.add(OrderStatus.REFOUNDED);
        HISTORY.add(OrderStatus.FINISH);
        // 全部订单，测试用
        ALL.add(OrderStatus.WATING);
        ALL.add(OrderStatus.PAYING);
        ALL.add(OrderStatus.PAID);
        ALL.add(OrderStatus.REFOUNDING);
        ALL.add(OrderStatus.REFOUNDED);
        ALL.add(OrderStatus.FINISH);
        ALL.add(OrderStatus.CANCEl);
        ALL.add(OrderStatus.CANCELPAYMENT);
        ALL.add(OrderStatus.TIMEOUT);
        // 领队头像
        LEADERS.add("/imgs/1.jpg");
        LEADERS.add("/imgs/2.jpg");
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
    private DiscountRepository discountRepository;

    @Autowired
    private DiscountCodeRepository discountCodeRepository;

    @Autowired
    private OrderDiscountRepository orderDiscountRepository;

    @Autowired
    private OrderRefoundRepository orderRefoundRepository;

    @Override
    public void getOrders(GetOrdersRequest request, GetOrdersResponse response) throws Exception {

        Long accountid = request.getAccountid();
        String orderType = request.getOrderType();

        List<Order> orders = new ArrayList<Order>();

        List<OrderInfo> orderInfo = null;

        TravelGroup travelGroup;
        TravelRoute travelRoute;
        List<OrderTravellers> orderTravellers;
        OrderDiscount discountPolicy;
        OrderDiscount discountCode;
        OrderRefound orderRefound;

        if (accountid == null) {
            response.setStatus(Status.PARAM_ERROR);
            return;
        }
        try {
            if (orderType == null) {
                orderInfo = orderInfoRepository.findByAccountidAndStatusIn(accountid, ALL);
            }
            if ("current".equals(orderType)) {
                orderInfo = orderInfoRepository.findByAccountidAndStatusIn(accountid, CURRENT);
            }
            if ("history".equals(orderType)) {
                orderInfo = orderInfoRepository.findByAccountidAndStatusIn(accountid, HISTORY);
            }

            for (OrderInfo tempOrderInfo : orderInfo) {

                Long orderid = tempOrderInfo.getOrderid();
                Long routeid = tempOrderInfo.getRouteid();
                Long groupid = tempOrderInfo.getGroupid();

                travelRoute = travelRouteRepository.findByRouteid(routeid);
                travelGroup = travelGroupRepository.findByGroupid(groupid);
                orderTravellers = orderTravellersRepository.findByOrderid(orderid);
                discountPolicy = orderDiscountRepository.findByOrderidAndDiscountCodeIsNull(orderid);
                discountCode = orderDiscountRepository.findByOrderidAndDiscountCodeNotNull(orderid);
                orderRefound = orderRefoundRepository.findByOrderid(orderid);

                orders.add(new Order(tempOrderInfo, travelRoute, travelGroup, orderTravellers, discountPolicy,
                        discountCode, orderRefound));
            }
            response.setOrders(orders);
            response.setStatus(Status.OK);
        } catch (Exception e) {
            LOG.error("get order failed", e);
            response.setStatus(Status.DB_ERROR);
        }
    }

    @Override
    public void getBriefOrders(GetBriefRequest request, GetBriefResponse response) throws Exception {
        Long accountid = request.getAccountid();
        List<BriefOrder> briefOrders = new ArrayList<BriefOrder>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            List<OrderInfo> orderInfo = orderInfoRepository.findByAccountidAndStatusIn(accountid, CURRENT);

            TravelGroup travelGroup;
            TravelRoute travelRoute;
            List<OrderTravellers> orderTravellers;

            for (OrderInfo tempOrderInfo : orderInfo) {

                Long orderid = tempOrderInfo.getOrderid();
                Long routeid = tempOrderInfo.getRouteid();
                Long groupid = tempOrderInfo.getGroupid();

                travelRoute = travelRouteRepository.findByRouteid(routeid);
                travelGroup = travelGroupRepository.findByGroupid(groupid);

                orderTravellers = orderTravellersRepository.findByOrderid(orderid);

                Set<String> avatars = new HashSet<String>();
                Set<String> names = new HashSet<String>();
                for (OrderTravellers tempTraveller : orderTravellers) {
                    Long travellerAccountid = tempTraveller.getAccountid();
                    names.add(tempTraveller.getName());
                    avatars.add(accountSettingRepository.findByAccountid(travellerAccountid).getAvatarUrl());
                }
                // 添加领队头像
                avatars.addAll(LEADERS);

                briefOrders.add(new BriefOrder(routeid, travelRoute.getName(), travelRoute.getTitle(),
                        travelRoute.getImgs().split(",")[0], sdf.format(new Date(travelGroup.getStartDate())),
                        sdf.format(new Date(travelGroup.getEndDate())), travelGroup.getWxQrcode(),
                        (travelGroup.getStartDate() - System.currentTimeMillis()) / 1000 / 60 / 60 / 24 + 1,
                        (tempOrderInfo.getAddTime() - System.currentTimeMillis()) / 1000 / 60 + 120,
                        tempOrderInfo.getStatus(), names, avatars));
            }
            response.setBriefOrders(briefOrders);
            response.setCurrentOrders(orderInfoRepository.countByAccountidAndStatusIn(accountid, CURRENT));
            response.setHistoryOrders(orderInfoRepository.countByAccountidAndStatusIn(accountid, HISTORY));
        } catch (Exception e) {
            LOG.error("get brief order failed", e);
            response.setStatus(Status.DB_ERROR);
        }
    }

    @Override
    public void getDiscount(GetDiscountRequest request, GetDiscountResponse response) throws Exception {
        Long routeid = request.getRouteid();
        Long groupid = request.getGroupid();
        int count = request.getCount();

        List<DiscountObject> discountList = new ArrayList<DiscountObject>();
        Discount discount;
        DiscountObject discountObject;

        try {
            TravelGroup group = travelGroupRepository.findByGroupid(groupid);

            // 人数优惠,订单不会超过5人
            switch (count) {
            case 1:
                discountObject = getDiscountObjectByType(DiscountType.COUNT_1);
                if (discountObject != null)
                    discountList.add(discountObject);
                break;
            case 2:
                discountObject = getDiscountObjectByType(DiscountType.COUNT_2);
                if (discountObject != null)
                    discountList.add(discountObject);
                break;
            case 3:
                discountObject = getDiscountObjectByType(DiscountType.COUNT_3);
                if (discountObject != null)
                    discountList.add(discountObject);
                break;
            case 4:
                discountObject = getDiscountObjectByType(DiscountType.COUNT_4);
                if (discountObject != null)
                    discountList.add(discountObject);
                break;
            case 5:
                discountObject = getDiscountObjectByType(DiscountType.COUNT_5);
                if (discountObject != null)
                    discountList.add(discountObject);
                break;
            }

            // 下单时间优惠
            discountObject = getDiscountObjectByType(DiscountType.TIME_ORDER);
            if (discountObject != null)
                discountList.add(discountObject);

            // 出行时间优惠
            discountObject = getDiscountObjectByTypeAndTime(DiscountType.TIME_TRAVEL, group.getStartDate());
            if (discountObject != null)
                discountList.add(discountObject);

            // 路线优惠 单人时才显示
            if (count == 1) {
                discount = discountRepository.findByTypeAndRouteidAndStartTimeLessThanAndEndTimeGreaterThan(
                        DiscountType.ROUTE, routeid, System.currentTimeMillis(), System.currentTimeMillis());
                if (discount != null)
                    discountList
                            .add(new DiscountObject(discount.getDiscountid(), discount.getDesc(), discount.getValue()));
            }

            // 最大优惠
            Long index = -1L;
            Long value = -1L;
            for (DiscountObject temp : discountList) {
                if (temp.getValue() > value) {
                    value = temp.getValue();
                    index = temp.getDiscountid();
                }
            }
            if (index > -1)
                response.setIndex(index);

            // 学生证优惠
            discount = discountRepository.findByTypeAndRouteidAndStartTimeLessThanAndEndTimeGreaterThan(
                    DiscountType.STUDENT, routeid, group.getStartDate(), group.getStartDate());
            if (discount != null)
                response.setStudentDiscount(discount.getValue());

            response.setList(discountList);
            response.setStatus(Status.OK);
        } catch (Exception e) {
            LOG.error("get discount failed", e);
            response.setStatus(Status.DB_ERROR);
        }
    }

    @Override
    public void validateDiscountCode(ValidateCodeRequest request, ValidateCodeResponse response) throws Exception {
        Long code = request.getCode();
        DiscountCode discountCode = discountCodeRepository.findByDiscountCode(code);
        if (discountCode == null) {
            response.setMessage("优惠码错误");
            response.setStatus(Status.PARAM_ERROR);
        } else {
            switch (discountCode.getStatus()) {
            case CREATED:
                discountCode.setStatus(DiscountCodeStatus.VERIFIED);
                discountCodeRepository.save(discountCode);
                response.setValue(discountCode.getValue());
                response.setMessage("验证通过！");
                response.setStatus(Status.OK);
                break;
            case VERIFIED:
                response.setValue(discountCode.getValue());
                response.setMessage("验证通过！");
                response.setStatus(Status.OK);
                break;
            case OCCUPIED:
                response.setMessage("优惠码已使用，请取消订单后重新验证");
                response.setStatus(Status.PARAM_ERROR);
                break;
            case TIMEOUT:
                response.setMessage("优惠码已过期");
                response.setStatus(Status.PARAM_ERROR);
                break;
            case USED:
                response.setMessage("优惠码已被使用");
                response.setStatus(Status.PARAM_ERROR);
                break;
            }
        }
    }

    private DiscountObject getDiscountObjectByTypeAndTime(DiscountType type, Long time) {
        Discount discount = discountRepository.findByTypeAndStartTimeLessThanAndEndTimeGreaterThan(type, time, time);
        if (discount != null) {
            return new DiscountObject(discount.getDiscountid(), discount.getDesc(), discount.getValue());
        } else {
            return null;
        }
    }

    private DiscountObject getDiscountObjectByType(DiscountType type) {
        return getDiscountObjectByTypeAndTime(type, System.currentTimeMillis());
    }

}
