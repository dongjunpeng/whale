package com.buterfleoge.whale.biz.order;

import com.buterfleoge.whale.type.protocol.Response;
import com.buterfleoge.whale.type.protocol.order.OrderRequest;

/**
 * @author xiezhenzong
 *
 */
public interface CancelOrderBiz {

    void cancelOrder(Long accountid, OrderRequest request, Response response) throws Exception;

}
