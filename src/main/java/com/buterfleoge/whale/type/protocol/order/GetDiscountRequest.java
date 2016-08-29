package com.buterfleoge.whale.type.protocol.order;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.buterfleoge.whale.Constants;
import com.buterfleoge.whale.validator.IdExist;
import com.buterfleoge.whale.validator.IdExist.IdType;

/**
 * @author Brent24
 *
 */
public class GetDiscountRequest extends OrderRequest {

    @IdExist(type = IdType.ROUTE_ID, nullable = false, message = "")
    private Long routeid;

    @IdExist(type = IdType.GROUP_ID, nullable = false, message = "")
    private Long groupid;

    @NotNull(message = "")
    @Range(min = 1, max = Constants.DefaultValue.MAX_ORDER_TRAVELLER_COUNT, message = "")
    private Integer count;

    public Long getRouteid() {
        return routeid;
    }

    public void setRouteid(Long routeid) {
        this.routeid = routeid;
    }

    public Long getGroupid() {
        return groupid;
    }

    public void setGroupid(Long groupid) {
        this.groupid = groupid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
