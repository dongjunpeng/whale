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
import com.buterfleoge.whale.type.entity.converter.DateTimeConverter;
import com.buterfleoge.whale.type.entity.converter.PriceConverter;

/**
 * 优惠券
 *
 * @author xiezhenzong
 *
 */
@Entity
@Table(name = "coupon")
public class Coupon extends BaseObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "codeid")
    private Long codeid;

    @Column(name = "type")
    private Integer type;

    @Column(name = "status")
    private Integer status;

    @NumberFormat(style = Style.CURRENCY)
    @Column(name = "value")
    @Convert(converter = PriceConverter.class)
    private BigDecimal value;

    @Column(name = "discount_code")
    private String discountCode;

    @Column(name = "accountid")
    private Long accountid;

    @Column(name = "agent")
    private Long agent;

    @DateTimeFormat(pattern = Pattern.DATE)
    @Column(name = "start_time")
    @Convert(converter = DateTimeConverter.class)
    private Date startTime;

    @DateTimeFormat(pattern = Pattern.DATE)
    @Column(name = "end_time")
    @Convert(converter = DateTimeConverter.class)
    private Date endTime;

    @DateTimeFormat(pattern = Pattern.DATE)
    @Column(name = "valide_time")
    @Convert(converter = DateTimeConverter.class)
    private Date valideTime;

    @DateTimeFormat(pattern = Pattern.DATE)
    @Column(name = "use_time")
    @Convert(converter = DateTimeConverter.class)
    private Date useTime;

    @DateTimeFormat(pattern = Pattern.DATE)
    @Column(name = "add_time")
    @Convert(converter = DateTimeConverter.class)
    private Date addTime;

    /**
     * @return the codeid
     */
    public Long getCodeid() {
        return codeid;
    }

    /**
     * @param codeid
     *            the codeid to set
     */
    public void setCodeid(Long codeid) {
        this.codeid = codeid;
    }

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
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return the value
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    /**
     * @return the discountCode
     */
    public String getDiscountCode() {
        return discountCode;
    }

    /**
     * @param discountCode
     *            the discountCode to set
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
     * @param accountid
     *            the accountid to set
     */
    public void setAccountid(Long accountid) {
        this.accountid = accountid;
    }

    /**
     * @return the agent
     */
    public Long getAgent() {
        return agent;
    }

    /**
     * @param agent
     *            the agent to set
     */
    public void setAgent(Long agent) {
        this.agent = agent;
    }

    /**
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     *            the startTime to set
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
     * @param endTime
     *            the endTime to set
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the valideTime
     */
    public Date getValideTime() {
        return valideTime;
    }

    /**
     * @param valideTime
     *            the valideTime to set
     */
    public void setValideTime(Date valideTime) {
        this.valideTime = valideTime;
    }

    /**
     * @return the useTime
     */
    public Date getUseTime() {
        return useTime;
    }

    /**
     * @param useTime
     *            the useTime to set
     */
    public void setUseTime(Date useTime) {
        this.useTime = useTime;
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
