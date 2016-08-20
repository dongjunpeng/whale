/**
 *
 */
package com.buterfleoge.whale.biz.order.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buterfleoge.whale.Constants.Status;
import com.buterfleoge.whale.biz.order.CreateOrderBiz;
import com.buterfleoge.whale.dao.OrderInfoRepository;
import com.buterfleoge.whale.type.OrderStatus;
import com.buterfleoge.whale.type.OrderStatusCategory;
import com.buterfleoge.whale.type.entity.OrderInfo;
import com.buterfleoge.whale.type.protocol.order.CreateOrderRequest;
import com.buterfleoge.whale.type.protocol.order.CreateOrderResponse;
import com.buterfleoge.whale.type.protocol.order.NewOrderRequest;
import com.buterfleoge.whale.type.protocol.order.NewOrderResponse;

/**
 * @author xiezhenzong
 *
 */
@Service("createOrderBiz")
public class CreateOrderBizImpl implements CreateOrderBiz {

    private static final Logger LOG = LoggerFactory.getLogger(CreateOrderBizImpl.class);

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Override
    public void newOrder(Long accountid, NewOrderRequest request, NewOrderResponse response) throws Exception {
        try {
            OrderInfo orderInfo = orderInfoRepository.findByAccountidAndRouteidAndGroupidAndStatusIn(accountid,
                    request.getRouteid(), request.getGroupid(), OrderStatusCategory.NO_ALLOW_NEW.getOrderStatuses());
            if (orderInfo != null) {
                response.setOrderid(orderInfo.getOrderid());
                return;
            }
        } catch (Exception e) {
            LOG.error("find order info failed, reqid: " + request.getReqid(), e);
            response.setStatus(Status.DB_ERROR);
            return;
        }
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setAccountid(accountid);
        orderInfo.setRouteid(request.getRouteid());
        orderInfo.setGroupid(request.getGroupid());
        orderInfo.setStatus(OrderStatus.NEW.value);
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
        // createOrderHandler.createOrder(accountid, request, response);
    }

}
