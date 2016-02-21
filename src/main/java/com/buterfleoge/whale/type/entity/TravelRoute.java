/**
 * 
 */
package com.buterfleoge.whale.type.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.type.TravelScope;
import com.buterfleoge.whale.type.TravelType;

/**
 * @author Brent24
 *
 */

@Entity
@Table(name = "traveller_route")
public class TravelRoute extends BaseObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "routeid")
    private long routeid;

    @Column(name = "title")
    private String title = "";

    @Column(name = "type")
    private TravelType type = TravelType.LONG_TRIP;

    @Column(name = "days")
    private int days = 0;

    @Column(name = "scope")
    private TravelScope scope = TravelScope.UNKNOW;

    @Column(name = "start_place")
    private String startPlace = "";

    @Column(name = "end_place")
    private String endPlace = "";

    @Column(name = "views")
    private String views = "";

    @Column(name = "price_interval")
    private String priceInterval = "";

    @Column(name = "description")
    private String description = "";

    public long getRouteid() {
        return routeid;
    }

    public void setRouteid(long routeid) {
        this.routeid = routeid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TravelType getType() {
        return type;
    }

    public void setType(TravelType type) {
        this.type = type;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public TravelScope getScope() {
        return scope;
    }

    public void setScope(TravelScope scope) {
        this.scope = scope;
    }

    public String getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(String startPlace) {
        this.startPlace = startPlace;
    }

    public String getEndPlace() {
        return endPlace;
    }

    public void setEndPlace(String endPlace) {
        this.endPlace = endPlace;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getPriceInterval() {
        return priceInterval;
    }

    public void setPriceInterval(String priceInterval) {
        this.priceInterval = priceInterval;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
