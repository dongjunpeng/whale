package com.buterfleoge.whale.biz.order.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.buterfleoge.whale.Constants.Status;
import com.buterfleoge.whale.biz.order.PayOrderBiz;
import com.buterfleoge.whale.dao.OrderAlipayRepository;
import com.buterfleoge.whale.dao.OrderInfoRepository;
import com.buterfleoge.whale.dao.TravelGroupRepository;
import com.buterfleoge.whale.dao.TravelRouteRepository;
import com.buterfleoge.whale.service.AlipayService;
import com.buterfleoge.whale.service.alipay.AlipayConfig;
import com.buterfleoge.whale.service.alipay.protocol.AlipayCallbackRequest;
import com.buterfleoge.whale.service.alipay.protocol.AlipayCreateNotifyRequest;
import com.buterfleoge.whale.service.alipay.protocol.AlipayReturnRequest;
import com.buterfleoge.whale.type.AlipayRefundStatus;
import com.buterfleoge.whale.type.AlipayTradeStatus;
import com.buterfleoge.whale.type.OrderStatus;
import com.buterfleoge.whale.type.entity.OrderAlipay;
import com.buterfleoge.whale.type.entity.OrderInfo;
import com.buterfleoge.whale.type.entity.TravelGroup;
import com.buterfleoge.whale.type.entity.TravelRoute;
import com.buterfleoge.whale.type.protocol.Error;
import com.buterfleoge.whale.type.protocol.Response;
import com.buterfleoge.whale.type.protocol.order.PayOrderByAlipayResponse;
import com.buterfleoge.whale.type.protocol.order.PayOrderRequest;

/**
 * @author xiezhenzong
 *
 */
@Service("payOrderBiz")
public class PayOrderBizImpl implements PayOrderBiz {

    private static final Logger LOG = LoggerFactory.getLogger(PayOrderBizImpl.class);

    @Autowired
    private TravelRouteRepository travelRouteRepository;

    @Autowired
    private TravelGroupRepository travelGroupRepository;

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Autowired
    private OrderAlipayRepository orderAlipayRepository;

    @Autowired
    private AlipayService alipayService;

    @Autowired
    private AlipayConfig alipayConfig;

    @Override
    public void payOrder(Long accountid, PayOrderRequest request, PayOrderByAlipayResponse response) throws Exception {
        Long orderid = request.getOrderid();
        OrderInfo order = orderInfoRepository.findByOrderidAndAccountid(orderid, accountid);
        if (order == null) {
            throw new Exception("订单不存在");
        }
        TravelRoute route = travelRouteRepository.findOne(order.getRouteid());
        TravelGroup group = travelGroupRepository.findOne(order.getGroupid());

        order.setStatus(OrderStatus.PAYING.value);
        orderInfoRepository.save(order);

        StringBuilder subject = new StringBuilder(route.getName());
        subject.append("(").append(group.getTitle()).append(")");

        String alipayForm = alipayService.createDirectPay(orderid, order.getActualPrice(), group.getPrice(), subject.toString());
        response.setAlipayFrom(alipayForm);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void handlePayReturn(Long accountid, Map<String, String[]> paramsMap, AlipayReturnRequest request, Response response)
            throws Exception {
        Long orderid = request.getOut_trade_no();
        OrderInfo orderInfo = orderInfoRepository.findByOrderidAndAccountid(orderid, accountid);
        if (!verify(paramsMap, orderInfo, request, response)) {
            return;
        }

        try {
            List<OrderAlipay> orderAlipays = orderAlipayRepository.findByOrderidAndTradeNo(orderid, request.getTrade_no());
            OrderAlipay orderAlipay = createOrderAlipay(request);
            if (CollectionUtils.isEmpty(orderAlipays)) { // 未收到支付宝的异步通知，进行同步处理
                orderInfo.setStatus(OrderStatus.PAID.value);
                orderInfo.setModTime(new Date());
                orderInfoRepository.save(orderInfo);
            }
            orderAlipayRepository.save(orderAlipay);
        } catch (Exception e) {
            LOG.error("save order info and order alipay failed, reqid: " + request.getReqid(), e);
            response.setStatus(Status.DB_ERROR);
            response.addError(new Error("系统错误"));
            throw e;
        }
    }

    @Override
    public void handlePayNotify(Long accountid, Map<String, String[]> paramsMap, AlipayCreateNotifyRequest request, Response response)
            throws Exception {
        Long orderid = request.getOut_trade_no();
        OrderInfo orderInfo = orderInfoRepository.findByOrderidAndAccountid(orderid, accountid);
        if (!verify(paramsMap, orderInfo, request, response)) {
            return;
        }

        OrderAlipay orderAlipay = createOrderAlipay(request);
        orderAlipay.setGmtCreate(request.getGmt_create());
        orderAlipay.setGmtPayment(request.getGmt_payment());
        orderAlipay.setGmtClose(request.getGmt_close());
        orderAlipay.setGmtRefund(request.getGmt_refund());
        orderAlipay.setRefundStatus(request.getRefund_status());

        try {
            List<OrderAlipay> orderAlipays = orderAlipayRepository.findByOrderidAndTradeNo(orderid, request.getTrade_no());
            if (CollectionUtils.isEmpty(orderAlipays)) { // 还未收到同步通知或者异步通知
                orderInfo.setStatus(OrderStatus.PAID.value);
                orderInfoRepository.save(orderInfo);
            } else if (request.getRefund_status() != null && AlipayRefundStatus.REFUND_SUCCESS.equals(request.getRefund_status())
                    && request.getGmt_refund() != null) {
                orderInfo.setStatus(OrderStatus.REFUNDING.value);
                orderInfoRepository.save(orderInfo);
            }
            orderAlipayRepository.save(orderAlipay);
        } catch (Exception e) {
            LOG.error("save order info and order alipay failed, reqid: " + request.getReqid(), e);
            response.setStatus(Status.DB_ERROR);
            response.addError(new Error("系统错误"));
            throw e;
        }
    }

    private boolean verify(Map<String, String[]> paramsMap, OrderInfo orderInfo, AlipayCallbackRequest request, Response response) {
        if (orderInfo == null) {
            response.setStatus(Status.PARAM_ERROR);
            response.addError(new Error("您访问的订单不存在"));
            return false;
        }
        if (!isValid(request, orderInfo)) {
            response.setStatus(Status.PARAM_ERROR);
            response.addError(new Error("支付宝返回参数错误"));
            return false;
        }
        boolean isVerify = alipayService.verify(paramsMap);
        if (!isVerify) {
            response.setStatus(Status.BIZ_ERROR);
            response.addError(new Error("支付参数异常"));
            return false;
        }
        return true;
    }

    private OrderAlipay createOrderAlipay(AlipayCallbackRequest request) {
        OrderAlipay orderAlipay = new OrderAlipay();
        orderAlipay.setOrderid(request.getOut_trade_no());
        orderAlipay.setTradeNo(request.getTrade_no());
        orderAlipay.setTradeStatus(request.getTrade_status());
        orderAlipay.setNotifyId(request.getNotify_id());
        orderAlipay.setNotifyType(request.getNotify_type());
        orderAlipay.setNotifyTime(request.getNotify_time());
        orderAlipay.setTotalFee(request.getTotal_fee());
        orderAlipay.setSellerId(request.getSeller_id());
        orderAlipay.setBuyerId(request.getBuyer_id());
        orderAlipay.setBuyerEmail(request.getBuyer_email());
        orderAlipay.setAddTime(new Date());
        return orderAlipay;
    }

    private boolean isValid(AlipayCallbackRequest request, OrderInfo orderInfo) {
        AlipayTradeStatus tradeStatus = request.getTrade_status();
        return alipayConfig.seller_id.equals(request.getSeller_id()) && orderInfo.getActualPrice().equals(request.getTotal_fee())
                && (tradeStatus.equals(AlipayTradeStatus.TRADE_SUCCESS) || tradeStatus.equals(AlipayTradeStatus.TRADE_FINISHED));
    }

}
