package com.buterfleoge.whale.type.protocol.account;

import com.buterfleoge.whale.type.protocol.Request;

/**
 *
 * @author xiezhenzong
 *
 */
public class GetOrdersRequest extends Request {

    private Long accountid;

    /**
     * @return the accountid
     */
    public Long getAccountid() {
        return accountid;
    }

    /**
     * @param accountid the accountid to set
     */
    public void setAccountid(Long accountid) {
        this.accountid = accountid;
    }

}
