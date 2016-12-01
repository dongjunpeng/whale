package com.buterfleoge.whale.type.protocol.travel;

import java.util.List;

import com.buterfleoge.whale.type.entity.TravelRoute;
import com.buterfleoge.whale.type.entity.TravelRouteDay;
import com.buterfleoge.whale.type.entity.TravelRouteMore;
import com.buterfleoge.whale.type.entity.TravelRoutePcInfo;
import com.buterfleoge.whale.type.entity.TravelRouteWapInfo;
import com.buterfleoge.whale.type.protocol.Response;

/**
 * @author Brent24
 *
 */
public class GetRouteResponse extends Response {

    private List<TravelRoute> routes;
    private TravelRouteMore more;
    private List<TravelRouteDay> days;
    private TravelRoutePcInfo pcInfo;
    private TravelRouteWapInfo wapInfo;
    private String mdtext;

    /**
     * @return the routes
     */
    public List<TravelRoute> getRoutes() {
        return routes;
    }

    /**
     * @param routes
     *            the routes to set
     */
    public void setRoutes(List<TravelRoute> routes) {
        this.routes = routes;
    }

    /**
     * @return the more
     */
    public TravelRouteMore getMore() {
        return more;
    }

    /**
     * @param more
     *            the more to set
     */
    public void setMore(TravelRouteMore more) {
        this.more = more;
    }

    /**
     * @return the days
     */
    public List<TravelRouteDay> getDays() {
        return days;
    }

    /**
     * @param days
     *            the days to set
     */
    public void setDays(List<TravelRouteDay> days) {
        this.days = days;
    }

    /**
     * @return the pcInfo
     */
    public TravelRoutePcInfo getPcInfo() {
        return pcInfo;
    }

    /**
     * @param pcInfo
     *            the pcInfo to set
     */
    public void setPcInfo(TravelRoutePcInfo pcInfo) {
        this.pcInfo = pcInfo;
    }

    /**
     * @return the wapInfo
     */
    public TravelRouteWapInfo getWapInfo() {
        return wapInfo;
    }

    /**
     * @param wapInfo
     *            the wapInfo to set
     */
    public void setWapInfo(TravelRouteWapInfo wapInfo) {
        this.wapInfo = wapInfo;
    }

    /**
     * @return the mdtext
     */
    public String getMdtext() {
        return mdtext;
    }

    /**
     * @param mdtext
     *            the mdtext to set
     */
    public void setMdtext(String mdtext) {
        this.mdtext = mdtext;
    }

    @Override
    public String toString() {
        return routes.toString();
    }

}
