package com.buterfleoge.whale.type.protocol.account;

import com.buterfleoge.whale.type.protocol.Request;

/**
 *
 * @author xiezhenzong
 *
 */
public class GetContactsRequest extends Request {

    private Long contactid;

    private Long accountid;

    /**
     * @return the contactid
     */
    public Long getContactid() {
        return contactid;
    }

    /**
     * @param contactid the contactid to set
     */
    public void setContactid(Long contactid) {
        this.contactid = contactid;
    }

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
