package com.buterfleoge.whale.biz.order;

import java.util.Map;

import com.buterfleoge.whale.service.alipay.protocol.AlipayCreateNotifyRequest;
import com.buterfleoge.whale.service.alipay.protocol.AlipayCreateReturnRequest;
import com.buterfleoge.whale.service.weixin.protocol.WxPayJsapiNotifyRequest;
import com.buterfleoge.whale.service.weixin.protocol.WxPayJsapiNotifyResponse;
import com.buterfleoge.whale.type.protocol.Response;
import com.buterfleoge.whale.type.protocol.order.OrderPayResultRequest;
import com.buterfleoge.whale.type.protocol.order.OrderPayResultResponse;
import com.buterfleoge.whale.type.protocol.order.PayOrderByAlipayResponse;
import com.buterfleoge.whale.type.protocol.order.PayOrderRequest;

/**
 * @author xiezhenzong
 *
 */
public interface PayOrderBiz {

    void payOrder(Long accountid, PayOrderRequest request, PayOrderByAlipayResponse response) throws Exception;

    void handleAlipayReturn(Long accountid, Map<String, String[]> paramsMap, AlipayCreateReturnRequest request, Response response)
            throws Exception;

    void handleAlipayNotify(Map<String, String[]> paramsMap, AlipayCreateNotifyRequest request, Response response)
            throws Exception;

    void handleWxpayNotify(WxPayJsapiNotifyRequest request, WxPayJsapiNotifyResponse response) throws Exception;

    void getOrderPayResult(Long accountid, OrderPayResultRequest request, OrderPayResultResponse response) throws Exception;
}
