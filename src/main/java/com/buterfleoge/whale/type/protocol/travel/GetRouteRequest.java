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

    private String name;

    private Boolean isImgtextRequired = false;

    public Long getRouteid() {
        return routeid;
    }

    public void setRouteid(Long routeid) {
        this.routeid = routeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsImgtextRequired() {
        return isImgtextRequired;
    }

    public void setIsImgtextRequired(Boolean isImgtextRequired) {
        this.isImgtextRequired = isImgtextRequired;
    }

}
