package com.buterfleoge.whale.type.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.type.entity.converter.ListConverter;
import com.buterfleoge.whale.type.formatter.ImagePathFormat;
import com.buterfleoge.whale.type.formatter.ImagePathFormat.Prefix;

/**
 * @author xiezhenzong
 *
 */
@Entity
@Table(name = "travel_route_day")
public class TravelRouteDay extends BaseObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "routedaysid")
    private Long routedaysid;

    @Column(name = "routeid")
    private Long routeid;

    @Column(name = "dayno")
    private Integer dayno;

    @Column(name = "title")
    private String title;

    @ImagePathFormat(prefix = Prefix.ROUTE, isComposite = true)
    @Column(name = "imgs")
    @Convert(converter = ListConverter.class)
    private List<String> imgs;

    @Column(name = "description")
    @Convert(converter = ListConverter.class)
    private List<String> desc;

    @Column(name = "detail")
    private String detail;

    @Column(name = "scenery")
    @Convert(converter = ListConverter.class)
    private List<String> scenery;

    @Column(name = "star")
    private String star;

    @Column(name = "food")
    private String food;

    @Column(name = "distance")
    private String distance;

    @Column(name = "hotel")
    private String hotel;

    /**
     * @return the routedaysid
     */
    public Long getRoutedaysid() {
        return routedaysid;
    }

    /**
     * @param routedaysid
     *            the routedaysid to set
     */
    public void setRoutedaysid(Long routedaysid) {
        this.routedaysid = routedaysid;
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
     * @return the dayno
     */
    public Integer getDayno() {
        return dayno;
    }

    /**
     * @param dayno
     *            the dayno to set
     */
    public void setDayno(Integer dayno) {
        this.dayno = dayno;
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
     * @return the imgs
     */
    public List<String> getImgs() {
        return imgs;
    }

    /**
     * @param imgs
     *            the imgs to set
     */
    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    /**
     * @return the desc
     */
    public List<String> getDesc() {
        return desc;
    }

    /**
     * @param desc
     *            the desc to set
     */
    public void setDesc(List<String> desc) {
        this.desc = desc;
    }

    /**
     * @return the detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * @param detail
     *            the detail to set
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * @return the scenery
     */
    public List<String> getScenery() {
        return scenery;
    }

    /**
     * @param scenery
     *            the scenery to set
     */
    public void setScenery(List<String> scenery) {
        this.scenery = scenery;
    }

    /**
     * @return the star
     */
    public String getStar() {
        return star;
    }

    /**
     * @param star
     *            the star to set
     */
    public void setStar(String star) {
        this.star = star;
    }

    /**
     * @return the food
     */
    public String getFood() {
        return food;
    }

    /**
     * @param food
     *            the food to set
     */
    public void setFood(String food) {
        this.food = food;
    }

    /**
     * @return the distance
     */
    public String getDistance() {
        return distance;
    }

    /**
     * @param distance
     *            the distance to set
     */
    public void setDistance(String distance) {
        this.distance = distance;
    }

    /**
     * @return the hotel
     */
    public String getHotel() {
        return hotel;
    }

    /**
     * @param hotel
     *            the hotel to set
     */
    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    @Override
    public String toString() {
        return "{\"routeid\": " + getRouteid() + ", \"dayno\": " + getDayno() + "}";
    }

}
