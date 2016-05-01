package com.buterfleoge.whale.biz.order.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buterfleoge.whale.Constants.Status;
import com.buterfleoge.whale.biz.order.OrderBiz;
import com.buterfleoge.whale.dao.OrderDiscountRepository;
import com.buterfleoge.whale.dao.OrderInfoRepository;
import com.buterfleoge.whale.dao.OrderRefoundRepository;
import com.buterfleoge.whale.dao.OrderTravellersRepository;
import com.buterfleoge.whale.dao.TravelGroupRepository;
import com.buterfleoge.whale.dao.TravelRouteRepository;
import com.buterfleoge.whale.type.Order;
import com.buterfleoge.whale.type.entity.OrderDiscount;
import com.buterfleoge.whale.type.entity.OrderInfo;
import com.buterfleoge.whale.type.entity.OrderRefound;
import com.buterfleoge.whale.type.entity.OrderTravellers;
import com.buterfleoge.whale.type.entity.TravelGroup;
import com.buterfleoge.whale.type.entity.TravelRoute;
import com.buterfleoge.whale.type.enums.OrderStatus;
import com.buterfleoge.whale.type.protocol.order.GetOrdersRequest;
import com.buterfleoge.whale.type.protocol.order.GetOrdersResponse;

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
    }

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Autowired
    private TravelRouteRepository travelRouteRepository;

    @Autowired
    private TravelGroupRepository travelGroupRepository;

    @Autowired
    private OrderTravellersRepository orderTravellersRepository;

    @Autowired
    private OrderDiscountRepository orderDiscountRepository;

    @Autowired
    private OrderRefoundRepository orderRefoundRepository;

    @Override
    public void getOrder(GetOrdersRequest request, GetOrdersResponse response) throws Exception {

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

}
