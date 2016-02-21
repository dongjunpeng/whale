package com.buterfleoge.whale.type.protocol.account;

import org.hibernate.validator.constraints.NotBlank;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.type.AccountType;

/**
 * 登陆请求
 *
 * @author xiezhenzong
 *
 */
public class LoginRequestItem extends BaseObject {

    @NotBlank
    private String password;

    private String email;

    private AccountType type;

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
     * @return the type
     */
    public AccountType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(AccountType type) {
        this.type = type;
    }

}
