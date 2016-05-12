/**
 * 
 */
package com.buterfleoge.whale.type.protocol.account;

import javax.validation.constraints.NotNull;

import com.buterfleoge.whale.type.protocol.Request;

/**
 * @author Brent24
 *
 */
public class DeleteContactsRequest extends Request {

    @NotNull
    private Long contactid;

    public Long getContactid() {
        return contactid;
    }

    public void setContactid(Long contactid) {
        this.contactid = contactid;
    }

}
