package com.buterfleoge.whale.type.protocol.order;

import javax.validation.constraints.NotNull;

import com.buterfleoge.whale.type.protocol.Request;

/**
 *
 * @author xiezhenzong
 *
 */
public class NewOrderRequest extends Request {

    @NotNull
    private Long routeid;

    @NotNull
    private Long groupid;

    // 测试用
    private Long accountid;

    public Long getAccountid() {
        return accountid;
    }

    public void setAccountid(Long accountid) {
        this.accountid = accountid;
    }

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
