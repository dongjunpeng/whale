package com.buterfleoge.whale.type.protocol.account;

import com.buterfleoge.whale.type.protocol.Request;

/**
 *
 * @author xiezhenzong
 *
 */
public class GetContactsRequest extends Request {

    private Long contactid;

    private Boolean needDefault = false;

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
     * @return the needDefault
     */
    public Boolean getNeedDefault() {
        return needDefault;
    }

    /**
     * @param needDefault the needDefault to set
     */
    public void setNeedDefault(Boolean needDefault) {
        this.needDefault = needDefault;
    }


}
