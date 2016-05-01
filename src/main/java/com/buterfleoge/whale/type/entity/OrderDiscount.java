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
@Table(name = "order_discount")
public class OrderDiscount extends BaseObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_discountid")
    private Long orderDiscountid;

    @Column(name = "orderid")
    private Long orderid;

    @Column(name = "discountid")
    private Long discountid;

    @Column(name = "type")
    private DiscountType type;

    @Column(name = "discount_code")
    private Long discountCode;

    @Column(name = "discount_price")
    private Long discountPrice;

    @Column(name = "desc")
    private String desc;

    @Column(name = "add_time")
    private Long addTime;

    public Long getOrderDiscountid() {
        return orderDiscountid;
    }

    public void setOrderDiscountid(Long orderDiscountid) {
        this.orderDiscountid = orderDiscountid;
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

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

    public Long getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(Long discountCode) {
        this.discountCode = discountCode;
    }

    public Long getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Long discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

}
