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
import com.buterfleoge.whale.type.DiscountType;
import com.buterfleoge.whale.type.entity.converter.DateTimeConverter;
import com.buterfleoge.whale.type.entity.converter.PriceConverter;

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

    @NumberFormat(style = Style.CURRENCY)
    @Column(name = "value")
    @Convert(converter = PriceConverter.class)
    private BigDecimal value;

    @Column(name = "description")
    private String desc;

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

    /**
     * @return the discountid
     */
    public Long getDiscountid() {
        return discountid;
    }

    /**
     * @param discountid the discountid to set
     */
    public void setDiscountid(Long discountid) {
        this.discountid = discountid;
    }

    /**
     * @return the type
     */
    public DiscountType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(DiscountType type) {
        this.type = type;
    }

    /**
     * @return the routeid
     */
    public Long getRouteid() {
        return routeid;
    }

    /**
     * @param routeid the routeid to set
     */
    public void setRouteid(Long routeid) {
        this.routeid = routeid;
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

}
