package com.buterfleoge.whale.type.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.type.enums.RefoundStatus;
import com.buterfleoge.whale.type.enums.RefoundType;

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

    @Column(name = "refound")
    private Long refound;

    @Column(name = "description")
    private String desc;

    @Column(name = "add_time")
    private Long addTime;

    @Column(name = "finish_time")
    private Long finishTime;

    public Long getRefoundid() {
        return refoundid;
    }

    public void setRefoundid(Long refoundid) {
        this.refoundid = refoundid;
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public RefoundStatus getStatus() {
        return status;
    }

    public void setStatus(RefoundStatus status) {
        this.status = status;
    }

    public RefoundType getType() {
        return type;
    }

    public void setType(RefoundType type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getRefound() {
        return refound;
    }

    public void setRefound(Long refound) {
        this.refound = refound;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Long getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Long finishTime) {
        this.finishTime = finishTime;
    }

}
