package com.buterfleoge.whale.type.protocol.account;

import com.buterfleoge.whale.type.protocol.Request;
import com.buterfleoge.whale.validator.IdExist;
import com.buterfleoge.whale.validator.IdExist.IdType;

/**
 * @author xiezhenzong
 *
 */
public class GetWxShareConfigRequest extends Request {

    @IdExist(type = IdType.ROUTE_ID, nullable = false)
    private Long routeid;

    private String routeUrl;

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
     * @return the routeUrl
     */
    public String getRouteUrl() {
        return routeUrl;
    }

    /**
     * @param routeUrl
     *            the routeUrl to set
     */
    public void setRouteUrl(String routeUrl) {
        this.routeUrl = routeUrl;
    }

}
