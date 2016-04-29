package com.buterfleoge.whale.type.protocol.travel;

import java.util.List;

import com.buterfleoge.whale.type.entity.TravelRoute;
import com.buterfleoge.whale.type.protocol.Response;

/**
 * @author Brent24
 *
 */
public class GetRouteResponse extends Response {

    private List<TravelRoute> routes;

    /**
     * @return the routes
     */
    public List<TravelRoute> getRoutes() {
        return routes;
    }

    /**
     * @param routes the routes to set
     */
    public void setRoutes(List<TravelRoute> routes) {
        this.routes = routes;
    }



}
