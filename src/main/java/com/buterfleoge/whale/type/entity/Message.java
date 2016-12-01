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
import com.buterfleoge.whale.Constants;
import com.buterfleoge.whale.Constants.Pattern;
import com.buterfleoge.whale.type.Device;
import com.buterfleoge.whale.type.MessageStatus;
import com.buterfleoge.whale.type.MessageType;
import com.buterfleoge.whale.type.entity.converter.DateTimeConverter;

/**
 * @author xiezhenzong
 *
 */
@Entity
@Table(name = "message")
public class Message extends BaseObject {

    public static final Long SOURCEID_SYSTEM = Long.valueOf(0);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "messageid")
    private Long messageid;

    @Column(name = "accountid")
    private Long accountid;

    @Column(name = "sourceid")
    private Long sourceid;

    @Column(name = "type")
    private Integer type;

    @Column(name = "status")
    private Integer status;

    @Column(name = "device")
    private Integer device;

    @Column(name = "mssage")
    private String message;

    @DateTimeFormat(pattern = Pattern.DATE)
    @Column(name = "end_time")
    @Convert(converter = DateTimeConverter.class)
    private Date endTime;

    @DateTimeFormat(pattern = Pattern.DATE)
    @Column(name = "add_time")
    @Convert(converter = DateTimeConverter.class)
    private Date addTime;

    public static Message createSystemModalMessage(Long accountid, String message) {
        Message m = new Message();
        m.setAccountid(accountid);
        m.setSourceid(SOURCEID_SYSTEM);
        m.setType(MessageType.MODAL.value);
        m.setStatus(MessageStatus.NEW.value);
        m.setDevice(Device.ALL.value);
        m.setMessage(message);
        m.setEndTime(Constants.FUTURE);
        m.setAddTime(new Date());
        return m;
    }

    /**
     * @return the messageid
     */
    public Long getMessageid() {
        return messageid;
    }

    /**
     * @param messageid
     *            the messageid to set
     */
    public void setMessageid(Long messageid) {
        this.messageid = messageid;
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
     * @return the sourceid
     */
    public Long getSourceid() {
        return sourceid;
    }

    /**
     * @param sourceid
     *            the sourceid to set
     */
    public void setSourceid(Long sourceid) {
        this.sourceid = sourceid;
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
     * @return the device
     */
    public Integer getDevice() {
        return device;
    }

    /**
     * @param device
     *            the device to set
     */
    public void setDevice(Integer device) {
        this.device = device;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(String message) {
        this.message = message;
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
