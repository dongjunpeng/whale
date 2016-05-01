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

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public Long getAccountid() {
        return accountid;
    }

    public void setAccountid(Long accountid) {
        this.accountid = accountid;
    }

    public Long getRouteid() {
        return routeid;
    }

    public void setRouteid(Long routeid) {
        this.routeid = routeid;
    }

    public Long getGroupid() {
        return groupid;
    }

    public void setGroupid(Long groupid) {
        this.groupid = groupid;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(Long actualPrice) {
        this.actualPrice = actualPrice;
    }

    public Boolean getIsAgreementOk() {
        return isAgreementOk;
    }

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
