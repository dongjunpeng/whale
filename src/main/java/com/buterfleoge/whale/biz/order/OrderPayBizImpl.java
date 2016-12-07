package com.buterfleoge.whale.biz.order;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.buterfleoge.whale.Constants.DefaultValue;
import com.buterfleoge.whale.Constants.Pattern;
import com.buterfleoge.whale.Constants.Status;
import com.buterfleoge.whale.Utils;
import com.buterfleoge.whale.biz.OrderPayBiz;
import com.buterfleoge.whale.dao.OrderAlipayRepository;
import com.buterfleoge.whale.dao.OrderHistoryRepository;
import com.buterfleoge.whale.dao.OrderInfoRepository;
import com.buterfleoge.whale.dao.OrderWxpayRepository;
import com.buterfleoge.whale.dao.TravelGroupRepository;
import com.buterfleoge.whale.dao.TravelRouteRepository;
import com.buterfleoge.whale.service.AlipayService;
import com.buterfleoge.whale.service.WeixinPayService;
import com.buterfleoge.whale.service.alipay.AlipayConfig;
import com.buterfleoge.whale.service.alipay.protocol.AlipayCallbackRequest;
import com.buterfleoge.whale.service.alipay.protocol.AlipayCreateNotifyRequest;
import com.buterfleoge.whale.service.alipay.protocol.AlipayCreateReturnRequest;
import com.buterfleoge.whale.service.weixin.protocol.WxOrderQueryResponse;
import com.buterfleoge.whale.service.weixin.protocol.WxOrderQueryResponse.TradeStatusContants;
import com.buterfleoge.whale.service.weixin.protocol.WxPayJsapiNotifyRequest;
import com.buterfleoge.whale.service.weixin.protocol.WxPayJsapiNotifyResponse;
import com.buterfleoge.whale.service.weixin.protocol.WxUnifiedOrderResponse;
import com.buterfleoge.whale.type.AlipayRefundStatus;
import com.buterfleoge.whale.type.AlipayTradeStatus;
import com.buterfleoge.whale.type.OrderStatus;
import com.buterfleoge.whale.type.PayType;
import com.buterfleoge.whale.type.WxCode;
import com.buterfleoge.whale.type.entity.OrderAlipay;
import com.buterfleoge.whale.type.entity.OrderHistory;
import com.buterfleoge.whale.type.entity.OrderInfo;
import com.buterfleoge.whale.type.entity.OrderWxpay;
import com.buterfleoge.whale.type.entity.TravelGroup;
import com.buterfleoge.whale.type.entity.TravelRoute;
import com.buterfleoge.whale.type.entity.converter.PriceConverter;
import com.buterfleoge.whale.type.protocol.Error;
import com.buterfleoge.whale.type.protocol.Response;
import com.buterfleoge.whale.type.protocol.order.OrderPayResultRequest;
import com.buterfleoge.whale.type.protocol.order.OrderPayResultResponse;
import com.buterfleoge.whale.type.protocol.order.PayOrderByAlipayResponse;
import com.buterfleoge.whale.type.protocol.order.PayOrderRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author xiezhenzong
 *
 */
@Service("orderPayBiz")
public class OrderPayBizImpl implements OrderPayBiz {

    private static final Logger LOG = LoggerFactory.getLogger(OrderPayBizImpl.class);

    @Autowired
    private TravelRouteRepository travelRouteRepository;

    @Autowired
    private TravelGroupRepository travelGroupRepository;

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Autowired
    private OrderAlipayRepository orderAlipayRepository;

    @Autowired
    private OrderWxpayRepository orderWxpayRepository;

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    @Autowired
    private AlipayService alipayService;

    @Autowired
    private AlipayConfig alipayConfig;

    @Autowired
    private WeixinPayService weixinPayService;

    @Value("${wx.pay.key}")
    private String key;

    @Override
    public void payOrder(Long accountid, PayOrderRequest request, PayOrderByAlipayResponse response) throws Exception {
        Long orderid = request.getOrderid();
        OrderInfo order = orderInfoRepository.findByOrderidAndAccountid(orderid, accountid);
        if (order == null) {
            throw new Exception("订单不存在");
        }
        if (order.getStatus() != OrderStatus.WAITING.value && order.getStatus() != OrderStatus.PAYING.value) {
            throw new IllegalStateException("订单状态错误");
        }

        TravelRoute route = travelRouteRepository.findOne(order.getRouteid());
        TravelGroup group = travelGroupRepository.findOne(order.getGroupid());
        String productName = Utils.getProductName(route, group);

        order.setStatus(OrderStatus.PAYING.value);
        orderInfoRepository.save(order);

        if (request.getPayType() == PayType.ALIPAY.value) {
            Map<String, String> alipayFormModel = alipayService.createDirectPay(orderid, order.getActualPrice(), group.getPrice(),
                    productName);
            response.setAlipayFrom(alipayFormModel);
            orderHistoryRepository.save(OrderHistory.newInstance(orderid, order.getStatus(), "支付宝支付"));
        } else {
            WxUnifiedOrderResponse unifiedOrderResponse = weixinPayService.unifiedOrder(orderid, order, productName, request.getIp());
            Map<String, Object> wxJsapiModel = weixinPayService.geneJsapiParam(orderid, order.getActualPrice(),
                    unifiedOrderResponse.getPrepay_id());
            response.setWxJsapiModel(wxJsapiModel);
            orderHistoryRepository.save(OrderHistory.newInstance(orderid, order.getStatus(), "微信公众号H5内支付"));
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void handleAlipayReturn(Long accountid, Map<String, String[]> paramsMap, AlipayCreateReturnRequest request, Response response)
            throws Exception {
        Long orderid = request.getOut_trade_no();
        OrderInfo orderInfo = orderInfoRepository.findByOrderidAndAccountid(orderid, accountid);
        if (!verify(paramsMap, orderInfo, request, response)) {
            return;
        }
        Integer oldOrderStatus = orderInfo.getStatus();
        try {
            List<OrderAlipay> orderAlipays = orderAlipayRepository.findByOrderidAndTradeNo(orderid, request.getTrade_no());
            OrderAlipay orderAlipay = createOrderAlipay(request);
            if (CollectionUtils.isEmpty(orderAlipays)) { // 未收到支付宝的异步通知，进行同步处理
                if (AlipayTradeStatus.TRADE_SUCCESS.equals(request.getTrade_status())
                        || AlipayTradeStatus.TRADE_FINISHED.equals(request.getTrade_status())) {
                    orderInfo.setStatus(OrderStatus.PAID.value);
                    orderInfo.setModTime(new Date());
                    orderInfoRepository.save(orderInfo);
                    orderHistoryRepository.save(OrderHistory.newInstance(oldOrderStatus, orderInfo));
                }
            }
            orderAlipayRepository.save(orderAlipay);
        } catch (Exception e) {
            LOG.error("save order info and order alipay failed, reqid: " + request.getReqid(), e);
            response.setStatus(Status.DB_ERROR);
            response.addError(new Error("系统错误"));
            throw e;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void handleAlipayNotify(Map<String, String[]> paramsMap, AlipayCreateNotifyRequest request, Response response)
            throws Exception {
        Long orderid = request.getOut_trade_no();
        OrderInfo orderInfo = orderInfoRepository.findOne(orderid);
        if (!verify(paramsMap, orderInfo, request, response)) {
            return;
        }
        Integer oldOrderStatus = orderInfo.getStatus();
        OrderAlipay orderAlipay = createOrderAlipay(request);
        orderAlipay.setGmtCreate(request.getGmt_create());
        orderAlipay.setGmtPayment(request.getGmt_payment());
        orderAlipay.setGmtClose(request.getGmt_close());
        orderAlipay.setGmtRefund(request.getGmt_refund());
        orderAlipay.setRefundStatus(request.getRefund_status());

        try {
            List<OrderAlipay> orderAlipays = orderAlipayRepository.findByOrderidAndTradeNo(orderid, request.getTrade_no());
            if (CollectionUtils.isEmpty(orderAlipays)) { // 还未收到同步通知或者异步通知
                if (AlipayTradeStatus.TRADE_SUCCESS.equals(request.getTrade_status())
                        || AlipayTradeStatus.TRADE_FINISHED.equals(request.getTrade_status())) {
                    orderInfo.setStatus(OrderStatus.PAID.value);
                    orderInfo.setModTime(new Date());
                    orderInfoRepository.save(orderInfo);
                    orderHistoryRepository.save(OrderHistory.newInstance(oldOrderStatus, orderInfo));
                }
            } else if (request.getRefund_status() != null && AlipayRefundStatus.REFUND_SUCCESS.equals(request.getRefund_status())
                    && request.getGmt_refund() != null) {
                orderInfo.setStatus(OrderStatus.REFUNDED.value);
                orderInfo.setModTime(new Date());
                orderInfoRepository.save(orderInfo);
                orderHistoryRepository.save(OrderHistory.newInstance(oldOrderStatus, orderInfo));
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void handleWxpayNotify(WxPayJsapiNotifyRequest request, WxPayJsapiNotifyResponse response) throws Exception {
        Map<String, Object> param = objectToMap(request);
        if (!request.isReturnCodeSuccess() || !validateRequestSign(param, request.getSign())) { // return_code不为SUCCESS或者sign有问题
            response.setReturn_code(WxCode.FAIL.code);
            response.setReturn_msg("微信请求参数问题");
            return;
        }
        Long orderid = Long.parseLong(request.getOut_trade_no());
        OrderInfo orderInfo = orderInfoRepository.findOne(orderid);
        if (orderInfo != null && orderInfo.getStatus() == OrderStatus.PAYING.value && request.isResultCodeSuccess()) {
            orderInfo.setStatus(OrderStatus.PAID.value);
            orderInfo.setModTime(new Date());
            orderInfoRepository.save(orderInfo);
            orderHistoryRepository.save(OrderHistory.newInstance(OrderStatus.PAYING, orderInfo));
        }

        OrderWxpay orderWxpay = new OrderWxpay();
        orderWxpay.setOrderid(orderid);
        orderWxpay.setReturnCode(WxCode.parse(request.getReturn_code()));
        orderWxpay.setResultCode(WxCode.parse(request.getResult_code()));
        orderWxpay.setOpenid(request.getOpenid());
        orderWxpay.setTotalFee(PriceConverter.fenToYuan(Long.valueOf(request.getTotal_fee())));
        orderWxpay.setTransactionId(request.getTransaction_id());
        orderWxpay.setTimeEnd(DateUtils.parseDate(request.getTime_end(), new String[] { Pattern.DATE_TIME_WX }));
        ObjectMapper mapper = new ObjectMapper();
        orderWxpay.setParam(mapper.writeValueAsString(Utils.paraFilter(param)));
        orderWxpay.setAddTime(new Date());
        orderWxpayRepository.save(orderWxpay);
        response.setReturn_code(WxCode.SUCCESS.code);
    }

    private boolean validateRequestSign(Map<String, Object> param, String sign) throws Exception {
        param = new HashMap<String, Object>(param);
        param.remove("serialVersionUID");
        param.remove("sign");
        return Utils.createWxSign(param, key).equals(sign);
    }

    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if (obj == null) {
            return new HashMap<String, Object>(0);
        }

        Map<String, Object> map = new HashMap<String, Object>();

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }

        return map;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void getOrderPayResult(Long accountid, OrderPayResultRequest request, OrderPayResultResponse response) throws Exception {
        Long orderid = request.getOrderid();
        OrderInfo orderInfo = orderInfoRepository.findByOrderidAndAccountid(orderid, accountid);
        if (orderInfo == null) {
            response.setStatus(Status.PARAM_ERROR);
            response.addError(new Error("您访问的订单不存在"));
            return;
        }
        long start = System.currentTimeMillis();
        Integer status = orderInfo.getStatus();
        response.setOrderStatus(status);
        if (status == OrderStatus.PAID.value) {
            response.setPrice(orderInfo.getPrice());
            response.setActualPrice(orderInfo.getActualPrice());
        } else if (status == OrderStatus.PAYING.value) {
            if (request.getPayType() == PayType.ALIPAY.value) {
                // 他们并没有提供主动查询接口
            } else if (request.getPayType() == PayType.WXPAY_JSAPI.value) { // 主动查询微信支付的状态，并更新订单状态
                WxOrderQueryResponse orderQueryResponse = weixinPayService.orderQuery(orderid);
                if (orderQueryResponse.isCodeSuccess()
                        && TradeStatusContants.TRADE_STATUS_SUCCESS.equals(orderQueryResponse.getTrade_state())) {
                    orderInfo.setStatus(OrderStatus.PAID.value);
                    orderInfo.setModTime(new Date());
                    orderInfoRepository.save(orderInfo);
                    orderHistoryRepository.save(OrderHistory.newInstance(OrderStatus.PAYING, orderInfo));

                    OrderWxpay orderWxpay = new OrderWxpay();
                    orderWxpay.setOrderid(orderid);
                    orderWxpay.setType(OrderWxpay.TYPE_QUERY);
                    orderWxpay.setReturnCode(WxCode.parse(orderQueryResponse.getReturn_code()));
                    orderWxpay.setResultCode(WxCode.parse(orderQueryResponse.getResult_code()));
                    orderWxpay.setTrade_status(orderQueryResponse.getTrade_state());
                    orderWxpay.setOpenid(orderQueryResponse.getOpenid());
                    orderWxpay.setTotalFee(PriceConverter.fenToYuan(Long.valueOf(orderQueryResponse.getTotal_fee())));
                    orderWxpay.setTransactionId(orderQueryResponse.getTransaction_id());
                    orderWxpay.setTimeEnd(DateUtils.parseDate(orderQueryResponse.getTime_end(), new String[] { Pattern.DATE_TIME_WX }));
                    ObjectMapper mapper = new ObjectMapper();
                    orderWxpay.setParam(mapper.writeValueAsString(request));
                    orderWxpay.setAddTime(new Date());
                    orderWxpayRepository.save(orderWxpay);

                    response.setOrderStatus(OrderStatus.PAID.value);
                    response.setPrice(orderInfo.getPrice());
                    response.setActualPrice(orderInfo.getActualPrice());
                }
            } else {
                response.setStatus(Status.PARAM_ERROR);
                response.addError(new Error("查询的支付类型不支持"));
            }
        } else {
            response.setStatus(Status.PARAM_ERROR);
            response.addError(new Error("您访问的订单存在异常，请联系海逍遥：" + DefaultValue.HOTLINE));
            return;
        }
        long cost = System.currentTimeMillis() - start;
        if (cost < 2000L) { // 花费时间不足两秒，补足两秒
            Thread.sleep(2000L - cost);
        }
    }

}
