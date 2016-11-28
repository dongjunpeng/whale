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
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @author xiezhenzong
 *
 */
@Entity
@Table(name = "activity")
public class Activity extends BaseObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "activity")
    private Long activity;

    @Column(name = "name")
    private String name;

    @Column(name = "param")
    private ObjectNode param;

    @DateTimeFormat(pattern = Pattern.DATE)
    @Column(name = "start_time")
    @Convert(converter = DateTimeConverter.class)
    private Date startTime;

    @DateTimeFormat(pattern = Pattern.DATE)
    @Column(name = "end_time")
    @Convert(converter = DateTimeConverter.class)
    private Date endTime;

    /**
     * @return the activity
     */
    public Long getActivity() {
        return activity;
    }

    /**
     * @param activity
     *            the activity to set
     */
    public void setActivity(Long activity) {
        this.activity = activity;
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
