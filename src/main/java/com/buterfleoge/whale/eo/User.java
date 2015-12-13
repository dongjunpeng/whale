package com.buterfleoge.whale.eo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.buterfleoge.whale.BaseObject;

/**
 * entity object for user table
 * 
 * @author xiezhenzong
 *
 */
@Entity
public class User extends BaseObject {

    @Id
    private Long userid;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    /**
     * @return the userid
     */
    public Long getUserid() {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(Long userid) {
        this.userid = userid;
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
