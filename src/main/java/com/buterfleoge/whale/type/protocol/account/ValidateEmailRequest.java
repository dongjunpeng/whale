package com.buterfleoge.whale.type.protocol.account;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.buterfleoge.whale.type.protocol.Request;

/**
 *
 * @author xiezhenzong
 *
 */
public class ValidateEmailRequest extends Request {

    @NotBlank
    private String email;

    @NotNull
    private Long accountid;

    @NotBlank
    private String validCode;

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
     * @return the validCode
     */
    public String getValidCode() {
        return validCode;
    }

    /**
     * @param validCode the validCode to set
     */
    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }

}
