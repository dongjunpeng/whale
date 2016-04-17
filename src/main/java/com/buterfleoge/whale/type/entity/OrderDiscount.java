/**
 * 
 */
package com.buterfleoge.whale.type.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.type.DiscountType;

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
    @Column(name = "discountid")
    private Long discountid;

    @Column(name = "orderid")
    private Long orderid;

    @Column(name = "type")
    private DiscountType type;

    @Column(name = "discount_price")
    private Long discountPrice;

    @Column(name = "desc")
    private String desc;

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
     * @return the discountPrice
     */
    public Long getDiscountPrice() {
        return discountPrice;
    }

    /**
     * @param discountPrice the discountPrice to set
     */
    public void setDiscountPrice(Long discountPrice) {
        this.discountPrice = discountPrice;
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

}
