package com.buterfleoge.whale.service.alipay.protocol;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.buterfleoge.whale.Constants.Pattern;
import com.buterfleoge.whale.type.AlipayTradeStatus;
import com.buterfleoge.whale.type.protocol.Request;
import com.buterfleoge.whale.validator.IdExist;
import com.buterfleoge.whale.validator.IdExist.IdType;

/**
 * @author xiezhenzong
 *
 */
public abstract class AlipayCallbackRequest extends Request {

    /**
     * 支付宝验证id
     */
    protected String notify_id;
    @DateTimeFormat(pattern = Pattern.DATE_TIME)
    protected Date notify_time;
    protected String notify_type;
    protected String sign_type;
    protected String sign;
    /**
     * 海逍遥的id
     */
    @IdExist(type = IdType.ORDER_ID, nullable = false, message = "")
    protected Long out_trade_no;
    protected String subject;
    protected String payment_type;
    /**
     * 支付宝交易id
     */
    protected String trade_no;
    protected AlipayTradeStatus trade_status;
    protected String seller_id;
    protected String buyer_id;
    protected String buyer_email;
    protected BigDecimal total_fee;

    /**
     * @return the notify_id
     */
    public String getNotify_id() {
        return notify_id;
    }

    /**
     * @param notify_id
     *            the notify_id to set
     */
    public void setNotify_id(String notify_id) {
        this.notify_id = notify_id;
    }

    /**
     * @return the notify_time
     */
    public Date getNotify_time() {
        return notify_time;
    }

    /**
     * @param notify_time
     *            the notify_time to set
     */
    public void setNotify_time(Date notify_time) {
        this.notify_time = notify_time;
    }

    /**
     * @return the notify_type
     */
    public String getNotify_type() {
        return notify_type;
    }

    /**
     * @param notify_type
     *            the notify_type to set
     */
    public void setNotify_type(String notify_type) {
        this.notify_type = notify_type;
    }

    /**
     * @return the sign_type
     */
    public String getSign_type() {
        return sign_type;
    }

    /**
     * @param sign_type
     *            the sign_type to set
     */
    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
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
     * @return the out_trade_no
     */
    public Long getOut_trade_no() {
        return out_trade_no;
    }

    /**
     * @param out_trade_no
     *            the out_trade_no to set
     */
    public void setOut_trade_no(Long out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject
     *            the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the payment_type
     */
    public String getPayment_type() {
        return payment_type;
    }

    /**
     * @param payment_type
     *            the payment_type to set
     */
    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    /**
     * @return the trade_no
     */
    public String getTrade_no() {
        return trade_no;
    }

    /**
     * @param trade_no
     *            the trade_no to set
     */
    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    /**
     * @return the trade_status
     */
    public AlipayTradeStatus getTrade_status() {
        return trade_status;
    }

    /**
     * @param trade_status
     *            the trade_status to set
     */
    public void setTrade_status(AlipayTradeStatus trade_status) {
        this.trade_status = trade_status;
    }

    /**
     * @return the seller_id
     */
    public String getSeller_id() {
        return seller_id;
    }

    /**
     * @param seller_id
     *            the seller_id to set
     */
    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    /**
     * @return the buyer_id
     */
    public String getBuyer_id() {
        return buyer_id;
    }

    /**
     * @param buyer_id
     *            the buyer_id to set
     */
    public void setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id;
    }

    /**
     * @return the buyer_email
     */
    public String getBuyer_email() {
        return buyer_email;
    }

    /**
     * @param buyer_email
     *            the buyer_email to set
     */
    public void setBuyer_email(String buyer_email) {
        this.buyer_email = buyer_email;
    }

    /**
     * @return the total_fee
     */
    public BigDecimal getTotal_fee() {
        return total_fee;
    }

    /**
     * @param total_fee
     *            the total_fee to set
     */
    public void setTotal_fee(BigDecimal total_fee) {
        this.total_fee = total_fee;
    }

}
