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

}
