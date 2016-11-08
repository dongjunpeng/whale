package com.buterfleoge.whale.service.weixin.protocol;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.Utils;

/**
 * @author xiezhenzong
 *
 */
@XmlRootElement(name = "xml")
public class WxOrderQueryRequest extends BaseObject {

    private String appid;

    private String mch_id;

    private String transaction_id;

    private String out_trade_no;

    private String nonce_str;

    private String sign;

    public void fillSign(String key) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("appid", appid);
        param.put("mch_id", mch_id);
        param.put("nonce_str", nonce_str);
        param.put("transaction_id", transaction_id);
        param.put("out_trade_no", out_trade_no);
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
     * @return the transaction_id
     */
    public String getTransaction_id() {
        return transaction_id;
    }

    /**
     * @param transaction_id
     *            the transaction_id to set
     */
    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
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

}
