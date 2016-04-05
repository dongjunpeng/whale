package com.buterfleoge.whale.type.protocol.account;

import org.hibernate.validator.constraints.Email;

import com.buterfleoge.whale.type.protocol.Request;

/**
 * email 是否存在请求项
 *
 * @author xiezhenzong
 *
 */
public class EmailExistRequest extends Request {

    @Email
    private String email;

    /**
     *
     */
    public EmailExistRequest() {
    }

    /**
     * @param email
     */
    public EmailExistRequest(String email) {
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
