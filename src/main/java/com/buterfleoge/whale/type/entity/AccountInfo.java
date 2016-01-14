package com.buterfleoge.whale.type.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.type.AccountStatus;
import com.buterfleoge.whale.type.AccountType;

/**
 * entity class for userinfo table
 *
 * @author xiezhenzong
 *
 */
@Entity
@Table(name = "accountinfo")
public class AccountInfo extends BaseObject {

    private Long accountid;

    private String name;

    private String password;

    private AccountType atype;

    private AccountStatus status;

    private String id;

    private String email;

    private String wxid;

    private String qqid;

    private String wbid;

    private String cellphone;

    private String addtime;

    private String modtime;

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
     * @return the atype
     */
    public AccountType getAtype() {
        return atype;
    }

    /**
     * @param atype the atype to set
     */
    public void setAtype(AccountType atype) {
        this.atype = atype;
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
     * @return the cellphone
     */
    public String getCellphone() {
        return cellphone;
    }

    /**
     * @param cellphone the cellphone to set
     */
    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    /**
     * @return the addtime
     */
    public String getAddtime() {
        return addtime;
    }

    /**
     * @param addtime the addtime to set
     */
    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    /**
     * @return the modtime
     */
    public String getModtime() {
        return modtime;
    }

    /**
     * @param modtime the modtime to set
     */
    public void setModtime(String modtime) {
        this.modtime = modtime;
    }

}
