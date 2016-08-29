package com.buterfleoge.whale.biz.order.impl;

import java.util.ArrayList;
import java.util.Collections;
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
import com.buterfleoge.whale.dao.OrderDiscountRepository;
import com.buterfleoge.whale.dao.OrderInfoRepository;
import com.buterfleoge.whale.dao.OrderRefoundRepository;
import com.buterfleoge.whale.dao.OrderTravellersRepository;
import com.buterfleoge.whale.dao.TravelGroupRepository;
import com.buterfleoge.whale.dao.TravelRouteRepository;
import com.buterfleoge.whale.type.DiscountType;
import com.buterfleoge.whale.type.OrderStatus;
import com.buterfleoge.whale.type.OrderStatusCategory;
import com.buterfleoge.whale.type.RefundStatus;
import com.buterfleoge.whale.type.entity.OrderInfo;
import com.buterfleoge.whale.type.entity.OrderTravellers;
import com.buterfleoge.whale.type.entity.TravelGroup;
import com.buterfleoge.whale.type.entity.TravelRoute;
import com.buterfleoge.whale.type.protocol.order.GetBriefOrdersRequest;
import com.buterfleoge.whale.type.protocol.order.GetBriefOrdersResponse;
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

    // 领队头像
    private static final Set<String> LEADERS = new HashSet<String>();

    // 退款状态
    private static final Set<Integer> CONFIRM = new HashSet<Integer>();

    static {
        // 领队头像
        LEADERS.add("/imgs/1.jpg");
        LEADERS.add("/imgs/2.jpg");

        // 退款已确认状态
        CONFIRM.add(RefundStatus.CONFIRMED.value);
        CONFIRM.add(RefundStatus.REFOUNDED.value);
    }

    @Autowired
    private TravelRouteRepository travelRouteRepository;

    @Autowired
    private TravelGroupRepository travelGroupRepository;

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Autowired
    private OrderTravellersRepository orderTravellersRepository;

    @Autowired
    private OrderDiscountRepository orderDiscountRepository;

    @Autowired
    private OrderRefoundRepository orderRefoundRepository;

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
            response.setTravelRoute(travelRouteRepository.findOne(routeid));
            response.setTravelGroup(travelGroupRepository.findOne(groupid));
            response.setOrderTravellers(orderTravellersRepository.findByOrderidAndAccountid(orderid, accountid));
            response.setPolicy(orderDiscountRepository.findByOrderidAndTypeIn(orderid, DiscountType.getDiscountPolicy()));
            response.setCode(orderDiscountRepository.findByOrderidAndType(orderid, DiscountType.COUPON.value));
            response.setStudent(orderDiscountRepository.findByOrderidAndType(orderid, DiscountType.STUDENT.value));
            response.setOrderRefound(orderRefoundRepository.findByOrderidAndStatusIn(orderid, CONFIRM));
        } catch (Exception e) {
            LOG.error("find order info detail failed, reqid: " + request.getReqid(), e);
            response.setStatus(Status.DB_ERROR);
            return;
        }
        response.setTimeLeft(orderInfo.getTimeLeft());
        response.setStatus(Status.OK);
    }

    @Override
    public void getBriefOrders(Long accountid, GetBriefOrdersRequest request, GetBriefOrdersResponse response) throws Exception {
        String reqid = request.getReqid();
        response.setCurrentOrderCount(countOrderInfoByStatus(accountid, OrderStatusCategory.CURRENT, reqid));
        response.setHistoryOrderCount(countOrderInfoByStatus(accountid, OrderStatusCategory.HISTORY, reqid));
        response.setAllOrderCount(countOrderInfoByStatus(accountid, OrderStatusCategory.VISIBLE, reqid));

        Set<Integer> statusSet = request.getOrderType() != null
                ? OrderStatusCategory.HELPER.valueOf(request.getOrderType()).getOrderStatuses()
                : OrderStatusCategory.VISIBLE.getOrderStatuses();
        List<OrderInfo> orderInfos = getOrderInfosByStatus(accountid, statusSet, reqid);
        if (CollectionUtils.isEmpty(orderInfos)) {
            return;
        }
        List<BriefOrder> briefOrders = new ArrayList<BriefOrder>(orderInfos.size());
        Map<Long, TravelRoute> routes = getRoutes(orderInfos, reqid);
        Map<Long, TravelGroup> groups = getGroups(orderInfos, reqid);

        for (OrderInfo orderInfo : orderInfos) {
            try {
                orderInfo = changeOrderInfoStatusIfTimeout(orderInfo);
                if (statusSet.contains(orderInfo.getStatus())) {
                    TravelRoute travelRoute = routes.get(orderInfo.getRouteid());
                    TravelGroup travelGroup = groups.get(orderInfo.getGroupid());
                    briefOrders.add(createBriefOrder(orderInfo, travelRoute, travelGroup, reqid));
                }
            } catch (Exception e) {
                LOG.error("create brief order failed, reqid: " + reqid, e);
                continue;
            }
        }
        Collections.sort(briefOrders);
        response.setBriefOrders(briefOrders);
    }

    @Transactional(rollbackFor = Exception.class)
    public OrderInfo changeOrderInfoStatusIfTimeout(OrderInfo orderInfo) {
        Integer status = orderInfo.getStatus();
        if (status == OrderStatus.NEW.value || status == OrderStatus.WAITING.value || status == OrderStatus.PAYING.value) {
            if (DateUtils.addHours(orderInfo.getAddTime(), 2).getTime() < System.currentTimeMillis()) {
                orderInfo.setStatus(OrderStatus.TIMEOUT.value);
                orderInfo = orderInfoRepository.save(orderInfo);

                TravelGroup group = travelGroupRepository.findOne(orderInfo.getGroupid());
                group.setActualCount(group.getActualCount() - orderInfo.getCount());
                travelGroupRepository.save(group);
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

    private Map<Long, TravelRoute> getRoutes(List<OrderInfo> orderInfos, String reqid) {
        try {
            Set<Long> routeids = getRouteids(orderInfos);
            Iterable<TravelRoute> routes = travelRouteRepository.findAll(routeids);
            Map<Long, TravelRoute> routeMap = new HashMap<Long, TravelRoute>();
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
            Map<Long, TravelGroup> groupMap = new HashMap<Long, TravelGroup>();
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

    private BriefOrder createBriefOrder(OrderInfo orderInfo, TravelRoute travelRoute, TravelGroup travelGroup, String reqid) {
        BriefOrder briefOrder = new BriefOrder();
        briefOrder.setOrderid(orderInfo.getOrderid());
        briefOrder.setStatus(orderInfo.getStatus());
        briefOrder.setActualPrice(orderInfo.getActualPrice());
        briefOrder.setTimeLeft(orderInfo.getTimeLeft());
        briefOrder.setTravellerNames(getOrderTravellersNames(orderInfo, reqid));

        briefOrder.setRouteid(travelRoute.getRouteid());
        briefOrder.setName(travelRoute.getName());
        briefOrder.setTitle(travelRoute.getTitle());
        briefOrder.setHeadImg(travelRoute.getHeadImg());

        briefOrder.setStartDate(travelGroup.getStartDate());
        briefOrder.setEndDate(travelGroup.getEndDate());
        briefOrder.setWxQrCode(travelGroup.getWxQrcode());
        briefOrder.setDayLeft(travelGroup.getDayLeft());

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

}
