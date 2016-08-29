package com.buterfleoge.whale.biz.order.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import com.buterfleoge.whale.type.entity.TravelGroup;
import com.buterfleoge.whale.type.protocol.order.GetBriefOrdersRequest;
import com.buterfleoge.whale.type.protocol.order.GetBriefOrdersResponse;
import com.buterfleoge.whale.type.protocol.order.GetOrderResponse;
import com.buterfleoge.whale.type.protocol.order.OrderRequest;

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

    @Autowired
    private BriefOrderHandler briefOrderHandler;

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

        Date endTime = DateUtils.addHours(orderInfo.getAddTime(), 2);
        response.setTimeLeft((endTime.getTime() - System.currentTimeMillis()) / 1000);
        response.setStatus(Status.OK);
    }

    @Override
    public void getBriefOrders(Long accountid, GetBriefOrdersRequest request, GetBriefOrdersResponse response) throws Exception {
        briefOrderHandler.getBriefOrders(accountid, request, response);
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

}
