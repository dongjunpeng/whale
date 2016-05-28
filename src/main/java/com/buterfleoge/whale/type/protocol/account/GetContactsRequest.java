package com.buterfleoge.whale.type.protocol.account;

import com.buterfleoge.whale.type.protocol.Request;

/**
 *
 * @author xiezhenzong
 *
 */
public class GetContactsRequest extends Request {

    private Long contactid;

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

}
