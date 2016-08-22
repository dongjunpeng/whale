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
import com.buterfleoge.whale.type.entity.converter.DateTimeConverter;
import com.buterfleoge.whale.type.entity.converter.PriceConverter;

/**
 * 订单退款表
 *
 * @author Brent24
 *
 */
@Entity
@Table(name = "order_refund")
public class OrderRefund extends BaseObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "refundid")
    private Long refundid;

    @Column(name = "orderid")
    private Long orderid;

    @Column(name = "status")
    private Integer status;

    @Column(name = "type")
    private Integer type;

    @NumberFormat(style = Style.CURRENCY)
    @Column(name = "refund")
    @Convert(converter = PriceConverter.class)
    private BigDecimal refund;

    @Column(name = "description")
    private String desc;

    @DateTimeFormat(pattern = Pattern.DATE)
    @Column(name = "add_time")
    @Convert(converter = DateTimeConverter.class)
    private Date addTime;

    @DateTimeFormat(pattern = Pattern.DATE)
    @Column(name = "finish_time")
    @Convert(converter = DateTimeConverter.class)
    private Date finishTime;


    /**
     * @return the refundid
     */
    public Long getRefundid() {
        return refundid;
    }

    /**
     * @param refundid
     *            the refundid to set
     */
    public void setRefundid(Long refundid) {
        this.refundid = refundid;
    }

    /**
     * @return the orderid
     */
    public Long getOrderid() {
        return orderid;
    }

    /**
     * @param orderid the orderid to set
     */
    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return the type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return the refund
     */
    public BigDecimal getRefund() {
        return refund;
    }

    /**
     * @param refund
     *            the refund to set
     */
    public void setRefund(BigDecimal refund) {
        this.refund = refund;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return the addTime
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * @param addTime the addTime to set
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * @return the finishTime
     */
    public Date getFinishTime() {
        return finishTime;
    }

    /**
     * @param finishTime the finishTime to set
     */
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

}
