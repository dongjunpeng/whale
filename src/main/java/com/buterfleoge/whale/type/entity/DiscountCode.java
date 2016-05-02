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
import com.buterfleoge.whale.type.enums.DiscountCodeStatus;

/**
 * 订单优惠表
 * 
 * @author Brent24
 *
 */
@Entity
@Table(name = "discount_code")
public class DiscountCode extends BaseObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "codeid")
    private Long codeid;

    @Column(name = "discount_code")
    private Long discountCode;

    @Column(name = "accountid")
    private Long accountid;

    @Column(name = "status")
    private DiscountCodeStatus status;

    @Column(name = "agent")
    private Long agent;

    @Column(name = "value")
    private Long value;

    @Column(name = "start_time")
    private Long startTime;

    @Column(name = "end_time")
    private Long endTime;

    @Column(name = "add_time")
    private Long addTime;

    @Column(name = "effect_time")
    private Long effectTime;

    public Long getCodeid() {
        return codeid;
    }

    public void setCodeid(Long codeid) {
        this.codeid = codeid;
    }

    public Long getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(Long discountCode) {
        this.discountCode = discountCode;
    }

    public Long getAccountid() {
        return accountid;
    }

    public void setAccountid(Long accountid) {
        this.accountid = accountid;
    }

    public DiscountCodeStatus getStatus() {
        return status;
    }

    public void setStatus(DiscountCodeStatus status) {
        this.status = status;
    }

    public Long getAgent() {
        return agent;
    }

    public void setAgent(Long agent) {
        this.agent = agent;
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

    public Long getEffectTime() {
        return effectTime;
    }

    public void setEffectTime(Long effectTime) {
        this.effectTime = effectTime;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

}
