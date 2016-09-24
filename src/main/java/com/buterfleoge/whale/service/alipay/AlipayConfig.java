package com.buterfleoge.whale.service.alipay;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.4
 *修改日期：2016-03-08
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 *
 * @edit xiezhenzong
 */
@Component("alipayConfig")
public class AlipayConfig implements InitializingBean {

    /**
     * 字符编码格式 目前支持 gbk 或 utf-8
     */
    public static final String INPUT_CHARSET = "utf-8";

    /**
     * 支付类型 ，无需修改
     */
    public static final String PAYMENT_TYPE = "1";

    /**
     * 签名方式
     */
    public static final String SIGN_TYPE = "MD5";

    /**
     * 调用的接口名，无需修改
     */
    public static final String CREATE_DIRECT_PAY_BY_USER_SERVICE = "create_direct_pay_by_user";

    /**
     * 支付宝提供给商户的服务接入网关URL(新)
     */
    @Value("${alipay.gateway}")
    public String alipay_gateway_new;

    /**
     * 支付宝消息验证地址
     */
    @Value("${alipay.verify}")
    public String https_verify_url;

    // 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
    @Value("${alipay.partner}")
    public String partner;

    // 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
    public String seller_id;

    // MD5密钥，安全检验码，由数字和字母组成的32位字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
    @Value("${alipay.key}")
    public String key;

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    @Value("${alipay.notify_url}")
    public String notify_url;

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    @Value("${alipay.return_url}")
    public String return_url;

    // 防钓鱼时间戳  若要使用请调用类文件submit中的query_timestamp函数
    public String anti_phishing_key = "";

    // 客户端的IP地址 非局域网的外网IP地址，如：221.0.0.1
    public String exter_invoke_ip = "";

    @Override
    public void afterPropertiesSet() throws Exception {
        seller_id = partner;
        // notify_url = URLEncoder.encode(notify_url, "UTF-8");
        // return_url = URLEncoder.encode(return_url, "UTF-8");
    }

}

