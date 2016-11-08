package com.buterfleoge.whale.service;

import java.util.Map;

import com.buterfleoge.whale.service.weixin.protocol.WxOrderQueryResponse;
import com.buterfleoge.whale.service.weixin.protocol.WxUnifiedOrderResponse;
import com.buterfleoge.whale.type.entity.OrderInfo;

/**
 * @author xiezhenzong
 *
 */
public interface WeixinPayService {

    WxUnifiedOrderResponse unifiedOrder(Long orderid, OrderInfo orderInfo, String subject, String ip) throws Exception;

    Map<String, Object> geneJsapiParam(Long orderid, String prepayId) throws Exception;

    WxOrderQueryResponse orderQuery(Long orderid) throws Exception;

}
