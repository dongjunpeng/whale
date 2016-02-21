package com.buterfleoge.whale.type.protocol.account;

import org.hibernate.validator.constraints.Email;

import com.buterfleoge.whale.BaseObject;

/**
 * email 是否存在请求项
 *
 * @author xiezhenzong
 *
 */
public class EmailExistRequestItem extends BaseObject {

    @Email
    private String email;

    /**
     *
     */
    public EmailExistRequestItem() {
    }

    /**
     * @param email
     */
    public EmailExistRequestItem(String email) {
        this.email = email;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
