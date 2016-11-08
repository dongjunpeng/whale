/**
 *
 */
package com.buterfleoge.whale.service.alipay;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buterfleoge.whale.Constants.Status;
import com.buterfleoge.whale.log.InvokeLogger;
import com.buterfleoge.whale.service.AlipayService;

/**
 * @author xiezhenzong
 *
 */
@Service("alipayService")
public class AlipayServiceImpl implements AlipayService {

    private static final Logger LOG = LoggerFactory.getLogger(AlipayServiceImpl.class);

    @Autowired
    private AlipayConfig alipayConfig;

    @Override
    public Map<String, String> createDirectPay(Long orderid, BigDecimal totalFee, BigDecimal price, String subject) {
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", AlipayConfig.CREATE_DIRECT_PAY_BY_USER_SERVICE);
        sParaTemp.put("partner", alipayConfig.partner);
        sParaTemp.put("seller_id", alipayConfig.seller_id);
        sParaTemp.put("_input_charset", AlipayConfig.INPUT_CHARSET);
        sParaTemp.put("payment_type", AlipayConfig.PAYMENT_TYPE);
        sParaTemp.put("notify_url", alipayConfig.notify_url);
        sParaTemp.put("return_url", alipayConfig.return_url);
        sParaTemp.put("out_trade_no", String.valueOf(orderid));
        sParaTemp.put("subject", subject);
        sParaTemp.put("total_fee", totalFee.setScale(2, RoundingMode.HALF_UP).toString());
        // sParaTemp.put("anti_phishing_key", AlipayConfig.anti_phishing_key);
        // sParaTemp.put("exter_invoke_ip", AlipayConfig.exter_invoke_ip);
        // sParaTemp.put("show_url", value);
        // sParaTemp.put("it_b_pay", value);
        sParaTemp =  AlipaySubmit.buildRequestPara(sParaTemp, alipayConfig.key); // 根据要发送的表单数据进行加密
        sParaTemp.put("gateway", alipayConfig.alipay_gateway_new); // gateway不参与加密计算
        return sParaTemp;
    }

    @Override
    public boolean verify(Map<String, String[]> paramsMap) {
        long start = System.currentTimeMillis();
        int status = Status.OK;
        Boolean response = null;
        Map<String, String> params = new HashMap<String, String>(paramsMap.size());
        try {
            for (Entry<String, String[]> entry : paramsMap.entrySet()) {
                String name = entry.getKey();
                String[] values = entry.getValue();
                String valueStr = values[0];
                for (int i = 1, n = values.length; i < n; i++) {
                    valueStr = "," + valueStr + values[i];
                }
                // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
                // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
                params.put(name, valueStr);
            }
            response = AlipayNotify.verify(params, alipayConfig.https_verify_url, alipayConfig.partner, alipayConfig.key);
        } catch (Exception e) {
            LOG.error("Verify failed, params: " + params, e);
            status = Status.INVOKE_ERROR;
        } finally {
            InvokeLogger.log("alipay", "verify", params, response, start, System.currentTimeMillis() - start, status);
        }
        return response;
    }

}
