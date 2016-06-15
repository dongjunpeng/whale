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
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.type.RefoundStatus;
import com.buterfleoge.whale.type.RefoundType;
import com.buterfleoge.whale.type.entity.converter.DateTimeConverter;
import com.buterfleoge.whale.type.entity.converter.PriceConverter;

/**
 * 订单退款表
 * 
 * @author Brent24
 *
 */
@Entity
@Table(name = "order_refound")
public class OrderRefound extends BaseObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "refoundid")
    private Long refoundid;

    @Column(name = "orderid")
    private Long orderid;

    @Column(name = "status")
    private RefoundStatus status;

    @Column(name = "type")
    private RefoundType type;

    @NumberFormat(style = Style.CURRENCY)
    @Column(name = "refound")
    @Convert(converter = PriceConverter.class)
    private BigDecimal refound;

    @Column(name = "description")
    private String desc;

    @DateTimeFormat(iso = ISO.DATE)
    @Column(name = "add_time")
    @Convert(converter = DateTimeConverter.class)
    private Date addTime;

    @DateTimeFormat(iso = ISO.DATE)
    @Column(name = "finish_time")
    @Convert(converter = DateTimeConverter.class)
    private Date finishTime;

    /**
     * @return the refoundid
     */
    public Long getRefoundid() {
        return refoundid;
    }

    /**
     * @param refoundid the refoundid to set
     */
    public void setRefoundid(Long refoundid) {
        this.refoundid = refoundid;
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
    public RefoundStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(RefoundStatus status) {
        this.status = status;
    }

    /**
     * @return the type
     */
    public RefoundType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(RefoundType type) {
        this.type = type;
    }

    /**
     * @return the refound
     */
    public BigDecimal getRefound() {
        return refound;
    }

    /**
     * @param refound the refound to set
     */
    public void setRefound(BigDecimal refound) {
        this.refound = refound;
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
