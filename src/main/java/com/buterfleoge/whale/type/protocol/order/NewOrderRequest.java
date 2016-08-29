package com.buterfleoge.whale.type.protocol.order;

import javax.validation.constraints.NotNull;

import com.buterfleoge.whale.type.protocol.Request;
import com.buterfleoge.whale.validator.IdExist;
import com.buterfleoge.whale.validator.IdExist.IdType;

/**
 *
 * @author xiezhenzong
 *
 */
public class NewOrderRequest extends Request {

    @NotNull(message = "")
    @IdExist(type = IdType.ROUTE_ID, message = "")
    private Long routeid;

    @NotNull(message = "")
    @IdExist(type = IdType.GROUP_ID, message = "")
    private Long groupid;

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
     * @return the groupid
     */
    public Long getGroupid() {
        return groupid;
    }

    /**
     * @param groupid
     *            the groupid to set
     */
    public void setGroupid(Long groupid) {
        this.groupid = groupid;
    }

}
