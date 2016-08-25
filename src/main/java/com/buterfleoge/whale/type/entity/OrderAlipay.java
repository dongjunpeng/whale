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

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.type.AlipayRefundStatus;
import com.buterfleoge.whale.type.AlipayTradeStatus;
import com.buterfleoge.whale.type.entity.converter.AlipayNotifyTypeConverter;
import com.buterfleoge.whale.type.entity.converter.AlipayRefundStatusConverter;
import com.buterfleoge.whale.type.entity.converter.AlipayTradeStatusConverter;
import com.buterfleoge.whale.type.entity.converter.DateTimeConverter;
import com.buterfleoge.whale.type.entity.converter.PriceConverter;

/**
 * @author xiezhenzong
 *
 */
@Entity
@Table(name = "order_alipay")
public class OrderAlipay extends BaseObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "alipayid")
    private Long alipayid;

    @Column(name = "orderid")
    private Long orderid;

    @Column(name = "trade_no")
    private String tradeNo;

    @Column(name = "trade_status")
    @Convert(converter = AlipayTradeStatusConverter.class)
    private AlipayTradeStatus tradeStatus;

    @Column(name = "notify_id")
    private String notifyId;

    @Column(name="notify_type")
    @Convert(converter = AlipayNotifyTypeConverter.class)
    private String notifyType;

    @Column(name = "notify_time")
    @Convert(converter = DateTimeConverter.class)
    private Date notifyTime;

    @Column(name = "total_fee")
    @Convert(converter = PriceConverter.class)
    private BigDecimal totalFee;

    @Column(name = "seller_id")
    private String sellerId;

    @Column(name = "buyer_id")
    private String buyerId;

    @Column(name = "buyer_email")
    private String buyerEmail;

    @Column(name = "gmt_create")
    @Convert(converter = DateTimeConverter.class)
    private Date gmtCreate;

    @Column(name = "gmt_payment")
    @Convert(converter = DateTimeConverter.class)
    private Date gmtPayment;

    @Column(name = "gmt_close")
    @Convert(converter = DateTimeConverter.class)
    private Date gmtClose;

    @Column(name = "refund_status")
    @Convert(converter = AlipayRefundStatusConverter.class)
    private AlipayRefundStatus refundStatus;

    @Column(name = "gmt_refund")
    @Convert(converter = DateTimeConverter.class)
    private Date gmtRefund;

    @Column(name = "add_time")
    @Convert(converter = DateTimeConverter.class)
    private Date addTime;

    /**
     * @return the alipayid
     */
    public Long getAlipayid() {
        return alipayid;
    }

    /**
     * @param alipayid
     *            the alipayid to set
     */
    public void setAlipayid(Long alipayid) {
        this.alipayid = alipayid;
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
     * @return the tradeNo
     */
    public String getTradeNo() {
        return tradeNo;
    }

    /**
     * @param tradeNo
     *            the tradeNo to set
     */
    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    /**
     * @return the tradeStatus
     */
    public AlipayTradeStatus getTradeStatus() {
        return tradeStatus;
    }

    /**
     * @param tradeStatus
     *            the tradeStatus to set
     */
    public void setTradeStatus(AlipayTradeStatus tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    /**
     * @return the notifyId
     */
    public String getNotifyId() {
        return notifyId;
    }

    /**
     * @param notifyId
     *            the notifyId to set
     */
    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId;
    }

    /**
     * @return the notifyType
     */
    public String getNotifyType() {
        return notifyType;
    }

    /**
     * @param notifyType
     *            the notifyType to set
     */
    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    /**
     * @return the notifyTime
     */
    public Date getNotifyTime() {
        return notifyTime;
    }

    /**
     * @param notifyTime
     *            the notifyTime to set
     */
    public void setNotifyTime(Date notifyTime) {
        this.notifyTime = notifyTime;
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
     * @return the sellerId
     */
    public String getSellerId() {
        return sellerId;
    }

    /**
     * @param sellerId
     *            the sellerId to set
     */
    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    /**
     * @return the buyerId
     */
    public String getBuyerId() {
        return buyerId;
    }

    /**
     * @param buyerId
     *            the buyerId to set
     */
    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    /**
     * @return the buyerEmail
     */
    public String getBuyerEmail() {
        return buyerEmail;
    }

    /**
     * @param buyerEmail
     *            the buyerEmail to set
     */
    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    /**
     * @return the gmtCreate
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * @param gmtCreate
     *            the gmtCreate to set
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * @return the gmtPayment
     */
    public Date getGmtPayment() {
        return gmtPayment;
    }

    /**
     * @param gmtPayment
     *            the gmtPayment to set
     */
    public void setGmtPayment(Date gmtPayment) {
        this.gmtPayment = gmtPayment;
    }

    /**
     * @return the gmtClose
     */
    public Date getGmtClose() {
        return gmtClose;
    }

    /**
     * @param gmtClose
     *            the gmtClose to set
     */
    public void setGmtClose(Date gmtClose) {
        this.gmtClose = gmtClose;
    }

    /**
     * @return the refundStatus
     */
    public AlipayRefundStatus getRefundStatus() {
        return refundStatus;
    }

    /**
     * @param refundStatus
     *            the refundStatus to set
     */
    public void setRefundStatus(AlipayRefundStatus refundStatus) {
        this.refundStatus = refundStatus;
    }

    /**
     * @return the gmtRefund
     */
    public Date getGmtRefund() {
        return gmtRefund;
    }

    /**
     * @param gmtRefund
     *            the gmtRefund to set
     */
    public void setGmtRefund(Date gmtRefund) {
        this.gmtRefund = gmtRefund;
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
