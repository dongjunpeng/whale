package com.buterfleoge.whale.type.protocol;

import com.buterfleoge.whale.BaseObject;

/**
 * base request
 *
 * @author xiezhenzong
 *
 */
public class Request extends BaseObject {

    private String reqid;
    private Long accountid;

    /**
     * @return the reqid
     */
    public String getReqid() {
        return reqid;
    }

    /**
     * @param reqid the reqid to set
     */
    public void setReqid(String reqid) {
        this.reqid = reqid;
    }

    /**
     * @return the accountid
     */
    public Long getAccountid() {
        return accountid;
    }

    /**
     * @param accountid
     *            the accountid to set
     */
    public void setAccountid(Long accountid) {
        this.accountid = accountid;
    }

}
