package com.buterfleoge.whale.type.entity;

import java.math.BigDecimal;
import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.type.entity.converter.PriceConverter;
import com.buterfleoge.whale.type.entity.converter.TravelCoverConverter;
import com.buterfleoge.whale.type.formatter.ImagePathFormat;
import com.buterfleoge.whale.type.formatter.ImagePathFormat.Prefix;

/**
 * @author Brent24
 *
 */
@Entity
@Table(name = "travel_route")
public class TravelRoute extends BaseObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "routeid")
    private Long routeid;

    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private String title;

    @Column(name = "visible")
    private Boolean visible;

    @Column(name = "type")
    private Integer type;

    @Column(name = "days")
    private Integer days;

    @Column(name = "area")
    private Integer area;

    @Column(name = "season")
    private String season;

    @Column(name = "departure")
    private String departure;

    @Column(name = "distination")
    private String distination;

    @Column(name = "route")
    private String route;

    @Column(name = "description")
    private String desc;

    @Column(name = "cover")
    @Convert(converter = TravelCoverConverter.class)
    private String cover;

    @ImagePathFormat(prefix = Prefix.ROUTE)
    @Column(name = "head_img")
    private String headImg;

    @NumberFormat(style = Style.CURRENCY)
    @Column(name = "min_price")
    @Convert(converter = PriceConverter.class)
    private BigDecimal minPrice;

    @NumberFormat(style = Style.CURRENCY)
    @Column(name = "max_price")
    @Convert(converter = PriceConverter.class)
    private BigDecimal maxPrice;

    @Column(name = "pchot")
    private Integer pcHot;

    @Column(name = "waphot")
    private Integer wapHot;

    public static final Comparator<TravelRoute> pcHotComparator = new Comparator<TravelRoute>() {

        @Override
        public int compare(TravelRoute o1, TravelRoute o2) {
            int ret = -o1.getPcHot().compareTo(o2.getPcHot());
            return ret == 0 ? o1.getRouteid().compareTo(o2.getRouteid()) : ret;
        }
    };

    public static final Comparator<TravelRoute> wapHotComparator = new Comparator<TravelRoute>() {

        @Override
        public int compare(TravelRoute o1, TravelRoute o2) {
            int ret = -o1.getWapHot().compareTo(o2.getWapHot());
            return ret == 0 ? o1.getRouteid().compareTo(o2.getRouteid()) : ret;
        }
    };

    public static final Comparator<TravelRoute> getComparator(boolean isFromWx) {
        return isFromWx ? wapHotComparator : pcHotComparator;
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
     * @return the visible
     */
    public Boolean getVisible() {
        return visible;
    }

    /**
     * @param visible
     *            the visible to set
     */
    public void setVisible(Boolean visible) {
        this.visible = visible;
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
     * @return the days
     */
    public Integer getDays() {
        return days;
    }

    /**
     * @param days
     *            the days to set
     */
    public void setDays(Integer days) {
        this.days = days;
    }

    /**
     * @return the area
     */
    public Integer getArea() {
        return area;
    }

    /**
     * @param area
     *            the area to set
     */
    public void setArea(Integer area) {
        this.area = area;
    }

    /**
     * @return the season
     */
    public String getSeason() {
        return season;
    }

    /**
     * @param season
     *            the season to set
     */
    public void setSeason(String season) {
        this.season = season;
    }

    /**
     * @return the departure
     */
    public String getDeparture() {
        return departure;
    }

    /**
     * @param departure
     *            the departure to set
     */
    public void setDeparture(String departure) {
        this.departure = departure;
    }

    /**
     * @return the distination
     */
    public String getDistination() {
        return distination;
    }

    /**
     * @param distination
     *            the distination to set
     */
    public void setDistination(String distination) {
        this.distination = distination;
    }

    /**
     * @return the route
     */
    public String getRoute() {
        return route;
    }

    /**
     * @param route
     *            the route to set
     */
    public void setRoute(String route) {
        this.route = route;
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
     * @return the cover
     */
    public String getCover() {
        return cover;
    }

    /**
     * @param cover
     *            the cover to set
     */
    public void setCover(String cover) {
        this.cover = cover;
    }

    /**
     * @return the headImg
     */
    public String getHeadImg() {
        return headImg;
    }

    /**
     * @param headImg
     *            the headImg to set
     */
    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    /**
     * @return the minPrice
     */
    public BigDecimal getMinPrice() {
        return minPrice;
    }

    /**
     * @param minPrice
     *            the minPrice to set
     */
    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    /**
     * @return the maxPrice
     */
    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    /**
     * @param maxPrice
     *            the maxPrice to set
     */
    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    /**
     * @return the pcHot
     */
    public Integer getPcHot() {
        return pcHot != null ? pcHot : 0;
    }

    /**
     * @param pcHot
     *            the pcHot to set
     */
    public void setPcHot(Integer pcHot) {
        this.pcHot = pcHot;
    }

    /**
     * @return the wapHot
     */
    public Integer getWapHot() {
        return wapHot != null ? wapHot : 0;
    }

    /**
     * @param wapHot
     *            the wapHot to set
     */
    public void setWapHot(Integer wapHot) {
        this.wapHot = wapHot;
    }

    @Override
    public String toString() {
        return "{\"routeid\": " + getRouteid() + "}";
    }

}
