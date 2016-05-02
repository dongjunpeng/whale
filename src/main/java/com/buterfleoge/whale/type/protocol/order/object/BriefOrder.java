package com.buterfleoge.whale.type.protocol.order.object;

import java.util.Set;

import com.buterfleoge.whale.type.enums.OrderStatus;

/**
 * @author Brent24
 *
 */
public class BriefOrder {

    // TravelRoute
    private Long routeid;
    private String name;
    private String title;
    private String headImg;
    // TravelGroup
    private String startDate;
    private String endDate;
    private String wxQrCode;
    private Long dayCount;
    // OrderInfo
    private Long minuteCount;
    private OrderStatus status;
    // travellers + AccountSetting
    private Set<String> names;
    private Set<String> avatars;

    public BriefOrder(Long routeid, String name, String title, String headImg, String startDate, String endDate,
            String wxQrCode, Long dayCount, Long minuteCount, OrderStatus status, Set<String> names,
            Set<String> avatars) {
        this.routeid = routeid;
        this.name = name;
        this.title = title;
        this.headImg = headImg;
        this.startDate = startDate;
        this.endDate = endDate;
        this.wxQrCode = wxQrCode;
        this.dayCount = dayCount;
        this.minuteCount = minuteCount;
        this.status = status;
        this.names = names;
        this.avatars = avatars;

    }

    public Long getRouteid() {
        return routeid;
    }

    public void setRouteid(Long routeid) {
        this.routeid = routeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Long getDayCount() {
        return dayCount;
    }

    public void setDayCount(Long dayCount) {
        this.dayCount = dayCount;
    }

    public Long getMinuteCount() {
        return minuteCount;
    }

    public void setMinuteCount(Long minuteCount) {
        this.minuteCount = minuteCount;
    }

    public Set<String> getAvatars() {
        return avatars;
    }

    public void setAvatars(Set<String> avatars) {
        this.avatars = avatars;
    }

    public String getWxQrCode() {
        return wxQrCode;
    }

    public void setWxQrCode(String wxQrCode) {
        this.wxQrCode = wxQrCode;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Set<String> getNames() {
        return names;
    }

    public void setNames(Set<String> names) {
        this.names = names;
    }

}
