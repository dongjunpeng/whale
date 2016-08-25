package com.buterfleoge.whale.biz.order;

import java.util.Map;

import com.buterfleoge.whale.service.alipay.protocol.AlipayCreateNotifyRequest;
import com.buterfleoge.whale.service.alipay.protocol.AlipayReturnRequest;
import com.buterfleoge.whale.type.protocol.Response;
import com.buterfleoge.whale.type.protocol.order.PayOrderByAlipayResponse;
import com.buterfleoge.whale.type.protocol.order.PayOrderRequest;

/**
 * @author xiezhenzong
 *
 */
public interface PayOrderBiz {

    void payOrder(Long accountid, PayOrderRequest request, PayOrderByAlipayResponse response) throws Exception;

    void handlePayReturn(Long accountid, Map<String, String[]> paramsMap, AlipayReturnRequest request, Response response)
            throws Exception;

    void handlePayNotify(Long accountid, Map<String, String[]> paramsMap, AlipayCreateNotifyRequest request, Response response)
            throws Exception;
}
