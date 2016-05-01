package com.buterfleoge.whale.type.protocol.travel;

import java.util.List;

import com.buterfleoge.whale.type.entity.TravelRoute;
import com.buterfleoge.whale.type.protocol.Response;
import com.buterfleoge.whale.type.protocol.travel.imgtext.Imgtext;

/**
 * @author Brent24
 *
 */
public class GetRouteResponse extends Response {

    private List<TravelRoute> routes;
    private Imgtext imgtext;

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

    public Imgtext getImgtext() {
        return imgtext;
    }

    public void setImgtext(Imgtext imgtext) {
        this.imgtext = imgtext;
    }

}
