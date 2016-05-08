package com.buterfleoge.whale.type.protocol.order.object;

import java.util.Set;

import com.buterfleoge.whale.type.enums.OrderStatus;

/**
 * @author Brent24
 *
 */
public class BriefOrder implements Comparable<BriefOrder> {

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
    private Long orderid;
    private OrderStatus status;
    private Long actualPrice;
    // travellers + AccountSetting
    private Set<String> names;
    private Set<String> avatars;

    public BriefOrder(Long routeid, String name, String title, String headImg, String startDate, String endDate,
            String wxQrCode, Long dayCount, Long minuteCount, Long orderid, OrderStatus status, Long actualPrice,
            Set<String> names, Set<String> avatars) {
        this.routeid = routeid;
        this.name = name;
        this.title = title;
        this.headImg = headImg;
        this.startDate = startDate;
        this.endDate = endDate;
        this.wxQrCode = wxQrCode;
        this.dayCount = dayCount;
        this.minuteCount = minuteCount;
        this.orderid = orderid;
        this.status = status;
        this.actualPrice = actualPrice;
        this.names = names;
        this.avatars = avatars;
    }

    // sort排序顺序
    public Integer getOrder() {
        switch (status) {
        case PAID:
            return 1;
        case WAITING:
            return 2;
        case PAYING:
            return 3;
        case REFOUNDING:
            return 4;
        case REFOUNDED:
            return 5;
        case FINISH:
            return 6;
        case CANCELPAYMENT:
            return 7;
        case CANCEl:
            return 8;
        case TIMEOUT:
            return 9;
        default:
            return 100;
        }
    }

    @Override
    public int compareTo(BriefOrder arg0) {
        return this.getOrder().compareTo(arg0.getOrder());
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

    public Long getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(Long actualPrice) {
        this.actualPrice = actualPrice;
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

}
