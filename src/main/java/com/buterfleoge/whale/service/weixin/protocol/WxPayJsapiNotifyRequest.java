package com.buterfleoge.whale.service.weixin.protocol;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.buterfleoge.whale.type.WxCode;
import com.buterfleoge.whale.type.protocol.Request;

/**
 * @author xiezhenzong
 *
 */
@XmlRootElement(name = "xml")
public class WxPayJsapiNotifyRequest extends Request implements Serializable {

    /**
     * serial version uid
     */
    private static final long serialVersionUID = 554643097201031097L;

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

    private String trade_type;

    private String openid;

    private String is_subscribe;

    private String bank_type;

    private Integer total_fee;

    private Integer settlement_total_fee;

    private String fee_type;

    private Integer cash_fee;

    private String cash_fee_type;

    private Integer coupon_fee;

    private Integer coupon_count;

    // 妈的，智障
    private Integer coupon_type_0;
    private Integer coupon_type_1;
    private Integer coupon_type_2;
    private Integer coupon_type_3;
    private Integer coupon_type_4;
    private Integer coupon_type_5;
    private Integer coupon_type_6;
    private Integer coupon_type_7;
    private Integer coupon_type_8;
    private Integer coupon_type_9;

    private String coupon_id_0;
    private String coupon_id_1;
    private String coupon_id_2;
    private String coupon_id_3;
    private String coupon_id_4;
    private String coupon_id_5;
    private String coupon_id_6;
    private String coupon_id_7;
    private String coupon_id_8;
    private String coupon_id_9;

    private Integer coupon_fee_0;
    private Integer coupon_fee_1;
    private Integer coupon_fee_2;
    private Integer coupon_fee_3;
    private Integer coupon_fee_4;
    private Integer coupon_fee_5;
    private Integer coupon_fee_6;
    private Integer coupon_fee_7;
    private Integer coupon_fee_8;
    private Integer coupon_fee_9;

    private String transaction_id;

    private String out_trade_no;

    private String attach;

    private String time_end;

    public boolean isReturnCodeSuccess() {
        return WxCode.SUCCESS.code.equalsIgnoreCase(return_code);
    }

    public boolean isResultCodeSuccess() {
        return WxCode.SUCCESS.code.equalsIgnoreCase(result_code);
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

    /**
     * @return the is_subscribe
     */
    public String getIs_subscribe() {
        return is_subscribe;
    }

    /**
     * @param is_subscribe
     *            the is_subscribe to set
     */
    public void setIs_subscribe(String is_subscribe) {
        this.is_subscribe = is_subscribe;
    }

    /**
     * @return the bank_type
     */
    public String getBank_type() {
        return bank_type;
    }

    /**
     * @param bank_type
     *            the bank_type to set
     */
    public void setBank_type(String bank_type) {
        this.bank_type = bank_type;
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
     * @return the settlement_total_fee
     */
    public Integer getSettlement_total_fee() {
        return settlement_total_fee;
    }

    /**
     * @param settlement_total_fee
     *            the settlement_total_fee to set
     */
    public void setSettlement_total_fee(Integer settlement_total_fee) {
        this.settlement_total_fee = settlement_total_fee;
    }

    /**
     * @return the fee_type
     */
    public String getFee_type() {
        return fee_type;
    }

    /**
     * @param fee_type
     *            the fee_type to set
     */
    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    /**
     * @return the cash_fee
     */
    public Integer getCash_fee() {
        return cash_fee;
    }

    /**
     * @param cash_fee
     *            the cash_fee to set
     */
    public void setCash_fee(Integer cash_fee) {
        this.cash_fee = cash_fee;
    }

    /**
     * @return the cash_fee_type
     */
    public String getCash_fee_type() {
        return cash_fee_type;
    }

    /**
     * @param cash_fee_type
     *            the cash_fee_type to set
     */
    public void setCash_fee_type(String cash_fee_type) {
        this.cash_fee_type = cash_fee_type;
    }

    /**
     * @return the coupon_fee
     */
    public Integer getCoupon_fee() {
        return coupon_fee;
    }

    /**
     * @param coupon_fee
     *            the coupon_fee to set
     */
    public void setCoupon_fee(Integer coupon_fee) {
        this.coupon_fee = coupon_fee;
    }

    /**
     * @return the coupon_count
     */
    public Integer getCoupon_count() {
        return coupon_count;
    }

    /**
     * @param coupon_count
     *            the coupon_count to set
     */
    public void setCoupon_count(Integer coupon_count) {
        this.coupon_count = coupon_count;
    }

    /**
     * @return the coupon_type_0
     */
    public Integer getCoupon_type_0() {
        return coupon_type_0;
    }

    /**
     * @param coupon_type_0
     *            the coupon_type_0 to set
     */
    public void setCoupon_type_0(Integer coupon_type_0) {
        this.coupon_type_0 = coupon_type_0;
    }

    /**
     * @return the coupon_type_1
     */
    public Integer getCoupon_type_1() {
        return coupon_type_1;
    }

    /**
     * @param coupon_type_1
     *            the coupon_type_1 to set
     */
    public void setCoupon_type_1(Integer coupon_type_1) {
        this.coupon_type_1 = coupon_type_1;
    }

    /**
     * @return the coupon_type_2
     */
    public Integer getCoupon_type_2() {
        return coupon_type_2;
    }

    /**
     * @param coupon_type_2
     *            the coupon_type_2 to set
     */
    public void setCoupon_type_2(Integer coupon_type_2) {
        this.coupon_type_2 = coupon_type_2;
    }

    /**
     * @return the coupon_type_3
     */
    public Integer getCoupon_type_3() {
        return coupon_type_3;
    }

    /**
     * @param coupon_type_3
     *            the coupon_type_3 to set
     */
    public void setCoupon_type_3(Integer coupon_type_3) {
        this.coupon_type_3 = coupon_type_3;
    }

    /**
     * @return the coupon_type_4
     */
    public Integer getCoupon_type_4() {
        return coupon_type_4;
    }

    /**
     * @param coupon_type_4
     *            the coupon_type_4 to set
     */
    public void setCoupon_type_4(Integer coupon_type_4) {
        this.coupon_type_4 = coupon_type_4;
    }

    /**
     * @return the coupon_type_5
     */
    public Integer getCoupon_type_5() {
        return coupon_type_5;
    }

    /**
     * @param coupon_type_5
     *            the coupon_type_5 to set
     */
    public void setCoupon_type_5(Integer coupon_type_5) {
        this.coupon_type_5 = coupon_type_5;
    }

    /**
     * @return the coupon_type_6
     */
    public Integer getCoupon_type_6() {
        return coupon_type_6;
    }

    /**
     * @param coupon_type_6
     *            the coupon_type_6 to set
     */
    public void setCoupon_type_6(Integer coupon_type_6) {
        this.coupon_type_6 = coupon_type_6;
    }

    /**
     * @return the coupon_type_7
     */
    public Integer getCoupon_type_7() {
        return coupon_type_7;
    }

    /**
     * @param coupon_type_7
     *            the coupon_type_7 to set
     */
    public void setCoupon_type_7(Integer coupon_type_7) {
        this.coupon_type_7 = coupon_type_7;
    }

    /**
     * @return the coupon_type_8
     */
    public Integer getCoupon_type_8() {
        return coupon_type_8;
    }

    /**
     * @param coupon_type_8
     *            the coupon_type_8 to set
     */
    public void setCoupon_type_8(Integer coupon_type_8) {
        this.coupon_type_8 = coupon_type_8;
    }

    /**
     * @return the coupon_type_9
     */
    public Integer getCoupon_type_9() {
        return coupon_type_9;
    }

    /**
     * @param coupon_type_9
     *            the coupon_type_9 to set
     */
    public void setCoupon_type_9(Integer coupon_type_9) {
        this.coupon_type_9 = coupon_type_9;
    }

    /**
     * @return the coupon_id_0
     */
    public String getCoupon_id_0() {
        return coupon_id_0;
    }

    /**
     * @param coupon_id_0
     *            the coupon_id_0 to set
     */
    public void setCoupon_id_0(String coupon_id_0) {
        this.coupon_id_0 = coupon_id_0;
    }

    /**
     * @return the coupon_id_1
     */
    public String getCoupon_id_1() {
        return coupon_id_1;
    }

    /**
     * @param coupon_id_1
     *            the coupon_id_1 to set
     */
    public void setCoupon_id_1(String coupon_id_1) {
        this.coupon_id_1 = coupon_id_1;
    }

    /**
     * @return the coupon_id_2
     */
    public String getCoupon_id_2() {
        return coupon_id_2;
    }

    /**
     * @param coupon_id_2
     *            the coupon_id_2 to set
     */
    public void setCoupon_id_2(String coupon_id_2) {
        this.coupon_id_2 = coupon_id_2;
    }

    /**
     * @return the coupon_id_3
     */
    public String getCoupon_id_3() {
        return coupon_id_3;
    }

    /**
     * @param coupon_id_3
     *            the coupon_id_3 to set
     */
    public void setCoupon_id_3(String coupon_id_3) {
        this.coupon_id_3 = coupon_id_3;
    }

    /**
     * @return the coupon_id_4
     */
    public String getCoupon_id_4() {
        return coupon_id_4;
    }

    /**
     * @param coupon_id_4
     *            the coupon_id_4 to set
     */
    public void setCoupon_id_4(String coupon_id_4) {
        this.coupon_id_4 = coupon_id_4;
    }

    /**
     * @return the coupon_id_5
     */
    public String getCoupon_id_5() {
        return coupon_id_5;
    }

    /**
     * @param coupon_id_5
     *            the coupon_id_5 to set
     */
    public void setCoupon_id_5(String coupon_id_5) {
        this.coupon_id_5 = coupon_id_5;
    }

    /**
     * @return the coupon_id_6
     */
    public String getCoupon_id_6() {
        return coupon_id_6;
    }

    /**
     * @param coupon_id_6
     *            the coupon_id_6 to set
     */
    public void setCoupon_id_6(String coupon_id_6) {
        this.coupon_id_6 = coupon_id_6;
    }

    /**
     * @return the coupon_id_7
     */
    public String getCoupon_id_7() {
        return coupon_id_7;
    }

    /**
     * @param coupon_id_7
     *            the coupon_id_7 to set
     */
    public void setCoupon_id_7(String coupon_id_7) {
        this.coupon_id_7 = coupon_id_7;
    }

    /**
     * @return the coupon_id_8
     */
    public String getCoupon_id_8() {
        return coupon_id_8;
    }

    /**
     * @param coupon_id_8
     *            the coupon_id_8 to set
     */
    public void setCoupon_id_8(String coupon_id_8) {
        this.coupon_id_8 = coupon_id_8;
    }

    /**
     * @return the coupon_id_9
     */
    public String getCoupon_id_9() {
        return coupon_id_9;
    }

    /**
     * @param coupon_id_9
     *            the coupon_id_9 to set
     */
    public void setCoupon_id_9(String coupon_id_9) {
        this.coupon_id_9 = coupon_id_9;
    }

    /**
     * @return the coupon_fee_0
     */
    public Integer getCoupon_fee_0() {
        return coupon_fee_0;
    }

    /**
     * @param coupon_fee_0
     *            the coupon_fee_0 to set
     */
    public void setCoupon_fee_0(Integer coupon_fee_0) {
        this.coupon_fee_0 = coupon_fee_0;
    }

    /**
     * @return the coupon_fee_1
     */
    public Integer getCoupon_fee_1() {
        return coupon_fee_1;
    }

    /**
     * @param coupon_fee_1
     *            the coupon_fee_1 to set
     */
    public void setCoupon_fee_1(Integer coupon_fee_1) {
        this.coupon_fee_1 = coupon_fee_1;
    }

    /**
     * @return the coupon_fee_2
     */
    public Integer getCoupon_fee_2() {
        return coupon_fee_2;
    }

    /**
     * @param coupon_fee_2
     *            the coupon_fee_2 to set
     */
    public void setCoupon_fee_2(Integer coupon_fee_2) {
        this.coupon_fee_2 = coupon_fee_2;
    }

    /**
     * @return the coupon_fee_3
     */
    public Integer getCoupon_fee_3() {
        return coupon_fee_3;
    }

    /**
     * @param coupon_fee_3
     *            the coupon_fee_3 to set
     */
    public void setCoupon_fee_3(Integer coupon_fee_3) {
        this.coupon_fee_3 = coupon_fee_3;
    }

    /**
     * @return the coupon_fee_4
     */
    public Integer getCoupon_fee_4() {
        return coupon_fee_4;
    }

    /**
     * @param coupon_fee_4
     *            the coupon_fee_4 to set
     */
    public void setCoupon_fee_4(Integer coupon_fee_4) {
        this.coupon_fee_4 = coupon_fee_4;
    }

    /**
     * @return the coupon_fee_5
     */
    public Integer getCoupon_fee_5() {
        return coupon_fee_5;
    }

    /**
     * @param coupon_fee_5
     *            the coupon_fee_5 to set
     */
    public void setCoupon_fee_5(Integer coupon_fee_5) {
        this.coupon_fee_5 = coupon_fee_5;
    }

    /**
     * @return the coupon_fee_6
     */
    public Integer getCoupon_fee_6() {
        return coupon_fee_6;
    }

    /**
     * @param coupon_fee_6
     *            the coupon_fee_6 to set
     */
    public void setCoupon_fee_6(Integer coupon_fee_6) {
        this.coupon_fee_6 = coupon_fee_6;
    }

    /**
     * @return the coupon_fee_7
     */
    public Integer getCoupon_fee_7() {
        return coupon_fee_7;
    }

    /**
     * @param coupon_fee_7
     *            the coupon_fee_7 to set
     */
    public void setCoupon_fee_7(Integer coupon_fee_7) {
        this.coupon_fee_7 = coupon_fee_7;
    }

    /**
     * @return the coupon_fee_8
     */
    public Integer getCoupon_fee_8() {
        return coupon_fee_8;
    }

    /**
     * @param coupon_fee_8
     *            the coupon_fee_8 to set
     */
    public void setCoupon_fee_8(Integer coupon_fee_8) {
        this.coupon_fee_8 = coupon_fee_8;
    }

    /**
     * @return the coupon_fee_9
     */
    public Integer getCoupon_fee_9() {
        return coupon_fee_9;
    }

    /**
     * @param coupon_fee_9
     *            the coupon_fee_9 to set
     */
    public void setCoupon_fee_9(Integer coupon_fee_9) {
        this.coupon_fee_9 = coupon_fee_9;
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
     * @return the time_end
     */
    public String getTime_end() {
        return time_end;
    }

    /**
     * @param time_end
     *            the time_end to set
     */
    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

}
