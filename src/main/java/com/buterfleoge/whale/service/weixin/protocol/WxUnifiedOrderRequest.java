package com.buterfleoge.whale.service.weixin.protocol;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.CDATAAdapter;
import com.buterfleoge.whale.Utils;

/**
 * @author xiezhenzong
 *
 */
@XmlRootElement(name = "xml")
public class WxUnifiedOrderRequest extends BaseObject {

    private String appid;

    private String mch_id;

    private String device_info = "WEB";

    private String nonce_str;

    private String sign;

    private String body;

    // @XmlJavaTypeAdapter(CDATAAdapter.class)
    private String detail;

    private String attach;

    private String out_trade_no;

    private Integer total_fee;

    private String spbill_create_ip;

    private String time_start;

    private String time_expire;

    private String notify_url;

    private String trade_type = "JSAPI";

    private String product_id;

    private String openid;

    public void fillSign(String key) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("appid", appid);
        param.put("mch_id", mch_id);
        param.put("device_info", device_info);
        param.put("nonce_str", nonce_str);
        param.put("body", body);
        param.put("detail", detail);
        param.put("attach", attach);
        param.put("out_trade_no", out_trade_no);
        param.put("total_fee", total_fee);
        param.put("spbill_create_ip", spbill_create_ip);
        param.put("time_start", time_start);
        param.put("time_expire", time_expire);
        param.put("notify_url", notify_url);
        param.put("trade_type", trade_type);
        param.put("product_id", product_id);
        param.put("openid", openid);

        setSign(Utils.createWxSign(param, key));
    }

    /**
     * @return the appid
     */
    public String getAppid() {
        return appid;
    }

    /**
     * @param appid
     *            the appid to set
     */
    public void setAppid(String appid) {
        this.appid = appid;
    }

    /**
     * @return the mch_id
     */
    public String getMch_id() {
        return mch_id;
    }

    /**
     * @param mch_id
     *            the mch_id to set
     */
    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    /**
     * @return the device_info
     */
    public String getDevice_info() {
        return device_info;
    }

    /**
     * @param device_info
     *            the device_info to set
     */
    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    /**
     * @return the nonce_str
     */
    public String getNonce_str() {
        return nonce_str;
    }

    /**
     * @param nonce_str
     *            the nonce_str to set
     */
    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    /**
     * @return the sign
     */
    public String getSign() {
        return sign;
    }

    /**
     * @param sign
     *            the sign to set
     */
    public void setSign(String sign) {
        this.sign = sign;
    }

    /**
     * @return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * @param body
     *            the body to set
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * @return the detail
     */
    @XmlJavaTypeAdapter(CDATAAdapter.class)
    public String getDetail() {
        return detail;
    }

    /**
     * @param detail
     *            the detail to set
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * @return the attach
     */
    public String getAttach() {
        return attach;
    }

    /**
     * @param attach
     *            the attach to set
     */
    public void setAttach(String attach) {
        this.attach = attach;
    }

    /**
     * @return the out_trade_no
     */
    public String getOut_trade_no() {
        return out_trade_no;
    }

    /**
     * @param out_trade_no
     *            the out_trade_no to set
     */
    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    /**
     * @return the total_fee
     */
    public Integer getTotal_fee() {
        return total_fee;
    }

    /**
     * @param total_fee
     *            the total_fee to set
     */
    public void setTotal_fee(Integer total_fee) {
        this.total_fee = total_fee;
    }

    /**
     * @return the spbill_create_ip
     */
    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    /**
     * @param spbill_create_ip
     *            the spbill_create_ip to set
     */
    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    /**
     * @return the time_start
     */
    public String getTime_start() {
        return time_start;
    }

    /**
     * @param time_start
     *            the time_start to set
     */
    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    /**
     * @return the time_expire
     */
    public String getTime_expire() {
        return time_expire;
    }

    /**
     * @param time_expire
     *            the time_expire to set
     */
    public void setTime_expire(String time_expire) {
        this.time_expire = time_expire;
    }

    /**
     * @return the notify_url
     */
    public String getNotify_url() {
        return notify_url;
    }

    /**
     * @param notify_url
     *            the notify_url to set
     */
    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    /**
     * @return the trade_type
     */
    public String getTrade_type() {
        return trade_type;
    }

    /**
     * @param trade_type
     *            the trade_type to set
     */
    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    /**
     * @return the product_id
     */
    public String getProduct_id() {
        return product_id;
    }

    /**
     * @param product_id
     *            the product_id to set
     */
    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    /**
     * @return the openid
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * @param openid
     *            the openid to set
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }

}
