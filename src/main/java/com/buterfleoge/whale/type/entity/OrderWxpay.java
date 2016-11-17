package com.buterfleoge.whale.type.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.Constants.Pattern;
import com.buterfleoge.whale.type.WxCode;
import com.buterfleoge.whale.type.entity.converter.DateTimeConverter;
import com.buterfleoge.whale.type.entity.converter.PriceConverter;
import com.buterfleoge.whale.type.entity.converter.WxCodeConverter;

/**
 * @author xiezhenzong
 *
 */
@Entity
@Table(name = "order_wxpay")
public class OrderWxpay extends BaseObject {

    public static final Integer TYPE_NOTIFY = Integer.valueOf(0);
    public static final Integer TYPE_QUERY = Integer.valueOf(1);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "wxpayid")
    private Long wxpayid;

    @Column(name = "orderid")
    private Long orderid;

    @Column(name = "type")
    private Integer type = TYPE_NOTIFY;

    @Column(name = "return_code")
    @Convert(converter = WxCodeConverter.class)
    private WxCode returnCode;

    @Column(name = "result_code")
    @Convert(converter = WxCodeConverter.class)
    private WxCode resultCode;

    @Column(name = "trade_status")
    private String trade_status;

    @Column(name = "openid")
    private String openid;

    @NumberFormat(style = Style.CURRENCY)
    @Column(name = "total_fee")
    @Convert(converter = PriceConverter.class)
    private BigDecimal totalFee;

    @Column(name = "transaction_id")
    private String transactionId;

    @DateTimeFormat(pattern = Pattern.DATE)
    @Column(name = "time_end")
    @Convert(converter = DateTimeConverter.class)
    private Date timeEnd;

    @Column(name = "param")
    private String param;

    @DateTimeFormat(pattern = Pattern.DATE)
    @Column(name = "add_time")
    @Convert(converter = DateTimeConverter.class)
    private Date addTime;

    /**
     * @return the type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return the trade_status
     */
    public String getTrade_status() {
        return trade_status;
    }

    /**
     * @param trade_status
     *            the trade_status to set
     */
    public void setTrade_status(String trade_status) {
        this.trade_status = trade_status;
    }

    /**
     * @return the wxpayid
     */
    public Long getWxpayid() {
        return wxpayid;
    }

    /**
     * @param wxpayid
     *            the wxpayid to set
     */
    public void setWxpayid(Long wxpayid) {
        this.wxpayid = wxpayid;
    }

    /**
     * @return the orderid
     */
    public Long getOrderid() {
        return orderid;
    }

    /**
     * @param orderid
     *            the orderid to set
     */
    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    /**
     * @return the returnCode
     */
    public WxCode getReturnCode() {
        return returnCode;
    }

    /**
     * @param returnCode
     *            the returnCode to set
     */
    public void setReturnCode(WxCode returnCode) {
        this.returnCode = returnCode;
    }

    /**
     * @return the resultCode
     */
    public WxCode getResultCode() {
        return resultCode;
    }

    /**
     * @param resultCode
     *            the resultCode to set
     */
    public void setResultCode(WxCode resultCode) {
        this.resultCode = resultCode;
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
     * @return the totalFee
     */
    public BigDecimal getTotalFee() {
        return totalFee;
    }

    /**
     * @param totalFee
     *            the totalFee to set
     */
    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    /**
     * @return the transactionId
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * @param transactionId
     *            the transactionId to set
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * @return the timeEnd
     */
    public Date getTimeEnd() {
        return timeEnd;
    }

    /**
     * @param timeEnd
     *            the timeEnd to set
     */
    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    /**
     * @return the param
     */
    public String getParam() {
        return param;
    }

    /**
     * @param param
     *            the param to set
     */
    public void setParam(String param) {
        this.param = param;
    }

    /**
     * @return the addTime
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * @param addTime
     *            the addTime to set
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

}
