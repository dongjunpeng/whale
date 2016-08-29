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
import org.springframework.util.CollectionUtils;

import com.buterfleoge.whale.Constants.Status;
import com.buterfleoge.whale.dao.AccountBindingRepository;
import com.buterfleoge.whale.dao.OrderInfoRepository;
import com.buterfleoge.whale.dao.OrderTravellersRepository;
import com.buterfleoge.whale.dao.TravelGroupRepository;
import com.buterfleoge.whale.dao.TravelRouteRepository;
import com.buterfleoge.whale.type.OrderStatusCategory;
import com.buterfleoge.whale.type.entity.AccountBinding;
import com.buterfleoge.whale.type.entity.OrderInfo;
import com.buterfleoge.whale.type.entity.OrderTravellers;
import com.buterfleoge.whale.type.entity.TravelGroup;
import com.buterfleoge.whale.type.entity.TravelRoute;
import com.buterfleoge.whale.type.protocol.order.GetBriefOrdersRequest;
import com.buterfleoge.whale.type.protocol.order.GetBriefOrdersResponse;
import com.buterfleoge.whale.type.protocol.order.object.BriefOrder;

/**
 *
 * @author xiezhenzong
 *
 */
@Service("briefOrderHandler")
public class BriefOrderHandler {

    private static final Logger LOG = LoggerFactory.getLogger(BriefOrderHandler.class);

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Autowired
    private OrderTravellersRepository orderTravellersRepository;

    @Autowired
    private AccountBindingRepository accountSettingRepository;

    @Autowired
    private TravelRouteRepository travelRouteRepository;

    @Autowired
    private TravelGroupRepository travelGroupRepository;

    public Integer countOrderInfoByStatus(Long accountid, OrderStatusCategory orderStatusType, String reqid) {
        try {
            return orderInfoRepository.countByAccountidAndStatusIn(accountid, orderStatusType.getOrderStatuses());
        } catch (Exception e) {
            LOG.error("count order info failed, reqid: " + reqid, e);
            return null;
        }
    }

    public void getBriefOrders(Long accountid, GetBriefOrdersRequest request, GetBriefOrdersResponse response) throws Exception {
        String reqid = request.getReqid();
        response.setCurrentOrderCount(countOrderInfoByStatus(accountid, OrderStatusCategory.CURRENT, reqid));
        response.setHistoryOrderCount(countOrderInfoByStatus(accountid, OrderStatusCategory.HISTORY, reqid));
        response.setAllOrderCount(countOrderInfoByStatus(accountid, OrderStatusCategory.VISIBLE, reqid));

        Set<Integer> statusSet = request.getOrderType() != null
                ? OrderStatusCategory.HELPER.valueOf(request.getOrderType()).getOrderStatuses()
                        : OrderStatusCategory.VISIBLE.getOrderStatuses();

                List<OrderInfo> orderInfos = null;
                try {
                    orderInfos = orderInfoRepository.findByAccountidAndStatusIn(accountid, statusSet);
                } catch (Exception e) {
                    LOG.error("find order info failed, reqid: " + request.getReqid(), e);
                    response.setStatus(Status.DB_ERROR);
                    return;
                }
                if (CollectionUtils.isEmpty(orderInfos)) {
                    return;
                }
                List<BriefOrder> briefOrders = new ArrayList<BriefOrder>(orderInfos.size());
                Map<Long, TravelRoute> routes = getRoutes(getRouteids(orderInfos), reqid);
                Map<Long, TravelGroup> groups = getGroups(getGroupids(orderInfos), reqid);

                for (OrderInfo orderInfo : orderInfos) {
                    // orderInfo = changeOrderInfoStatusIfTimeout(orderInfo, reqid);
                    if (statusSet.contains(orderInfo.getStatus())) {
                        TravelRoute travelRoute = routes.get(orderInfo.getRouteid());
                        TravelGroup travelGroup = groups.get(orderInfo.getGroupid());
                        briefOrders.add(createBriefOrder(orderInfo, travelRoute, travelGroup, reqid));
                    }
                }
                Collections.sort(briefOrders);
                response.setBriefOrders(briefOrders);
    }

    private Map<Long, TravelRoute> getRoutes(Set<Long> routeids, String reqid) {
        try {
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

    private Map<Long, TravelGroup> getGroups(Set<Long> groupids, String reqid) {
        try {
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
        long minuteCount = DateUtils.addHours(orderInfo.getAddTime(), 2).getTime() - System.currentTimeMillis();
        briefOrder.setMinuteCount(minuteCount / DateUtils.MILLIS_PER_MINUTE);

        setOrderTravellersNamesAndAvatars(briefOrder, orderInfo, reqid);

        if (travelRoute != null) {
            briefOrder.setRouteid(travelRoute.getRouteid());
            briefOrder.setName(travelRoute.getName());
            briefOrder.setTitle(travelRoute.getTitle());
            briefOrder.setHeadImg(travelRoute.getHeadImg());
        }

        if (travelGroup != null) {
            briefOrder.setStartDate(travelGroup.getStartDate());
            briefOrder.setEndDate(travelGroup.getEndDate());
            briefOrder.setWxQrCode(travelGroup.getWxQrcode());
            long daycount = travelGroup.getStartDate().getTime() - System.currentTimeMillis();
            briefOrder.setDayCount(daycount / DateUtils.MILLIS_PER_DAY + (daycount % DateUtils.MILLIS_PER_DAY == 0 ? 0 : 1));
        }
        return briefOrder;
    }

    private void setOrderTravellersNamesAndAvatars(BriefOrder briefOrder, OrderInfo orderInfo, String reqid) {
        try {
            List<OrderTravellers> orderTravellers = orderTravellersRepository.findByOrderid(orderInfo.getOrderid());
            if (!CollectionUtils.isEmpty(orderTravellers)) {
                Set<Long> accountids = getAccountid(orderTravellers);
                Map<Long, AccountBinding> accountSettingMap = getAccountSettings(accountids, reqid);

                Set<String> avatars = new HashSet<String>(orderTravellers.size() + 2);
                Set<String> names = new HashSet<String>(orderTravellers.size());
                for (OrderTravellers tempTraveller : orderTravellers) {
                    names.add(tempTraveller.getName());
                    // FIXME
                    // avatars.add(getAvatartUrl(tempTraveller.getAccountid(),
                    // accountSettingMap));
                }
                // 添加领队头像
                // avatars.addAll();
                briefOrder.setNames(names);
                briefOrder.setAvatars(avatars);
            }
        } catch (Exception e) {
            LOG.error("find order travellers failed, reqid: " + reqid, e);
        }
    }

    private Set<Long> getAccountid(List<OrderTravellers> travellers) {
        Set<Long> accountids = new HashSet<Long>(travellers.size());
        for (OrderTravellers traveller : travellers) {
            accountids.add(traveller.getAccountid());
        }
        return accountids;
    }

    private Map<Long, AccountBinding> getAccountSettings(Set<Long> accountids, String reqid) {
        try {
            Iterable<AccountBinding> accountSettings = accountSettingRepository.findAll(accountids);
            Map<Long, AccountBinding> accountSettingMap = new HashMap<Long, AccountBinding>();
            for (AccountBinding accountSetting : accountSettings) {
                accountSettingMap.put(accountSetting.getAccountid(), accountSetting);
            }
            return accountSettingMap;
        } catch (Exception e) {
            LOG.error("find account setting failed, reqid: " + reqid, e);
            return Collections.emptyMap();
        }
    }

    // FIXME
    // private String getAvatartUrl(Long accountid, Map<Long, AccountBinding>
    // accountSettings) {
    // AccountBinding accountSetting = accountSettings.get(accountid);
    // return accountSetting != null && accountSetting.getAvatarUrl() != null ?
    // accountSetting.getAvatarUrl() : "";
    // }

}
