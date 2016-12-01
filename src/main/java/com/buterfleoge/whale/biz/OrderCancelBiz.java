package com.buterfleoge.whale.biz;

import com.buterfleoge.whale.type.protocol.Response;
import com.buterfleoge.whale.type.protocol.order.OrderRequest;

/**
 * @author xiezhenzong
 *
 */
public interface OrderCancelBiz {

    void cancelOrder(Long accountid, OrderRequest request, Response response) throws Exception;

}
