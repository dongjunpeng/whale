package com.buterfleoge.whale.type.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.buterfleoge.whale.BaseObject;
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

    @Column(name = "type")
    private RefoundType type;

    @Column(name = "description")
    private String desc;

    @Column(name = "refound")
    private Long refound;

    @Column(name = "add_time")
    private Long addTime;

    /**
     * @return the refoundid
     */
    public Long getRefoundid() {
        return refoundid;
    }

    /**
     * @param refoundid
     *            the refoundid to set
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
     * @param orderid
     *            the orderid to set
     */
    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    /**
     * @return the type
     */
    public RefoundType getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(RefoundType type) {
        this.type = type;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc
     *            the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return the refound
     */
    public Long getRefound() {
        return refound;
    }

    /**
     * @param refound
     *            the refound to set
     */
    public void setRefound(Long refound) {
        this.refound = refound;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

}
