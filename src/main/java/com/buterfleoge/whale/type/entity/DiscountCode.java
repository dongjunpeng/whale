/**
 *
 */
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
import com.buterfleoge.whale.type.DiscountCodeStatus;
import com.buterfleoge.whale.type.entity.converter.DateTimeConverter;
import com.buterfleoge.whale.type.entity.converter.PriceConverter;

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
    private String discountCode;

    @Column(name = "accountid")
    private Long accountid;

    @Column(name = "status")
    private DiscountCodeStatus status;

    @Column(name = "agent")
    private Long agent;

    @NumberFormat(style = Style.CURRENCY)
    @Column(name = "value")
    @Convert(converter = PriceConverter.class)
    private BigDecimal value;

    @DateTimeFormat(pattern = Pattern.DATE)
    @Column(name = "start_time")
    @Convert(converter = DateTimeConverter.class)
    private Date startTime;

    @DateTimeFormat(pattern = Pattern.DATE)
    @Column(name = "end_time")
    @Convert(converter = DateTimeConverter.class)
    private Date endTime;

    @DateTimeFormat(pattern = Pattern.DATE)
    @Column(name = "add_time")
    @Convert(converter = DateTimeConverter.class)
    private Date addTime;

    @DateTimeFormat(pattern = Pattern.DATE)
    @Column(name = "effect_time")
    @Convert(converter = DateTimeConverter.class)
    private Date effectTime;

    /**
     * @return the codeid
     */
    public Long getCodeid() {
        return codeid;
    }

    /**
     * @param codeid the codeid to set
     */
    public void setCodeid(Long codeid) {
        this.codeid = codeid;
    }

    /**
     * @return the discountCode
     */
    public String getDiscountCode() {
        return discountCode;
    }

    /**
     * @param discountCode the discountCode to set
     */
    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    /**
     * @return the accountid
     */
    public Long getAccountid() {
        return accountid;
    }

    /**
     * @param accountid the accountid to set
     */
    public void setAccountid(Long accountid) {
        this.accountid = accountid;
    }

    /**
     * @return the status
     */
    public DiscountCodeStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(DiscountCodeStatus status) {
        this.status = status;
    }

    /**
     * @return the agent
     */
    public Long getAgent() {
        return agent;
    }

    /**
     * @param agent the agent to set
     */
    public void setAgent(Long agent) {
        this.agent = agent;
    }

    /**
     * @return the value
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    /**
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
     * @return the effectTime
     */
    public Date getEffectTime() {
        return effectTime;
    }

    /**
     * @param effectTime the effectTime to set
     */
    public void setEffectTime(Date effectTime) {
        this.effectTime = effectTime;
    }

}
