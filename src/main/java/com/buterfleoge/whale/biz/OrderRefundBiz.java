package com.buterfleoge.whale.biz;

import com.buterfleoge.whale.type.protocol.Response;
import com.buterfleoge.whale.type.protocol.order.GetRefundTypeResponse;
import com.buterfleoge.whale.type.protocol.order.OrderRequest;
import com.buterfleoge.whale.type.protocol.order.RefundOrderRequest;

/**
 * @author xiezhenzong
 *
 */
public interface OrderRefundBiz {

    void getRefundType(Long accountid, OrderRequest request, GetRefundTypeResponse response) throws Exception;

    void refundOrder(Long accountid, RefundOrderRequest request, Response response) throws Exception;

}
