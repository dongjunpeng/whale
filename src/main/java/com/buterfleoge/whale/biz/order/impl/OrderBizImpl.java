package com.buterfleoge.whale.biz.order.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.buterfleoge.whale.Constants.Status;
import com.buterfleoge.whale.biz.order.OrderBiz;
import com.buterfleoge.whale.biz.order.OrderDiscountBiz;
import com.buterfleoge.whale.dao.AccountInfoRepository;
import com.buterfleoge.whale.dao.DiscountCodeRepository;
import com.buterfleoge.whale.dao.OrderDiscountRepository;
import com.buterfleoge.whale.dao.OrderHistoryRepository;
import com.buterfleoge.whale.dao.OrderInfoRepository;
import com.buterfleoge.whale.dao.OrderRefoundRepository;
import com.buterfleoge.whale.dao.OrderTravellersRepository;
import com.buterfleoge.whale.dao.TravelGroupRepository;
import com.buterfleoge.whale.dao.TravelRouteRepository;
import com.buterfleoge.whale.type.DiscountCodeStatus;
import com.buterfleoge.whale.type.DiscountType;
import com.buterfleoge.whale.type.GroupStatus;
import com.buterfleoge.whale.type.OrderStatus;
import com.buterfleoge.whale.type.OrderStatusCategory;
import com.buterfleoge.whale.type.entity.AccountInfo;
import com.buterfleoge.whale.type.entity.DiscountCode;
import com.buterfleoge.whale.type.entity.OrderDiscount;
import com.buterfleoge.whale.type.entity.OrderHistory;
import com.buterfleoge.whale.type.entity.OrderInfo;
import com.buterfleoge.whale.type.entity.OrderRefund;
import com.buterfleoge.whale.type.entity.OrderTravellers;
import com.buterfleoge.whale.type.entity.TravelGroup;
import com.buterfleoge.whale.type.entity.TravelRoute;
import com.buterfleoge.whale.type.protocol.order.GetBriefOrdersRequest;
import com.buterfleoge.whale.type.protocol.order.GetBriefOrdersResponse;
import com.buterfleoge.whale.type.protocol.order.GetOrderHistoryResponse;
import com.buterfleoge.whale.type.protocol.order.GetOrderResponse;
import com.buterfleoge.whale.type.protocol.order.OrderRequest;
import com.buterfleoge.whale.type.protocol.order.object.BriefOrder;

/**
 * @author Brent24
 *
 */
@Service("orderBiz")
public class OrderBizImpl implements OrderBiz {

    private static final Logger LOG = LoggerFactory.getLogger(OrderBizImpl.class);

    @Autowired
    private TravelRouteRepository travelRouteRepository;

    @Autowired
    private TravelGroupRepository travelGroupRepository;

    @Autowired
    private DiscountCodeRepository discountCodeRepository;

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Autowired
    private OrderTravellersRepository orderTravellersRepository;

    @Autowired
    private OrderDiscountRepository orderDiscountRepository;

    @Autowired
    private OrderRefoundRepository orderRefoundRepository;

    @Autowired
    private AccountInfoRepository accountInfoRepository;

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    @Autowired
    private OrderDiscountBiz orderDiscountBiz;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void getOrder(Long accountid, OrderRequest request, GetOrderResponse response) throws Exception {
        Long orderid = request.getOrderid();
        OrderInfo orderInfo = null;
        try {
            orderInfo = orderInfoRepository.findByOrderidAndAccountid(orderid, accountid);
            if (orderInfo == null) {
                throw new Exception("Can't find this order, orderid: " + orderid);
            }
            orderInfo = changeOrderInfoStatusIfTimeout(orderInfo);
            response.setOrderInfo(orderInfo);
        } catch (Exception e) {
            LOG.error("find order info failed, reqid: " + request.getReqid(), e);
            response.setStatus(Status.DB_ERROR);
            return;
        }

        Long routeid = orderInfo.getRouteid();
        Long groupid = orderInfo.getGroupid();

        try {
            TravelGroup travelGroup = travelGroupRepository.findOne(groupid);
            response.setTravelRoute(travelRouteRepository.findOne(routeid));
            response.setTravelGroup(travelGroup);
            response.setOrderTravellers(orderTravellersRepository.findByOrderidAndAccountid(orderid, accountid));

            List<OrderDiscount> discounts = orderDiscountRepository.findByOrderid(orderid);
            if (!CollectionUtils.isEmpty(discounts)) {
                response.setPolicy(orderDiscountBiz.filterDiscounts(discounts, DiscountType.getDiscountPolicy()));
                response.setCode(orderDiscountBiz.filterDiscounts(discounts, DiscountType.COUPON.value));
                response.setStudent(orderDiscountBiz.filterDiscounts(discounts, DiscountType.STUDENT.value));
            }
            response.setOrderRefound(orderRefoundRepository.findByOrderid(orderid));
            response.setTimeLeft(orderInfo.getTimeLeft());
            response.setDayLeft(travelGroup.getDayLeft());
            response.setStatus(Status.OK);
        } catch (Exception e) {
            LOG.error("find order info detail failed, reqid: " + request.getReqid(), e);
            response.setStatus(Status.DB_ERROR);
            return;
        }
    }

    @Override
    public void getBriefOrders(Long accountid, GetBriefOrdersRequest request, GetBriefOrdersResponse response) throws Exception {
        String reqid = request.getReqid();
        response.setCurrentOrderCount(countOrderInfoByStatus(accountid, OrderStatusCategory.CURRENT, reqid));
        response.setHistoryOrderCount(countOrderInfoByStatus(accountid, OrderStatusCategory.HISTORY, reqid));
        response.setAllOrderCount(countOrderInfoByStatus(accountid, OrderStatusCategory.VISIBLE, reqid));

        if (request.getOrderType() == null) { // 不传orderType则只获取订单数量
            return;
        }

        Set<Integer> statusSet = OrderStatusCategory.HELPER.valueOf(request.getOrderType()).getOrderStatuses();
        List<OrderInfo> orderInfos = getOrderInfosByStatus(accountid, statusSet, reqid);
        if (CollectionUtils.isEmpty(orderInfos)) {
            return;
        }
        Set<Long> orderids = getOrderids(orderInfos);
        Map<Long, TravelRoute> routes = getRoutes(orderInfos, reqid);
        Map<Long, TravelGroup> groups = getGroups(orderInfos, reqid);
        List<OrderDiscount> discounts = getOrderDiscounts(reqid, orderids);
        Map<Long, OrderRefund> orderRefounds = getOrderRefund(orderids, reqid);

        List<BriefOrder> briefOrders = new ArrayList<BriefOrder>(orderInfos.size());
        for (OrderInfo orderInfo : orderInfos) {
            try {
                orderInfo = changeOrderInfoStatusIfTimeout(orderInfo);
                if (statusSet.contains(orderInfo.getStatus())) {
                    TravelRoute travelRoute = routes.get(orderInfo.getRouteid());
                    TravelGroup travelGroup = groups.get(orderInfo.getGroupid());
                    OrderRefund orderRefund = orderRefounds.get(orderInfo.getOrderid());
                    briefOrders.add(createBriefOrder(orderInfo, travelRoute, travelGroup, discounts, orderRefund, reqid));
                }
            } catch (Exception e) {
                LOG.error("create brief order failed, reqid: " + reqid, e);
                continue;
            }
        }
        Collections.sort(briefOrders);
        response.setBriefOrders(briefOrders);
    }

    @Override
    public void getOrderHistory(Long accountid, OrderRequest request, GetOrderHistoryResponse response) throws Exception {
        Long orderid = request.getOrderid();
        OrderInfo orderInfo = orderInfoRepository.findByOrderidAndAccountid(orderid, accountid);
        if (orderInfo == null) {
            response.setStatus(Status.PARAM_ERROR);
            return;
        }
        List<OrderHistory> historys = orderHistoryRepository.findByOrderid(orderid);
        // 只是点了报名, 然后就超时了，那就显示一个完整的订单流程
        if (CollectionUtils.isEmpty(historys) || (historys.size() == 1 && historys.get(0).getType() == OrderStatus.TIMEOUT.value)) {
            response.addHistoryItem(OrderStatus.NEW.desc).addNextItem(OrderStatus.WAITING.desc).addNextItem(OrderStatus.PAYING.desc)
                    .addNextItem(OrderStatus.FINISH.desc);
            return;
        }
        boolean hasPaid = false;
        for (OrderHistory orderHistory : historys) {
            Integer type = orderHistory.getType();
            response.addHistoryItem(OrderStatus.HELPER.valueOf(type).desc);
            hasPaid |= type.equals(OrderStatus.PAID); // 只要有一个等于PAID
        }
        int status = orderInfo.getStatus().intValue();
        if (status == OrderStatus.WAITING.value) {
            response.addNextItem(OrderStatus.PAYING.desc);
            response.addNextItem(OrderStatus.PAID.desc);
            response.addNextItem(OrderStatus.FINISH.desc);
        } else if (status == OrderStatus.PAYING.value) {
            response.addNextItem(OrderStatus.PAID.desc);
            response.addNextItem(OrderStatus.FINISH.desc);
        } else if (status == OrderStatus.PAID.value) {
            response.addNextItem(OrderStatus.FINISH.desc);
        } else if (status == OrderStatus.REFUNDING.value) {
            response.addNextItem(OrderStatus.REFUNDED.desc);
        } else if (status == OrderStatus.CLOSED.value && hasPaid) {
            response.addNextItem(OrderStatus.REFUNDING.desc);
            response.addNextItem(OrderStatus.REFUNDED.desc);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderInfo changeOrderInfoStatusIfTimeout(OrderInfo orderInfo) throws Exception {
        Integer status = orderInfo.getStatus();
        if (!OrderStatusCategory.NO_ALLOW_NEW.getOrderStatuses().contains(status)) {
            return orderInfo;
        }
        if (DateUtils.addHours(orderInfo.getAddTime(), 2).getTime() < System.currentTimeMillis()) {
            Integer oldOrderStatus = orderInfo.getStatus();
            orderInfo.setStatus(OrderStatus.TIMEOUT.value);
            orderInfo.setModTime(new Date());
            orderInfo = orderInfoRepository.save(orderInfo);
            orderHistoryRepository.save(OrderHistory.newInstance(oldOrderStatus, orderInfo));

            TravelGroup group = travelGroupRepository.findOne(orderInfo.getGroupid());
            group.setStatus(GroupStatus.OPEN.value);
            group.setActualCount(group.getActualCount() - orderInfo.getCount());
            travelGroupRepository.save(group);

            OrderDiscount orderDiscount = orderDiscountRepository.findByOrderidAndType(orderInfo.getOrderid(), DiscountType.COUPON.value);
            if (orderDiscount != null) {
                DiscountCode discountCode = discountCodeRepository.findByDiscountCode(orderDiscount.getDiscountCode());
                discountCode.setStatus(DiscountCodeStatus.VERIFIED.value);
                discountCodeRepository.save(discountCode);
            }
        }
        return orderInfo;
    }

    public Integer countOrderInfoByStatus(Long accountid, OrderStatusCategory orderStatusType, String reqid) {
        try {
            return orderInfoRepository.countByAccountidAndStatusIn(accountid, orderStatusType.getOrderStatuses());
        } catch (Exception e) {
            LOG.error("count order info failed, reqid: " + reqid, e);
            return null;
        }
    }

    public List<OrderInfo> getOrderInfosByStatus(Long accountid, Set<Integer> statusSet, String reqid) {
        try {
            return orderInfoRepository.findByAccountidAndStatusIn(accountid, statusSet);
        } catch (Exception e) {
            LOG.error("find order info failed, reqid: " + reqid, e);
            return Collections.emptyList();
        }
    }

    private List<OrderDiscount> getOrderDiscounts(String reqid, Set<Long> orderids) {
        try {
            return orderDiscountRepository.findByOrderidIn(orderids);
        } catch (Exception e) {
            LOG.error("find order discount by orderids failed, reqid: " + reqid, e);
            return Collections.emptyList();
        }
    }

    private Set<Long> getOrderids(List<OrderInfo> orderInfos) {
        Set<Long> orderids = new HashSet<Long>(orderInfos.size());
        for (OrderInfo orderInfo : orderInfos) {
            orderids.add(orderInfo.getOrderid());
        }
        return orderids;
    }

    private Map<Long, TravelRoute> getRoutes(List<OrderInfo> orderInfos, String reqid) {
        try {
            Set<Long> routeids = getRouteids(orderInfos);
            Iterable<TravelRoute> routes = travelRouteRepository.findAll(routeids);
            Map<Long, TravelRoute> routeMap = new HashMap<Long, TravelRoute>(routeids.size());
            for (TravelRoute travelRoute : routes) {
                routeMap.put(travelRoute.getRouteid(), travelRoute);
            }
            return routeMap;
        } catch (Exception e) {
            LOG.error("find travel route failed, reqid: " + reqid, e);
            return Collections.emptyMap();
        }
    }

    private Set<Long> getRouteids(List<OrderInfo> orderInfos) {
        Set<Long> routeids = new HashSet<Long>(orderInfos.size());
        for (OrderInfo orderInfo : orderInfos) {
            routeids.add(orderInfo.getRouteid());
        }
        return routeids;
    }

    private Map<Long, TravelGroup> getGroups(List<OrderInfo> orderInfos, String reqid) {
        try {
            Set<Long> groupids = getGroupids(orderInfos);
            Iterable<TravelGroup> groups = travelGroupRepository.findAll(groupids);
            Map<Long, TravelGroup> groupMap = new HashMap<Long, TravelGroup>(groupids.size());
            for (TravelGroup travelGroup : groups) {
                groupMap.put(travelGroup.getGroupid(), travelGroup);
            }
            return groupMap;
        } catch (Exception e) {
            LOG.error("find travel route failed, reqid: " + reqid, e);
            return Collections.emptyMap();
        }
    }

    private Set<Long> getGroupids(List<OrderInfo> orderInfos) {
        Set<Long> groupids = new HashSet<Long>(orderInfos.size());
        for (OrderInfo orderInfo : orderInfos) {
            groupids.add(orderInfo.getGroupid());
        }
        return groupids;
    }

    private Map<Long, OrderRefund> getOrderRefund(Set<Long> orderids, String reqid) {
        try {
            Iterable<OrderRefund> orderRefunds = orderRefoundRepository.findByOrderidIn(orderids);
            Map<Long, OrderRefund> refundMap = new HashMap<Long, OrderRefund>(orderids.size());
            for (OrderRefund refund : orderRefunds) {
                refundMap.put(refund.getOrderid(), refund);
            }
            return refundMap;
        } catch (Exception e) {
            LOG.error("find order refunds failed, reqid: " + reqid, e);
            return Collections.emptyMap();
        }
    }

    private BriefOrder createBriefOrder(OrderInfo orderInfo, TravelRoute travelRoute, TravelGroup travelGroup,
            List<OrderDiscount> discounts, OrderRefund orderRefund, String reqid) {
        Long orderid = orderInfo.getOrderid();
        BriefOrder briefOrder = new BriefOrder();
        briefOrder.setOrderInfo(orderInfo);
        briefOrder.setTravelRoute(travelRoute);
        briefOrder.setTravelGroup(travelGroup);
        if (!CollectionUtils.isEmpty(discounts)) {
            briefOrder.setPolicy(orderDiscountBiz.filterDiscounts(discounts, orderid, DiscountType.getDiscountPolicy()));
            briefOrder.setCode(orderDiscountBiz.filterDiscounts(discounts, orderid, DiscountType.COUPON.value));
            briefOrder.setStudent(orderDiscountBiz.filterDiscounts(discounts, orderid, DiscountType.STUDENT.value));
        }
        briefOrder.setOrderRefound(orderRefund);
        briefOrder.setTimeLeft(orderInfo.getTimeLeft());
        briefOrder.setDayLeft(travelGroup.getDayLeft());
        briefOrder.setTravellerNames(getOrderTravellersNames(orderInfo, reqid));
        briefOrder.setOtherTravellers(getOtherTravellers(orderInfo, reqid));
        return briefOrder;
    }

    private Set<String> getOrderTravellersNames(OrderInfo orderInfo, String reqid) {
        try {
            List<OrderTravellers> orderTravellers = orderTravellersRepository.findByOrderid(orderInfo.getOrderid());
            Set<String> names = new HashSet<String>(orderTravellers.size());
            for (OrderTravellers tempTraveller : orderTravellers) {
                names.add(tempTraveller.getName());
            }
            return names;
        } catch (Exception e) {
            LOG.error("find order travellers failed, reqid: " + reqid, e);
            return Collections.emptySet();
        }
    }

    private Set<AccountInfo> getOtherTravellers(OrderInfo orderInfo, String reqid) {
        Long routeid = orderInfo.getRouteid();
        Long groupid = orderInfo.getGroupid();
        List<OrderInfo> orderInfos = orderInfoRepository.findByRouteidAndGroupidAndStatusIn(routeid, groupid,
                Collections.singleton(OrderStatus.PAID.value));
        if (CollectionUtils.isEmpty(orderInfos)) {
            return Collections.emptySet();
        }
        try {
            Set<Long> accountids = getAccountids(orderInfos);
            accountids.remove(orderInfo.getAccountid());
            Iterable<AccountInfo> accounts = accountInfoRepository.findAll(accountids);
            List<AccountInfo> otherTravellers = new ArrayList<AccountInfo>(accountids.size());
            for (AccountInfo account : accounts) {
                AccountInfo info = new AccountInfo(); // 避免其它信息泄露
                info.setNickname(account.getNickname());
                info.setAvatarUrl(account.getAvatarUrl());
                otherTravellers.add(account);
            }
            if (otherTravellers.size() > 5) {
                otherTravellers = otherTravellers.subList(0, 6);
            }
            return new HashSet<AccountInfo>(otherTravellers);
        } catch (Exception e) {
            LOG.error("find travel route failed, reqid: " + reqid, e);
            return Collections.emptySet();
        }
    }

    private Set<Long> getAccountids(List<OrderInfo> orderInfos) {
        Set<Long> accountids = new HashSet<Long>(orderInfos.size());
        for (OrderInfo orderInfo : orderInfos) {
            accountids.add(orderInfo.getAccountid());
        }
        return accountids;
    }

}
