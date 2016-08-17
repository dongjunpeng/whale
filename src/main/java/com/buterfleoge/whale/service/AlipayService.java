/**
 *
 */
package com.buterfleoge.whale.service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author xiezhenzong
 *
 */
public interface AlipayService {

    String createDirectPay(Long orderid, BigDecimal totalFee, BigDecimal price, String subject);

    String buildRequest(Map<String, String> sParaTemp);

}
