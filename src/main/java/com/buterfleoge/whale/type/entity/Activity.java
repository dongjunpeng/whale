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
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.Constants.Pattern;
import com.buterfleoge.whale.type.entity.converter.DateTimeConverter;
import com.buterfleoge.whale.type.entity.converter.JsonObjectConverter;
import com.buterfleoge.whale.type.entity.converter.PriceConverter;
import com.buterfleoge.whale.type.formatter.ImagePathFormat;
import com.buterfleoge.whale.type.formatter.ImagePathFormat.Prefix;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @author xiezhenzong
 *
 */
@Entity
@Table(name = "activity")
public class Activity extends BaseObject {

    public static final Long ACTIVITY_NEW = Long.valueOf(1);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "activityid")
    private Long activityid;

    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String desc;

    @ImagePathFormat(prefix = Prefix.ACTIVITY)
    @Column(name = "wap_img")
    private String wapImg;

    @NumberFormat(style = Style.CURRENCY)
    @Column(name = "value")
    @Convert(converter = PriceConverter.class)
    private BigDecimal value;

    @Column(name = "param")
    @Convert(converter = JsonObjectConverter.class)
    private ObjectNode param;

    @DateTimeFormat(pattern = Pattern.DATE)
    @Column(name = "start_time")
    @Convert(converter = DateTimeConverter.class)
    private Date startTime;

    @DateTimeFormat(pattern = Pattern.DATE)
    @Column(name = "end_time")
    @Convert(converter = DateTimeConverter.class)
    private Date endTime;

    public boolean isOpen() {
        long now = System.currentTimeMillis();
        return startTime.getTime() <= now && now <= endTime.getTime();
    }

    public boolean isClose() {
        long now = System.currentTimeMillis();
        return startTime.getTime() > now || now > endTime.getTime();
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc
     *            the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return the wapImg
     */
    public String getWapImg() {
        return wapImg;
    }

    /**
     * @param wapImg
     *            the wapImg to set
     */
    public void setWapImg(String wapImg) {
        this.wapImg = wapImg;
    }

    /**
     * @return the value
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    /**
     * @return the param
     */
    public ObjectNode getParam() {
        return param;
    }

    /**
     * @param param
     *            the param to set
     */
    public void setParam(ObjectNode param) {
        this.param = param;
    }

    /**
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     *            the startTime to set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
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

}
