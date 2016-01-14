package com.buterfleoge.whale.type.protocol.account;

import java.io.Serializable;

import com.buterfleoge.whale.BaseObject;

/**
 * 
 *
 * @author xiezhenzong
 *
 */
public class RegisterRequest extends BaseObject implements Serializable {

    /**
     * serial version uid
     */
    private static final long serialVersionUID = 749154490420349916L;

    private String email;
    private String password;

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

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
