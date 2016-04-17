package com.buterfleoge.whale.type.protocol.account;

import java.util.List;

import com.buterfleoge.whale.type.entity.AccountContacts;
import com.buterfleoge.whale.type.protocol.Response;

/**
 *
 * @author xiezhenzong
 *
 */
public class GetContactsResponse extends Response {

    private List<AccountContacts> contacts;

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
