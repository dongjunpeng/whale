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

    Map<String, String> createDirectPay(Long orderid, BigDecimal totalFee, BigDecimal price, String subject);

    boolean verify(Map<String, String[]> paramsMap);

}
