package com.buterfleoge.whale.biz.order;

import com.buterfleoge.whale.type.protocol.Response;
import com.buterfleoge.whale.type.protocol.order.PayOrderByAlipayResponse;
import com.buterfleoge.whale.type.protocol.order.PayOrderRequest;

/**
 * @author xiezhenzong
 *
 */
public interface PayOrderBiz {

    void payOrder(Long accountid, PayOrderRequest request, PayOrderByAlipayResponse response) throws Exception;

    void alipay(Long accountid, PayOrderRequest requst, Response response) throws Exception;

}
