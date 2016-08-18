package com.buterfleoge.whale.type.protocol.account;

import com.buterfleoge.whale.type.protocol.Request;

/**
 * @author Brent24
 *
 */
public class GetBasicInfoRequest extends Request {

    private Long accountid;

    public Long getAccountid() {
        return accountid;
    }

    public void setAccountid(Long accountid) {
        this.accountid = accountid;
    }

}
