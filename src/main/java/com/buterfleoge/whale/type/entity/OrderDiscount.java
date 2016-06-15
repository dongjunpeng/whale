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
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.buterfleoge.whale.BaseObject;
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
@Table(name = "order_discount")
public class OrderDiscount extends BaseObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_discountid")
    private Long orderDiscountid;

    @Column(name = "orderid")
    private Long orderid;

    @Column(name = "discount_code")
    private String discountCode;

    @Column(name = "discountid")
    private Long discountid;

    @Column(name = "type")
    private DiscountType type;

    @NumberFormat(style = Style.CURRENCY)
    @Column(name = "value")
    @Convert(converter = PriceConverter.class)
    private BigDecimal value;

    @Column(name = "description")
    private String desc;

    @DateTimeFormat(iso = ISO.DATE)
    @Column(name = "add_time")
    @Convert(converter = DateTimeConverter.class)
    private Date addTime;

    /**
     * @return the orderDiscountid
     */
    public Long getOrderDiscountid() {
        return orderDiscountid;
    }

    /**
     * @param orderDiscountid the orderDiscountid to set
     */
    public void setOrderDiscountid(Long orderDiscountid) {
        this.orderDiscountid = orderDiscountid;
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
