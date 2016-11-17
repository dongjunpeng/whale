package com.buterfleoge.whale.service.weixin.protocol;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.buterfleoge.whale.BaseObject;

/**
 * @author xiezhenzong
 *
 */
@XmlRootElement(name = "xml")
public class WxUnifiedOrderResponse extends BaseObject implements Serializable {

    /**
     * serial version uid
     */
    private static final long serialVersionUID = 4117146158439258611L;

    public static final String SUCCESS = "SUCCESS";
    public static final String FAIL = "FAIL";

    private String return_code;

    private String return_msg;

    // 以下字段在return_code为SUCCESS的时候有返回

    private String appid;

    private String mch_id;

    private String device_info;

    private String nonce_str;

    private String sign;

    private String result_code;

    private String err_code;

    private String err_code_des;

    // 以下字段在return_code 和result_code都为SUCCESS的时候有返回
    private String trade_type;

    private String prepay_id;

    // trade_type为NATIVE是有返回，可将该参数值生成二维码展示出来进行扫码支付
    private String code_url;

    public boolean isReturnCodeSuccess() {
        return SUCCESS.equalsIgnoreCase(return_code);
    }

    public boolean isResultCodeSuccess() {
        return SUCCESS.equalsIgnoreCase(result_code);
    }

    public boolean isCodeSuccess() {
        return isReturnCodeSuccess() && isResultCodeSuccess();
    }

    /**
     * @return the return_code
     */
    public String getReturn_code() {
        return return_code;
    }

    /**
     * @param return_code
     *            the return_code to set
     */
    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    /**
     * @return the return_msg
     */
    public String getReturn_msg() {
        return return_msg;
    }

    /**
     * @param return_msg
     *            the return_msg to set
     */
    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
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
     * @return the result_code
     */
    public String getResult_code() {
        return result_code;
    }

    /**
     * @param result_code
     *            the result_code to set
     */
    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    /**
     * @return the err_code
     */
    public String getErr_code() {
        return err_code;
    }

    /**
     * @param err_code
     *            the err_code to set
     */
    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    /**
     * @return the err_code_des
     */
    public String getErr_code_des() {
        return err_code_des;
    }

    /**
     * @param err_code_des
     *            the err_code_des to set
     */
    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
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
     * @return the prepay_id
     */
    public String getPrepay_id() {
        return prepay_id;
    }

    /**
     * @param prepay_id
     *            the prepay_id to set
     */
    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    /**
     * @return the code_url
     */
    public String getCode_url() {
        return code_url;
    }

    /**
     * @param code_url
     *            the code_url to set
     */
    public void setCode_url(String code_url) {
        this.code_url = code_url;
    }

}
