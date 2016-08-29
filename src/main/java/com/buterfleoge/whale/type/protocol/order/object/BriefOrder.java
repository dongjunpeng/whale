package com.buterfleoge.whale.type.protocol.order.object;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.buterfleoge.whale.Constants.Pattern;
import com.buterfleoge.whale.type.formatter.ImagePathFormat;
import com.buterfleoge.whale.type.formatter.ImagePathFormat.Prefix;

/**
 * @author Brent24
 *
 */
public class BriefOrder implements Comparable<BriefOrder> {

    // order info

    private Long orderid;

    private Integer status;

    @NumberFormat(style = Style.CURRENCY)
    private BigDecimal actualPrice;

    private Long timeLeft;

    private Set<String> travellerNames;

    // route info

    private Long routeid;

    private String name;

    private String title;

    @ImagePathFormat(prefix = Prefix.ROUTE)
    private String headImg;

    // group info

    @DateTimeFormat(pattern = Pattern.DATE)
    private Date startDate;

    @DateTimeFormat(pattern = Pattern.DATE)
    private Date endDate;

    private String wxQrCode;

    private Long dayLeft;

    // sort排序顺序
    // public Integer getOrder() {
    // switch (status.value) {
    // case PAID:
    // return 1;
    // case WAITING:
    // return 2;
    // case PAYING:
    // return 3;
    // case REFOUNDING:
    // return 4;
    // case REFOUNDED:
    // return 5;
    // case FINISH:
    // return 6;
    // case CANCELPAYMENT:
    // return 7;
    // case CANCEL:
    // return 8;
    // case TIMEOUT:
    // return 9;
    // default:
    // return 100;
    // }
    // }

    @Override
    public int compareTo(BriefOrder arg0) {
        // return this.getOrder().compareTo(arg0.getOrder());
        return status - arg0.getStatus();
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
     * @return the timeLeft
     */
    public Long getTimeLeft() {
        return timeLeft;
    }

    /**
     * @param timeLeft
     *            the timeLeft to set
     */
    public void setTimeLeft(Long timeLeft) {
        this.timeLeft = timeLeft;
    }

    /**
     * @return the travellerNames
     */
    public Set<String> getTravellerNames() {
        return travellerNames;
    }

    /**
     * @param travellerNames
     *            the travellerNames to set
     */
    public void setTravellerNames(Set<String> travellerNames) {
        this.travellerNames = travellerNames;
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
     * @return the dayLeft
     */
    public Long getDayLeft() {
        return dayLeft;
    }

    /**
     * @param dayLeft
     *            the dayLeft to set
     */
    public void setDayLeft(Long dayLeft) {
        this.dayLeft = dayLeft;
    }

}
