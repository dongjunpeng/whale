/**
 * 
 */
package com.buterfleoge.whale.type.protocol.travel;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.buterfleoge.whale.type.protocol.Request;

/**
 * @author Brent24
 *
 */
public class GetRouteRequest extends Request {

    private List<Long> routeids;

    private Boolean isImgtextRequired = Boolean.FALSE;

    @DateTimeFormat(iso = ISO.DATE)
    private Date date;

    /**
     * @return the routeids
     */
    public List<Long> getRouteids() {
        return routeids;
    }

    /**
     * @param routeids the routeids to set
     */
    public void setRouteids(List<Long> routeids) {
        this.routeids = routeids;
    }

    /**
     * @return the isImgtextRequired
     */
    public Boolean getIsImgtextRequired() {
        return isImgtextRequired;
    }

    /**
     * @param isImgtextRequired the isImgtextRequired to set
     */
    public void setIsImgtextRequired(Boolean isImgtextRequired) {
        this.isImgtextRequired = isImgtextRequired;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

}
