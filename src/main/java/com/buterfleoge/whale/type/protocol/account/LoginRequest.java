package com.buterfleoge.whale.type.protocol.account;

import java.io.Serializable;

import com.buterfleoge.whale.type.protocol.Request;

/**
 * 
 *
 * @author xiezhenzong
 *
 */
public class LoginRequest extends Request implements Serializable {

    /**
     * serial version uid
     */
    private static final long serialVersionUID = -3361225960433232410L;

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
