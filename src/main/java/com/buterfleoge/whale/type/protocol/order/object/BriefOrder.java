package com.buterfleoge.whale.type.protocol.order.object;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.buterfleoge.whale.type.enums.OrderStatus;
import com.buterfleoge.whale.type.formatter.ImagePathFormat;
import com.buterfleoge.whale.type.formatter.ImagePathFormat.Prefix;

/**
 * @author Brent24
 *
 */
public class BriefOrder implements Comparable<BriefOrder> {

    private Long routeid;
    private String name;
    private String title;
    @ImagePathFormat(prefix = Prefix.ROUTE)
    private String headImg;

    @DateTimeFormat(iso = ISO.DATE)
    private Date startDate;
    @DateTimeFormat(iso = ISO.DATE)
    private Date endDate;
    private String wxQrCode;
    private Long dayCount;

    private Long minuteCount;
    private Long orderid;
    private OrderStatus status;
    @NumberFormat(style = Style.CURRENCY)
    private BigDecimal actualPrice;

    private Set<String> names;
    private Set<String> avatars;

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
        case CANCEL:
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

    /**
     * @return the routeid
     */
    public Long getRouteid() {
        return routeid;
    }

    /**
     * @param routeid the routeid to set
     */
    public void setRouteid(Long routeid) {
        this.routeid = routeid;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
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
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the headImg
     */
    public String getHeadImg() {
        return headImg;
    }

    /**
     * @param headImg the headImg to set
     */
    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the wxQrCode
     */
    public String getWxQrCode() {
        return wxQrCode;
    }

    /**
     * @param wxQrCode the wxQrCode to set
     */
    public void setWxQrCode(String wxQrCode) {
        this.wxQrCode = wxQrCode;
    }

    /**
     * @return the dayCount
     */
    public Long getDayCount() {
        return dayCount;
    }

    /**
     * @param dayCount the dayCount to set
     */
    public void setDayCount(Long dayCount) {
        this.dayCount = dayCount;
    }

    /**
     * @return the minuteCount
     */
    public Long getMinuteCount() {
        return minuteCount;
    }

    /**
     * @param minuteCount the minuteCount to set
     */
    public void setMinuteCount(Long minuteCount) {
        this.minuteCount = minuteCount;
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
     * @return the status
     */
    public OrderStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    /**
     * @return the actualPrice
     */
    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    /**
     * @param actualPrice the actualPrice to set
     */
    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    /**
     * @return the names
     */
    public Set<String> getNames() {
        return names;
    }

    /**
     * @param names the names to set
     */
    public void setNames(Set<String> names) {
        this.names = names;
    }

    /**
     * @return the avatars
     */
    public Set<String> getAvatars() {
        return avatars;
    }

    /**
     * @param avatars the avatars to set
     */
    public void setAvatars(Set<String> avatars) {
        this.avatars = avatars;
    }

}
