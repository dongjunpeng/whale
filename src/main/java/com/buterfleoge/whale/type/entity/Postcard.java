package com.buterfleoge.whale.type.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.Constants.Pattern;
import com.buterfleoge.whale.type.entity.converter.DateTimeConverter;

/**
 * @author xiezhenzong
 *
 */
@Entity
@Table(name = "postcard")
public class Postcard extends BaseObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "postcardid")
    private Long postcardid;

    @Column(name = "activityid")
    private Long activityid;

    @Column(name = "accountid")
    private Long accountid;

    @Column(name = "address")
    private String address;

    @DateTimeFormat(pattern = Pattern.DATE)
    @Column(name = "add_time")
    @Convert(converter = DateTimeConverter.class)
    private Date addTime;

    public static Postcard create(Long activityid, Long accountid, String address) {
        Postcard postcard  = new Postcard();
        postcard.setActivityid(activityid);
        postcard.setAccountid(accountid);
        postcard.setAddress(address);
        postcard.setAddTime(new Date());
        return postcard;
    }

    /**
     * @return the postcardid
     */
    public Long getPostcardid() {
        return postcardid;
    }

    /**
     * @param postcardid
     *            the postcardid to set
     */
    public void setPostcardid(Long postcardid) {
        this.postcardid = postcardid;
    }

    /**
     * @return the activityid
     */
    public Long getActivityid() {
        return activityid;
    }

    /**
     * @param activityid
     *            the activityid to set
     */
    public void setActivityid(Long activityid) {
        this.activityid = activityid;
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
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     *            the address to set
     */
    public void setAddress(String address) {
        this.address = address;
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
