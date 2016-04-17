package com.buterfleoge.whale.type.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.type.OrderStaffStatus;

/**
 *
 * @author xiezhenzong
 *
 */
@Entity(name = "order_travellers")
public class OrderTravellers extends BaseObject {

    @Id
    @Column(name = "travellerid")
    private Long travellerid;

    @Column(name = "orderid")
    private Long orderid;

    @Column(name = "accountid")
    private Long accountid;

    @Column(name = "contactid")
    private Long contactid;

    @Column(name = "status")
    private OrderStaffStatus status;

    @Column(name = "roommate")
    private String roommate;

    @Column(name = "refound")
    private Long refound;

    /**
     * @return the travellerid
     */
    public Long getTravellerid() {
        return travellerid;
    }

    /**
     * @param travellerid the travellerid to set
     */
    public void setTravellerid(Long travellerid) {
        this.travellerid = travellerid;
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
     * @return the contactid
     */
    public Long getContactid() {
        return contactid;
    }

    /**
     * @param contactid the contactid to set
     */
    public void setContactid(Long contactid) {
        this.contactid = contactid;
    }

    /**
     * @return the status
     */
    public OrderStaffStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(OrderStaffStatus status) {
        this.status = status;
    }

    /**
     * @return the roommate
     */
    public String getRoommate() {
        return roommate;
    }

    /**
     * @param roommate the roommate to set
     */
    public void setRoommate(String roommate) {
        this.roommate = roommate;
    }

    /**
     * @return the refound
     */
    public Long getRefound() {
        return refound;
    }

    /**
     * @param refound the refound to set
     */
    public void setRefound(Long refound) {
        this.refound = refound;
    }

}
