package com.buterfleoge.whale.type.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.type.enums.OrderStatus;

/**
 * 订单信息
 *
 * @author xiezhenzong
 *
 */
@Entity
@Table(name = "order_info")
public class OrderInfo extends BaseObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderid")
    private Long orderid;

    @Column(name = "accountid")
    private Long accountid;

    @Column(name = "routeid")
    private Long routeid;

    @Column(name = "groupid")
    private Long groupid;

    @Column(name = "status")
    private OrderStatus status;

    /**
     * 本订单的人数
     */
    @Column(name = "count")
    private Integer count;

    /**
     * 价格
     */
    @Column(name = "price")
    private Long price;

    /**
     * 实际付款
     */
    @Column(name = "actual_price")
    private Long actualPrice;

    @Column(name = "is_agreement_ok")
    private Boolean isAgreementOk;

    @Column(name = "add_time")
    private Long addTime;

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
     * @return the groupid
     */
    public Long getGroupid() {
        return groupid;
    }

    /**
     * @param groupid
     *            the groupid to set
     */
    public void setGroupid(Long groupid) {
        this.groupid = groupid;
    }

    /**
     * @return the status
     */
    public OrderStatus getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    /**
     * @return the count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * @param count
     *            the count to set
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * @return the price
     */
    public Long getPrice() {
        return price;
    }

    /**
     * @param price
     *            the price to set
     */
    public void setPrice(Long price) {
        this.price = price;
    }

    /**
     * @return the actualPrice
     */
    public Long getActualPrice() {
        return actualPrice;
    }

    /**
     * @param actualPrice
     *            the actualPrice to set
     */
    public void setActualPrice(Long actualPrice) {
        this.actualPrice = actualPrice;
    }

    /**
     * @return the isAgreementOk
     */
    public Boolean getIsAgreementOk() {
        return isAgreementOk;
    }

    /**
     * @param isAgreementOk
     *            the isAgreementOk to set
     */
    public void setIsAgreementOk(Boolean isAgreementOk) {
        this.isAgreementOk = isAgreementOk;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

}
