package com.buterfleoge.whale.type.protocol.account;

import java.util.List;

import com.buterfleoge.whale.type.entity.AccountContact;
import com.buterfleoge.whale.type.protocol.Response;

/**
 *
 * @author xiezhenzong
 *
 */
public class GetContactsResponse extends Response {

    private List<AccountContact> contacts;

    /**
     * @return the contacts
     */
    public List<AccountContact> getContacts() {
        return contacts;
    }

    /**
     * @param contacts the contacts to set
     */
    public void setContacts(List<AccountContact> contacts) {
        this.contacts = contacts;
    }

}
