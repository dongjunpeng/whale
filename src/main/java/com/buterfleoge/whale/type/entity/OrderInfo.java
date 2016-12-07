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

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.Constants.Pattern;
import com.buterfleoge.whale.type.OrderStatus;
import com.buterfleoge.whale.type.entity.converter.DateTimeConverter;
import com.buterfleoge.whale.type.entity.converter.PriceConverter;

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
    private Integer status;

    @Column(name = "count")
    private Integer count = 0;

    @Column(name = "student_count")
    private Integer studentCount = 0;

    @NumberFormat(style = Style.CURRENCY)
    @Column(name = "price")
    @Convert(converter = PriceConverter.class)
    private BigDecimal price = BigDecimal.ZERO;

    @NumberFormat(style = Style.CURRENCY)
    @Column(name = "actual_price")
    @Convert(converter = PriceConverter.class)
    private BigDecimal actualPrice = BigDecimal.ZERO;

    @Column(name = "is_agreed")
    private Boolean isAgreed;

    @Column(name = "emergency_contact")
    private String emergencyContact;

    @Column(name = "emergency_mobile")
    private String emergencyMobile;

    @Column(name = "roommate")
    private Boolean roommate;

    @DateTimeFormat(pattern = Pattern.DATE)
    @Column(name = "add_time")
    @Convert(converter = DateTimeConverter.class)
    private Date addTime;

    @DateTimeFormat(pattern = Pattern.DATE)
    @Column(name = "create_time")
    @Convert(converter = DateTimeConverter.class)
    private Date createTime;

    @DateTimeFormat(pattern = Pattern.DATE)
    @Column(name = "mod_time")
    @Convert(converter = DateTimeConverter.class)
    private Date modTime;

    /**
     * 获取两小时时限的剩余秒数
     *
     * @return timeLeft
     */
    public long getTimeLeft() {
        return getStatus() != OrderStatus.WAITING.value || createTime == null ? 0L
                : (DateUtils.addHours(createTime, 2).getTime() - System.currentTimeMillis()) / DateUtils.MILLIS_PER_SECOND;
    }

    /**
     * 获取订单编号
     *
     * @return orderNo
     */
    public String getOrderNo() {
        return DateFormatUtils.format(addTime, Pattern.ORDER_PREFIX) + orderid;
    }

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
     * @return the routeid
     */
    public Long getRouteid() {
        return routeid;
    }

    /**
     * @param routeid
     *            the routeid to set
     */
    public void setRouteid(Long routeid) {
        this.routeid = routeid;
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
     * @return the studentCount
     */
    public Integer getStudentCount() {
        return studentCount;
    }

    /**
     * @param studentCount
     *            the studentCount to set
     */
    public void setStudentCount(Integer studentCount) {
        this.studentCount = studentCount;
    }

    /**
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price
     *            the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @return the actualPrice
     */
    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    /**
     * @param actualPrice
     *            the actualPrice to set
     */
    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    /**
     * @return the isAgreed
     */
    public Boolean getIsAgreed() {
        return isAgreed;
    }

    /**
     * @param isAgreed
     *            the isAgreed to set
     */
    public void setIsAgreed(Boolean isAgreed) {
        this.isAgreed = isAgreed;
    }

    /**
     * @return the emergencyContact
     */
    public String getEmergencyContact() {
        return emergencyContact;
    }

    /**
     * @param emergencyContact
     *            the emergencyContact to set
     */
    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    /**
     * @return the emergencyMobile
     */
    public String getEmergencyMobile() {
        return emergencyMobile;
    }

    /**
     * @param emergencyMobile
     *            the emergencyMobile to set
     */
    public void setEmergencyMobile(String emergencyMobile) {
        this.emergencyMobile = emergencyMobile;
    }

    /**
     * @return the roommate
     */
    public Boolean getRoommate() {
        return roommate;
    }

    /**
     * @param roommate
     *            the roommate to set
     */
    public void setRoommate(Boolean roommate) {
        this.roommate = roommate;
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

    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     *            the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the modTime
     */
    public Date getModTime() {
        return modTime;
    }

    /**
     * @param modTime
     *            the modTime to set
     */
    public void setModTime(Date modTime) {
        this.modTime = modTime;
    }

}
