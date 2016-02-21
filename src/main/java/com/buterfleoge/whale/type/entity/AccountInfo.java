package com.buterfleoge.whale.type.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.type.AccountStatus;
import com.buterfleoge.whale.type.AccountType;

/**
 * entity class for account_info table
 *
 * @author xiezhenzong
 *
 */
@Entity
@Table(name = "account_info")
public class AccountInfo extends BaseObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accountid")
    private long accountid;

    @Column(name = "name")
    private String name = "";

    @Column(name = "password")
    private String password = "";

    @Column(name = "type")
    private AccountType type = AccountType.USER;

    @Column(name = "status")
    private AccountStatus status = AccountStatus.WAIT_ACTIVE;

    @Column(name = "id")
    private String id = "";

    @Column(name = "email")
    private String email = "";

    @Column(name = "wxid")
    private String wxid = "";

    @Column(name = "qqid")
    private String qqid = "";

    @Column(name = "wbid")
    private String wbid = "";

    @Column(name = "mobile")
    private String mobile = "";

    @Column(name = "add_time")
    private long addTime = 0;

    @Column(name = "mod_time")
    private long modTime = 0;

    /**
     * @return the accountid
     */
    public long getAccountid() {
        return accountid;
    }

    /**
     * @param accountid the accountid to set
     */
    public void setAccountid(long accountid) {
        this.accountid = accountid;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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

    /**
     * @return the status
     */
    public AccountStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
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
     * @return the wxid
     */
    public String getWxid() {
        return wxid;
    }

    /**
     * @param wxid the wxid to set
     */
    public void setWxid(String wxid) {
        this.wxid = wxid;
    }

    /**
     * @return the qqid
     */
    public String getQqid() {
        return qqid;
    }

    /**
     * @param qqid the qqid to set
     */
    public void setQqid(String qqid) {
        this.qqid = qqid;
    }

    /**
     * @return the wbid
     */
    public String getWbid() {
        return wbid;
    }

    /**
     * @param wbid the wbid to set
     */
    public void setWbid(String wbid) {
        this.wbid = wbid;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the addTime
     */
    public long getAddTime() {
        return addTime;
    }

    /**
     * @param addTime the addTime to set
     */
    public void setAddTime(long addTime) {
        this.addTime = addTime;
    }

    /**
     * @return the modTime
     */
    public long getModTime() {
        return modTime;
    }

    /**
     * @param modTime the modTime to set
     */
    public void setModTime(long modTime) {
        this.modTime = modTime;
    }

}
