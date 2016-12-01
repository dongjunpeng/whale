/**
 *
 */
package com.buterfleoge.whale.biz;

import com.buterfleoge.whale.type.protocol.Response;
import com.buterfleoge.whale.type.protocol.order.CreateOrderRequest;
import com.buterfleoge.whale.type.protocol.order.NewOrderRequest;
import com.buterfleoge.whale.type.protocol.order.NewOrderResponse;
import com.buterfleoge.whale.type.protocol.order.OrderRequest;
import com.buterfleoge.whale.type.protocol.order.PreviewContractRequest;

/**
 * @author xiezhenzong
 *
 */
public interface OrderCreateBiz {

    void newOrder(Long accountid, NewOrderRequest request, NewOrderResponse response) throws Exception;

    void createOrder(Long accountid, CreateOrderRequest request, Response response) throws Exception;

    String getContract(Long accountid, OrderRequest request, Response response) throws Exception;

    String previewContract(Long accountid, PreviewContractRequest request, Response response) throws Exception;

}
