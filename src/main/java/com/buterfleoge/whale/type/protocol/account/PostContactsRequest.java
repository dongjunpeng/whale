package com.buterfleoge.whale.type.protocol.account;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.buterfleoge.whale.type.entity.AccountContacts;
import com.buterfleoge.whale.type.protocol.Request;

/**
 *
 * @author xiezhenzong
 *
 */
public class PostContactsRequest extends Request {

    @NotNull
    private Long accountid;

    @NotEmpty
    private List<AccountContacts> contacts;

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

    /**
     * @return the contacts
     */
    public List<AccountContacts> getContacts() {
        return contacts;
    }

    /**
     * @param contacts the contacts to set
     */
    public void setContacts(List<AccountContacts> contacts) {
        this.contacts = contacts;
    }

}
