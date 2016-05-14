package com.buterfleoge.whale.type.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.type.enums.TravelArea;
import com.buterfleoge.whale.type.enums.TravelType;

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
    private TravelType type;

    @Column(name = "days")
    private Integer days;

    @Column(name = "area")
    private TravelArea area;

    @Column(name = "departure")
    private String departure;

    @Column(name = "distination")
    private String distination;

    @Column(name = "route")
    private String route;

    @Column(name = "min_price")
    private Long minPrice;

    @Column(name = "max_price")
    private Long maxPrice;

    @Column(name = "description")
    private String desc;

    @Column(name = "head_img")
    private String headImg;

    @Column(name = "map_img")
    private String mapImg;

    @Column(name = "wx_link")
    private String wxLink;

    @Column(name = "imgtext")
    private String imgtext;

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

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public TravelType getType() {
        return type;
    }

    public void setType(TravelType type) {
        this.type = type;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public TravelArea getArea() {
        return area;
    }

    public void setArea(TravelArea area) {
        this.area = area;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDistination() {
        return distination;
    }

    public void setDistination(String distination) {
        this.distination = distination;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Long getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Long minPrice) {
        this.minPrice = minPrice;
    }

    public Long getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Long maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getMapImg() {
        return mapImg;
    }

    public void setMapImg(String mapImg) {
        this.mapImg = mapImg;
    }

    public String getWxLink() {
        return wxLink;
    }

    public void setWxLink(String wxLink) {
        this.wxLink = wxLink;
    }

    public String getImgtext() {
        return imgtext;
    }

    public void setImgtext(String imgtext) {
        this.imgtext = imgtext;
    }

}
