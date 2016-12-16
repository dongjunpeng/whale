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
@Table(name = "share")
public class Share extends BaseObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "shareid")
    private Long shareid;

    @Column(name = "source")
    private String source;

    @Column(name = "channel")
    private String channel;

    @Column(name = "routeid")
    private Long routeid;

    @Column(name = "link")
    private String link;

    @Column(name = "accountid")
    private Long accountid;

    @Column(name = "ip")
    private String ip;

    @DateTimeFormat(pattern = Pattern.DATE_TIME)
    @Column(name = "time")
    @Convert(converter = DateTimeConverter.class)
    private Date time;

    @Column(name = "device")
    private Integer device;

    public static final Share create(String source, String channel, Long routeid, String link, Long accountid, String ip, Integer device) {
        Share share = new Share();
        share.source = source;
        share.channel = channel;
        share.routeid = routeid;
        share.link = link;
        share.accountid = accountid;
        share.ip = ip;
        share.time = new Date();
        share.device = device;
        return share;
    }

    /**
     * @return the shareid
     */
    public Long getShareid() {
        return shareid;
    }

    /**
     * @param shareid
     *            the shareid to set
     */
    public void setShareid(Long shareid) {
        this.shareid = shareid;
    }

    /**
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source
     *            the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return the channel
     */
    public String getChannel() {
        return channel;
    }

    /**
     * @param channel
     *            the channel to set
     */
    public void setChannel(String channel) {
        this.channel = channel;
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
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link
     *            the link to set
     */
    public void setLink(String link) {
        this.link = link;
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
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip
     *            the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the time
     */
    public Date getTime() {
        return time;
    }

    /**
     * @param time
     *            the time to set
     */
    public void setTime(Date time) {
        this.time = time;
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

}
