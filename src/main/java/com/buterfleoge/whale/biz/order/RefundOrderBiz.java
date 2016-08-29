package com.buterfleoge.whale.biz.order;

import com.buterfleoge.whale.type.protocol.Response;
import com.buterfleoge.whale.type.protocol.order.RefundOrderRequest;

/**
 * @author xiezhenzong
 *
 */
public interface RefundOrderBiz {

    void getRefundInfo(Long accountid, RefundOrderRequest request, Response response) throws Exception;

}
