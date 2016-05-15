package com.buterfleoge.whale.biz.order.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buterfleoge.whale.Constants.Status;
import com.buterfleoge.whale.biz.order.OrderBiz;
import com.buterfleoge.whale.biz.travel.TravelBiz;
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
import com.buterfleoge.whale.type.protocol.order.GetBriefRequest;
import com.buterfleoge.whale.type.protocol.order.GetBriefResponse;
import com.buterfleoge.whale.type.protocol.order.GetDiscountRequest;
import com.buterfleoge.whale.type.protocol.order.GetDiscountResponse;
import com.buterfleoge.whale.type.protocol.order.GetOrderDetailRequest;
import com.buterfleoge.whale.type.protocol.order.GetOrderDetailResponse;
import com.buterfleoge.whale.type.protocol.order.NewOrderRequest;
import com.buterfleoge.whale.type.protocol.order.NewOrderResponse;
import com.buterfleoge.whale.type.protocol.order.RefoundRequest;
import com.buterfleoge.whale.type.protocol.order.RefoundResponse;
import com.buterfleoge.whale.type.protocol.order.ValidateCodeRequest;
import com.buterfleoge.whale.type.protocol.order.ValidateCodeResponse;
import com.buterfleoge.whale.type.protocol.order.object.BriefOrder;
import com.buterfleoge.whale.type.protocol.order.object.DiscountObject;

/**
 * @author Brent24
 *
 */

@Service("orderBiz")
public class OrderBizImpl implements OrderBiz {

    private static final Logger LOG = LoggerFactory.getLogger(OrderBizImpl.class);

    // 订单状态
    private static final Set<OrderStatus> CURRENT = new HashSet<OrderStatus>();
    private static final Set<OrderStatus> HISTORY = new HashSet<OrderStatus>();
    private static final Set<OrderStatus> VISIBLE = new HashSet<OrderStatus>();
    private static final Set<OrderStatus> ALL = new HashSet<OrderStatus>();
    // 领队头像
    private static final Set<String> LEADERS = new HashSet<String>();
    // 优惠类型
    private static final Set<DiscountType> POLICY = new HashSet<DiscountType>();
    // 退款状态
    private static final Set<RefoundStatus> CONFIRM = new HashSet<RefoundStatus>();

    static {
        // 当前订单{创建等待付款,付款中,付款完成到账,退款中}
        CURRENT.add(OrderStatus.WAITING);
        CURRENT.add(OrderStatus.PAYING);
        CURRENT.add(OrderStatus.PAID);
        CURRENT.add(OrderStatus.REFOUNDING);
        // 历史订单{退款完成,已出行}
        HISTORY.add(OrderStatus.REFOUNDED);
        HISTORY.add(OrderStatus.FINISH);
        // 可见订单
        VISIBLE.add(OrderStatus.WAITING);
        VISIBLE.add(OrderStatus.PAYING);
        VISIBLE.add(OrderStatus.PAID);
        VISIBLE.add(OrderStatus.REFOUNDING);
        VISIBLE.add(OrderStatus.REFOUNDED);
        VISIBLE.add(OrderStatus.FINISH);
        // 全部订单，测试用
        ALL.add(OrderStatus.WAITING);
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
        // 优惠策略
        POLICY.add(DiscountType.COUNT_1);
        POLICY.add(DiscountType.COUNT_2);
        POLICY.add(DiscountType.COUNT_3);
        POLICY.add(DiscountType.COUNT_4);
        POLICY.add(DiscountType.COUNT_5);
        POLICY.add(DiscountType.ROUTE);
        POLICY.add(DiscountType.TIME_ORDER);
        POLICY.add(DiscountType.TIME_TRAVEL);
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
    private DiscountRepository discountRepository;

    @Autowired
    private DiscountCodeRepository discountCodeRepository;

    @Autowired
    private OrderDiscountRepository orderDiscountRepository;

    @Autowired
    private OrderRefoundRepository orderRefoundRepository;

    @Autowired
    private TravelBiz travelBiz;

    @Override
    public void newOrder(Long accountid, NewOrderRequest request, NewOrderResponse response) throws Exception {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setAccountid(accountid);
        orderInfo.setRouteid(request.getRouteid());
        orderInfo.setGroupid(request.getGroupid());
        orderInfo.setStatus(OrderStatus.NEW);
        orderInfo.setIsAgreed(Boolean.FALSE);
        orderInfo.setAddTime(System.currentTimeMillis());
        try {
            orderInfo = orderInfoRepository.save(orderInfo);
            response.setOrderid(orderInfo.getOrderid());
        } catch (Exception e) {
            LOG.error("new a order failed, order: " + orderInfo, e);
            response.setStatus(Status.DB_ERROR);
        }
    }

    @Override
    public void getOrderDetail(GetOrderDetailRequest request, GetOrderDetailResponse response) throws Exception {
        Long orderid = request.getOrderid();

        OrderInfo orderInfo = new OrderInfo();
        TravelGroup travelGroup = new TravelGroup();
        TravelRoute travelRoute = new TravelRoute();
        List<OrderTravellers> orderTravellers = new ArrayList<OrderTravellers>();
        OrderDiscount policy = new OrderDiscount();
        OrderDiscount code = new OrderDiscount();
        OrderDiscount student = new OrderDiscount();
        OrderRefound orderRefound = new OrderRefound();

        setOrderObjects(orderid, orderInfo, travelRoute, travelGroup, orderTravellers, policy, code, student,
                orderRefound);
        response.setOrderObjects(orderInfo, travelGroup, travelRoute, orderTravellers, policy, code, student,
                orderRefound);
        response.setQuota(travelBiz.getQuota(travelGroup.getGroupid()));
        response.setStatus(Status.OK);
    }

    @Override
    public void getBriefOrders(GetBriefRequest request, GetBriefResponse response) throws Exception {
        Long accountid = request.getAccountid();
        String orderType = request.getOrderType();
        Set<OrderStatus> statusSet = VISIBLE;// 不输入或者输入错误返回全部
        List<BriefOrder> briefOrders = new ArrayList<BriefOrder>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        if ("CURRENT".equals(orderType)) {
            statusSet = CURRENT;
        }
        if ("HISTORY".equals(orderType)) {
            statusSet = HISTORY;
        }
        if ("ALL".equals(orderType)) {
            statusSet = ALL;
        }

        try {
            List<OrderInfo> orderInfo = orderInfoRepository.findByAccountidAndStatusIn(accountid, statusSet);

            TravelGroup travelGroup;
            TravelRoute travelRoute;
            List<OrderTravellers> orderTravellers;

            // 循环获取用户所有订单信息
            for (OrderInfo tempOrderInfo : orderInfo) {

                if (tempOrderInfo.getAddTime() + 1000 * 60 * 120 < System.currentTimeMillis()
                        && tempOrderInfo.getStatus() == OrderStatus.WAITING) {
                    tempOrderInfo.setStatus(OrderStatus.TIMEOUT);
                    orderInfoRepository.save(tempOrderInfo);
                }

                Long orderid = tempOrderInfo.getOrderid();
                Long routeid = tempOrderInfo.getRouteid();
                Long groupid = tempOrderInfo.getGroupid();

                travelRoute = travelRouteRepository.findByRouteid(routeid);
                travelGroup = travelGroupRepository.findByGroupid(groupid);

                orderTravellers = orderTravellersRepository.findByOrderid(orderid);

                // 获得每个traveller报名账户的头像
                Set<String> avatars = new HashSet<String>();
                Set<String> names = new HashSet<String>();
                for (OrderTravellers tempTraveller : orderTravellers) {
                    Long travellerAccountid = tempTraveller.getAccountid();
                    names.add(tempTraveller.getName());
                    avatars.add(accountSettingRepository.findByAccountid(travellerAccountid).getAvatarUrl());
                }
                // 添加领队头像
                avatars.addAll(LEADERS);

                // 封装订单对象
                briefOrders.add(new BriefOrder(routeid, travelRoute.getName(), travelRoute.getTitle(),
                        travelRoute.getHeadImg(), sdf.format(new Date(travelGroup.getStartDate())),
                        sdf.format(new Date(travelGroup.getEndDate())), travelGroup.getWxQrcode(),
                        (travelGroup.getStartDate() - System.currentTimeMillis()) / 1000 / 60 / 60 / 24 + 1,
                        (tempOrderInfo.getAddTime() - System.currentTimeMillis()) / 1000 / 60 + 120,
                        tempOrderInfo.getOrderid(), tempOrderInfo.getStatus(), tempOrderInfo.getActualPrice(), names,
                        avatars));
            }
            Collections.sort(briefOrders);
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

            // 路线优惠
            // 尽可能不用，通过不同group来实现价格变化
            // 这里单人订单时出现做测试用
            if (count == 1) {
                discount = discountRepository.findByTypeAndRouteidAndStartTimeLessThanAndEndTimeGreaterThan(
                        DiscountType.ROUTE, routeid, System.currentTimeMillis(), System.currentTimeMillis());
                if (discount != null)
                    discountList
                            .add(new DiscountObject(discount.getDiscountid(), discount.getDesc(), discount.getValue()));
            }

            // 最大优惠
            Long defaultDiscountid = -1L;
            Long value = -1L;
            for (DiscountObject temp : discountList) {
                if (temp.getValue() > value) {
                    value = temp.getValue();
                    defaultDiscountid = temp.getDiscountid();
                }
            }
            if (defaultDiscountid > -1)
                response.setDefaultDiscountid(defaultDiscountid);

            // 学生证优惠
            discount = discountRepository.findByTypeAndRouteidAndStartTimeLessThanAndEndTimeGreaterThan(
                    DiscountType.STUDENT, routeid, group.getStartDate(), group.getStartDate());
            if (discount != null)
                response.setStudentDiscount(
                        new DiscountObject(discount.getDiscountid(), discount.getDesc(), discount.getValue()));

            response.setPolicy(discountList);
            response.setStatus(Status.OK);
        } catch (Exception e) {
            LOG.error("get discount failed", e);
            response.setStatus(Status.DB_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void validateDiscountCode(ValidateCodeRequest request, ValidateCodeResponse response) throws Exception {
        String code = request.getCode();
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
                response.setStatus(Status.OK);
                break;
            case VERIFIED:
                response.setValue(discountCode.getValue());
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createOrder(CreateOrderRequest request, Response response) throws Exception {

        // request获取信息
        Long orderid = request.getOrderid();
        List<OrderTravellers> travellers = request.getTravellers();
        int count = travellers.size();
        Long policyDiscountid = request.getPolicyDiscountid();
        String code = request.getDiscountCode();
        Long studentDiscountid = request.getStudentDiscountid();
        int studentCount = request.getStudentCount();
        Long fronthActualPrice = request.getActualPrice();

        try {
            // repository获取对象

            Discount policyDiscount = discountRepository.findByDiscountid(request.getPolicyDiscountid());
            DiscountCode discountCode = discountCodeRepository.findByDiscountCode(request.getDiscountCode());
            Discount studentDiscount = discountRepository.findByDiscountid(request.getStudentDiscountid());
            OrderInfo orderInfo = orderInfoRepository.findByOrderid(orderid);

            // group相关处理
            Long groupid = orderInfo.getGroupid();
            TravelGroup group = travelGroupRepository.findByGroupid(groupid);
            int quota = travelBiz.getQuota(groupid);
            // 名额不足异常
            if (quota < count) {
                throw new Exception("名额不足！");
            }
            if (quota == count) {
                group.setStatus(GroupStatus.FULL);
            }
            group.setActualCount(group.getActualCount() + count);
            travelGroupRepository.save(group);

            // 折扣是否一致
            Long price = group.getPrice() * count;
            Long actualPrice = price - ((policyDiscount != null) ? policyDiscount.getValue() : 0)
                    - ((discountCode != null) ? discountCode.getValue() : 0)
                    - ((studentDiscount != null) ? studentDiscount.getValue() : 0) * studentCount;
            if (!fronthActualPrice.equals(actualPrice)) {
                throw new Exception("折扣错误！");
            }

            // OrderInfo对象
            orderInfo.setStatus(OrderStatus.WAITING);
            orderInfo.setCount(count);
            orderInfo.setStudentCount(studentCount);
            orderInfo.setPrice(price);
            orderInfo.setActualPrice(actualPrice);
            orderInfo.setIsAgreed(request.getIsAgreed());
            orderInfo.setAddTime(System.currentTimeMillis());
            orderInfo = orderInfoRepository.save(orderInfo);

            // 封装保存OrderTravellers
            for (OrderTravellers temp : travellers) {
                temp.setOrderid(orderid);
                temp.setAccountid(orderInfo.getAccountid());
            }
            orderTravellersRepository.save(travellers);

            // 封装保存OrderDiscount
            if (policyDiscountid != null) {

                OrderDiscount policy = new OrderDiscount();
                policy.setOrderid(orderid);
                policy.setDiscountid(policyDiscount.getDiscountid());
                policy.setType(policyDiscount.getType());
                policy.setRouteid(policyDiscount.getRouteid());
                policy.setValue(policyDiscount.getValue());
                policy.setDesc(policyDiscount.getDesc());
                policy.setAddTime(System.currentTimeMillis());
                orderDiscountRepository.save(policy);
            }

            if (code != null) {

                OrderDiscount codeDiscount = new OrderDiscount();
                codeDiscount.setOrderid(orderid);
                codeDiscount.setDiscountCode(discountCode.getDiscountCode());
                codeDiscount.setType(DiscountType.COUPON);
                codeDiscount.setValue(discountCode.getValue());
                codeDiscount.setAddTime(System.currentTimeMillis());
                orderDiscountRepository.save(codeDiscount);

                // 改变优惠码状态
                discountCode.setStatus(DiscountCodeStatus.OCCUPIED);
                discountCodeRepository.save(discountCode);
            }

            if (studentDiscountid != null) {

                OrderDiscount student = new OrderDiscount();
                student.setOrderid(orderid);
                student.setRouteid(orderInfo.getRouteid());
                student.setDiscountid(studentDiscount.getDiscountid());
                student.setType(DiscountType.STUDENT);
                student.setRouteid(studentDiscount.getRouteid());
                student.setValue(studentDiscount.getValue());
                student.setDesc(studentDiscount.getDesc());
                student.setAddTime(System.currentTimeMillis());
                orderDiscountRepository.save(student);
            }

            // 更改group信息

            response.setStatus(Status.OK);

        } catch (Exception e) {
            LOG.error("create order failed", e);
            response.setStatus(Status.DB_ERROR);
            throw new Exception("事物滚回");
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelOrder(CancelOrderRequest request, Response response) throws Exception {
        Long orderid = request.getOrderid();
        OrderInfo orderInfo = orderInfoRepository.findByOrderid(orderid);
        orderInfo.setStatus(OrderStatus.TIMEOUT);
        orderInfoRepository.save(orderInfo);

        Long groupid = orderInfo.getGroupid();
        int orderCount = orderInfo.getCount();
        TravelGroup group = travelGroupRepository.findByGroupid(groupid);
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
    public void alipay(AlipayRequest request, Response response) {
        Long orderid = request.getOrderid();
        OrderInfo orderInfo = orderInfoRepository.findByOrderid(orderid);
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

    // 退款算法
    @Override
    public void getRefoundInfo(RefoundRequest request, RefoundResponse response) {
        Long orderid = request.getOrderid();

        OrderInfo orderInfo = new OrderInfo();
        TravelGroup travelGroup = new TravelGroup();
        TravelRoute travelRoute = new TravelRoute();
        List<OrderTravellers> orderTravellers = new ArrayList<OrderTravellers>();
        OrderDiscount policy = new OrderDiscount();
        OrderDiscount code = new OrderDiscount();
        OrderDiscount student = new OrderDiscount();
        OrderRefound orderRefound = new OrderRefound();

        setOrderObjects(orderid, orderInfo, travelRoute, travelGroup, orderTravellers, policy, code, student,
                orderRefound);

        if (orderRefound.getOrderid() != null) {
            response.setStatus(Status.PARAM_ERROR);
            return;
        }

        Long actualPrice = orderInfo.getActualPrice();
        TravelType travelType = travelRoute.getType();
        Long startDate = travelGroup.getStartDate();
        Long now = System.currentTimeMillis();
        Long leftMinutes = (startDate - now) / 1000 / 60;

        if (leftMinutes <= 0) {
            response.setStatus(Status.PARAM_ERROR);
            return;
        }

        orderRefound.setOrderid(orderid);
        orderRefound.setAddTime(now);
        orderRefound.setStatus(RefoundStatus.CREATED);

        switch (travelType) {
        case LONG_TRIP:
            if (leftMinutes >= 60 * 24 * 21) {
                orderRefound.setType(RefoundType.LONG_PCT_95);
                orderRefound.setRefound((long) (actualPrice * 0.95));
            } else {
                if (leftMinutes >= 60 * 24 * 14) {
                    orderRefound.setType(RefoundType.LONG_PCT_80);
                    orderRefound.setRefound((long) (actualPrice * 0.80));
                } else {
                    if (leftMinutes >= 60 * 24 * 7) {
                        orderRefound.setType(RefoundType.LONG_PCT_50);
                        orderRefound.setRefound((long) (actualPrice * 0.50));
                    } else {
                        orderRefound.setType(RefoundType.LONG_PCT_20);
                        orderRefound.setRefound((long) (actualPrice * 0.20));
                    }
                }
            }
            break;
        case SHORT_TRIP:
            if (leftMinutes >= 60 * 24 * 7) {
                orderRefound.setType(RefoundType.SHORT_PCT_100);
                orderRefound.setRefound((long) (actualPrice));
            } else {
                if (leftMinutes >= 60 * 24 * 4) {
                    orderRefound.setType(RefoundType.LONG_PCT_80);
                    orderRefound.setRefound((long) (actualPrice * 0.80));
                } else {
                    if (leftMinutes >= 60 * 24 * 1) {
                        orderRefound.setType(RefoundType.LONG_PCT_50);
                        orderRefound.setRefound((long) (actualPrice * 0.50));
                    } else {
                        orderRefound.setType(RefoundType.LONG_PCT_20);
                        orderRefound.setRefound((long) (actualPrice * 0.20));
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
        String leftTime = "剩余时间： " + leftMinutes / 60 / 24 + " 天 " + leftMinutes / 60 % 24 + " 小时 " + leftMinutes % 60
                + " 分";

        response.setAll(orderInfo, travelGroup, travelRoute, orderTravellers, policy, code, student, leftTime,
                orderRefound);
        response.setStatus(Status.OK);

    }

    private void setOrderObjects(Long orderid, OrderInfo orderInfo, TravelRoute travelRoute, TravelGroup travelGroup,
            List<OrderTravellers> travellers, OrderDiscount policy, OrderDiscount code, OrderDiscount student,
            OrderRefound orderRefound) {
        BeanUtils.copyProperties(orderInfoRepository.findByOrderid(orderid), orderInfo);
        setOrderObjects(orderInfo, travelRoute, travelGroup, travellers, policy, code, student, orderRefound);
    }

    private void setOrderObjects(OrderInfo orderInfo, TravelRoute travelRoute, TravelGroup travelGroup,
            List<OrderTravellers> travellers, OrderDiscount policy, OrderDiscount code, OrderDiscount student,
            OrderRefound orderRefound) {

        Long orderid = orderInfo.getOrderid();
        Long routeid = orderInfo.getRouteid();
        Long groupid = orderInfo.getGroupid();

        try {
            BeanUtils.copyProperties(travelRouteRepository.findByRouteid(routeid), travelRoute);
            BeanUtils.copyProperties(travelGroupRepository.findByGroupid(groupid), travelGroup);
            for (OrderTravellers temp : orderTravellersRepository.findByOrderid(orderid)) {
                travellers.add(temp);
            }
            BeanUtils.copyProperties(orderDiscountRepository.findByOrderidAndTypeIn(orderid, POLICY), policy);
            BeanUtils.copyProperties(orderDiscountRepository.findByOrderidAndType(orderid, DiscountType.COUPON), code);
            BeanUtils.copyProperties(orderDiscountRepository.findByOrderidAndType(orderid, DiscountType.STUDENT),
                    student);
            BeanUtils.copyProperties(orderRefoundRepository.findByOrderidAndStatusIn(orderid, CONFIRM), orderRefound);
        } catch (Exception e) {
        }
    }

}
