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

import org.apache.commons.lang.time.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.Constants.Pattern;
import com.buterfleoge.whale.type.GroupStatus;
import com.buterfleoge.whale.type.entity.converter.DateTimeConverter;
import com.buterfleoge.whale.type.entity.converter.PriceConverter;

/**
 * 发团信息
 *
 * @author Brent24
 *
 */
@Entity
@Table(name = "travel_group")
public class TravelGroup extends BaseObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "groupid")
    private Long groupid;

    @Column(name = "routeid")
    private Long routeid;

    @DateTimeFormat(pattern = Pattern.DATE)
    @Column(name = "start_date")
    @Convert(converter = DateTimeConverter.class)
    private Date startDate;

    @DateTimeFormat(pattern = Pattern.DATE)
    @Column(name = "end_date")
    @Convert(converter = DateTimeConverter.class)
    private Date endDate;

    @Column(name = "title")
    private String title;

    @NumberFormat(style = Style.CURRENCY)
    @Column(name = "price")
    @Convert(converter = PriceConverter.class)
    private BigDecimal price;

    @Column(name = "status")
    private Integer status;

    @Column(name = "min_count")
    private Integer minCount;

    @Column(name = "max_count")
    private Integer maxCount = 0;

    @Column(name = "actual_count")
    private Integer actualCount = 0;

    @DateTimeFormat(pattern = Pattern.DATE)
    @Column(name = "add_time")
    @Convert(converter = DateTimeConverter.class)
    private Date addTime;

    @DateTimeFormat(pattern = Pattern.DATE)
    @Column(name = "mod_time")
    @Convert(converter = DateTimeConverter.class)
    private Date modTime;

    /**
     * 获取距离出发日期还有几天
     *
     * @return dayLeft
     */
    public long getDayLeft() {
        if (getStatus() == GroupStatus.OPEN.value || getStatus() == GroupStatus.FULL.value) {
            long daycount = getStartDate().getTime() - System.currentTimeMillis();
            return daycount / DateUtils.MILLIS_PER_DAY + (daycount % DateUtils.MILLIS_PER_DAY == 0 ? 0 : 1);
        } else {
            return 0L;
        }
    }

    public Long getGroupid() {
        return groupid;
    }

    public void setGroupid(Long groupid) {
        this.groupid = groupid;
    }

    public Long getRouteid() {
        return routeid;
    }

    public void setRouteid(Long routeid) {
        this.routeid = routeid;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getMinCount() {
        return minCount;
    }

    public void setMinCount(Integer minCount) {
        this.minCount = minCount;
    }

    public Integer getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(Integer maxCount) {
        this.maxCount = maxCount;
    }

    public Integer getActualCount() {
        return actualCount;
    }

    public void setActualCount(Integer actualCount) {
        this.actualCount = actualCount;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getModTime() {
        return modTime;
    }

    public void setModTime(Date modTime) {
        this.modTime = modTime;
    }

    @Override
    public String toString() {
        return "{\"groupid\": " + getGroupid() + "}";
    }
}
