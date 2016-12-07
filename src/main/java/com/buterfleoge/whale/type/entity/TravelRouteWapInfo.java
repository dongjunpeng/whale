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
@Table(name = "travel_route_wapinfo")
public class TravelRouteWapInfo extends BaseObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "routeid")
    private Long routeid;

    @Column(name = "directory")
    @Convert(converter = ListConverter.class)
    private List<String> directory;

    @ImagePathFormat(prefix = Prefix.ROUTE)
    @Column(name = "map_img")
    private String mapImg;

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
     * @return the directory
     */
    public List<String> getDirectory() {
        return directory;
    }

    /**
     * @param directory
     *            the directory to set
     */
    public void setDirectory(List<String> directory) {
        this.directory = directory;
    }

    /**
     * @return the mapImg
     */
    public String getMapImg() {
        return mapImg;
    }

    /**
     * @param mapImg
     *            the mapImg to set
     */
    public void setMapImg(String mapImg) {
        this.mapImg = mapImg;
    }

    @Override
    public String toString() {
        return "{\"routeid\": " + getRouteid() + "}";
    }

}
