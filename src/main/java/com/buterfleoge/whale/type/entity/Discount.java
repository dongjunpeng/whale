/**
 * 
 */
package com.buterfleoge.whale.type.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.type.enums.DiscountType;

/**
 * 订单优惠表
 * 
 * @author Brent24
 *
 */
@Entity
@Table(name = "discount")
public class Discount extends BaseObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "discountid")
    private Long discountid;

    @Column(name = "type")
    private DiscountType type;

    @Column(name = "routeid")
    private Long routeid;

    @Column(name = "value")
    private Long value;

    @Column(name = "description")
    private String desc;

    @Column(name = "start_time")
    private Long startTime;

    @Column(name = "end_time")
    private Long endTime;

    @Column(name = "add_time")
    private Long addTime;

    public Long getDiscountid() {
        return discountid;
    }

    public void setDiscountid(Long discountid) {
        this.discountid = discountid;
    }

    public DiscountType getType() {
        return type;
    }

    public void setType(DiscountType type) {
        this.type = type;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Long getRouteid() {
        return routeid;
    }

    public void setRouteid(Long routeid) {
        this.routeid = routeid;
    }

}
