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
import com.buterfleoge.whale.type.entity.converter.PointConverter;
import com.buterfleoge.whale.type.entity.converter.PointsConverter;
import com.buterfleoge.whale.type.entity.converter.PointsConverter.Point;

/**
 * @author xiezhenzong
 *
 */
@Entity
@Table(name = "travel_route_pcinfo")
public class TravelRoutePcInfo extends BaseObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "routeid")
    private Long routeid;

    @Column(name = "spotlights")
    @Convert(converter = ListConverter.class)
    private List<String> spotlights;

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "center")
    @Convert(converter = PointConverter.class)
    private Point center;

    @Column(name = "zoom")
    private Integer zoom;

    @Column(name = "cities")
    @Convert(converter = ListConverter.class)
    private List<String> cities;

    @Column(name = "points")
    @Convert(converter = PointsConverter.class)
    private List<Point> points;

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
     * @return the spotlights
     */
    public List<String> getSpotlights() {
        return spotlights;
    }

    /**
     * @param spotlights
     *            the spotlights to set
     */
    public void setSpotlights(List<String> spotlights) {
        this.spotlights = spotlights;
    }

    /**
     * @return the introduction
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * @param introduction
     *            the introduction to set
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    /**
     * @return the center
     */
    public Point getCenter() {
        return center;
    }

    /**
     * @param center
     *            the center to set
     */
    public void setCenter(Point center) {
        this.center = center;
    }

    /**
     * @return the zoom
     */
    public Integer getZoom() {
        return zoom;
    }

    /**
     * @param zoom
     *            the zoom to set
     */
    public void setZoom(Integer zoom) {
        this.zoom = zoom;
    }

    /**
     * @return the cities
     */
    public List<String> getCities() {
        return cities;
    }

    /**
     * @param cities
     *            the cities to set
     */
    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    /**
     * @return the points
     */
    public List<Point> getPoints() {
        return points;
    }

    /**
     * @param points
     *            the points to set
     */
    public void setPoints(List<Point> points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "{\"routeid\": " + getRouteid() + "}";
    }
}
