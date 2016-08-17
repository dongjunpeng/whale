package com.buterfleoge.whale.service.alipay;

import java.util.Map;
import java.util.Map.Entry;

/* *
 *类名：AlipaySubmit
 *功能：支付宝各接口请求提交类
 *详细：构造支付宝各接口表单HTML文本，获取远程HTTP数据
 *版本：3.3
 *日期：2012-08-13
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */
public class AlipaySubmit {

    /**
     * 建立请求，以表单HTML形式构造（默认）
     *
     * @param sParaTemp
     *            请求参数数组
     * @param key
     *            key
     * @return 提交表单HTML文本
     */
    public static String buildRequest(Map<String, String> sParaTemp, String key) {
        // 待请求参数数组
        Map<String, String> sPara = buildRequestPara(sParaTemp, key);

        StringBuffer builder = new StringBuffer();
        builder.append("<form id=\"alipaysubmit\" name=\"alipaysubmit\" action=\"")
        .append(AlipayConfig.ALIPAY_GATEWAY_NEW).append("_input_charset=").append(AlipayConfig.input_charset)
        .append("\" method=\"get\">");
        for (Entry<String, String> entry : sPara.entrySet()) {
            builder.append("<input type=\"hidden\" name=\"").append(entry.getKey()).append("\" value=\"")
            .append(entry.getValue()).append("\"/>");
        }

        // submit按钮控件请不要含有name属性
        builder.append("<input type=\"submit\" value=\"确认\" style=\"display:none;\"></form>");
        builder.append("<script>document.forms['alipaysubmit'].submit();</script>");
        return builder.toString();
    }

    /**
     * 生成要请求给支付宝的参数数组
     *
     * @param sParaTemp
     *            请求前的参数数组
     * @param key
     *            key
     * @return 要请求的参数数组
     */
    private static Map<String, String> buildRequestPara(Map<String, String> sParaTemp, String key) {
        // 除去数组中的空值和签名参数
        Map<String, String> sPara = AlipayCore.paraFilter(sParaTemp);
        // 生成签名结果
        String prestr = AlipayCore.createLinkString(sPara);
        // 签名结果与签名方式加入请求提交参数组中
        sPara.put("sign", MD5.sign(prestr, key, AlipayConfig.input_charset));
        sPara.put("sign_type", AlipayConfig.sign_type);
        return sPara;
    }

}
