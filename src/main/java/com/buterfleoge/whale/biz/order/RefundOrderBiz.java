package com.buterfleoge.whale.biz.order;

import com.buterfleoge.whale.type.protocol.order.OrderRequest;
import com.buterfleoge.whale.type.protocol.order.RefundResponse;

/**
 * @author xiezhenzong
 *
 */
public interface RefundOrderBiz {

    void getRefundInfo(Long accountid, OrderRequest request, RefundResponse response) throws Exception;

}
