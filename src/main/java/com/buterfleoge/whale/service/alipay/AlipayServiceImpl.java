/**
 *
 */
package com.buterfleoge.whale.service.alipay;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buterfleoge.whale.service.AlipayService;

/**
 * @author xiezhenzong
 *
 */
@Service("alipayService")
public class AlipayServiceImpl implements AlipayService {

    @Autowired
    private AlipayConfig alipayConfig;

    @Override
    public String createDirectPay(Long orderid, BigDecimal totalFee, BigDecimal price, String subject) {
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", AlipayConfig.service);
        sParaTemp.put("partner", alipayConfig.partner);
        sParaTemp.put("seller_id", alipayConfig.seller_id);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        sParaTemp.put("payment_type", AlipayConfig.payment_type);
        sParaTemp.put("notify_url", alipayConfig.notify_url);
        sParaTemp.put("return_url", alipayConfig.return_url);
        // sParaTemp.put("anti_phishing_key", AlipayConfig.anti_phishing_key);
        // sParaTemp.put("exter_invoke_ip", AlipayConfig.exter_invoke_ip);
        // sParaTemp.put("show_url", value);
        // sParaTemp.put("it_b_pay", value);
        sParaTemp.put("out_trade_no", String.valueOf(orderid));
        sParaTemp.put("subject", subject);
        sParaTemp.put("total_fee", totalFee.setScale(2, RoundingMode.HALF_UP).toString());
        sParaTemp.put("price", price.setScale(2, RoundingMode.HALF_UP).toString());
        return AlipaySubmit.buildRequest(sParaTemp, alipayConfig.key);
    }

    @Override
    public String buildRequest(Map<String, String> sParaTemp) {
        return AlipaySubmit.buildRequest(sParaTemp, alipayConfig.key);
    }

}
