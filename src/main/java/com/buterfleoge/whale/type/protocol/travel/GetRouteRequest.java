/**
 * 
 */
package com.buterfleoge.whale.type.protocol.travel;

import com.buterfleoge.whale.type.protocol.Request;

/**
 * @author Brent24
 *
 */
public class GetRouteRequest extends Request {

    private Long routeid;

    private Boolean isImgtextRequired = Boolean.FALSE;

    public Long getRouteid() {
        return routeid;
    }

    public void setRouteid(Long routeid) {
        this.routeid = routeid;
    }

    public Boolean getIsImgtextRequired() {
        return isImgtextRequired;
    }

    public void setIsImgtextRequired(Boolean isImgtextRequired) {
        this.isImgtextRequired = isImgtextRequired;
    }

}
