package com.buterfleoge.whale.type.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.Constants.Pattern;
import com.buterfleoge.whale.type.AccountStatus;
import com.buterfleoge.whale.type.AccountType;
import com.buterfleoge.whale.type.IdType;
import com.buterfleoge.whale.type.entity.converter.DateTimeConverter;

/**
 * 账户的基本信息
 *
 * @author xiezhenzong
 *
 */
@Entity
@Table(name = "account_info")
public class AccountInfo extends BaseObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "accountid")
    private Long accountid;

    @Column(name = "name")
    private String name = "";

    @Column(name = "password")
    private String password = "";

    @Column(name = "type")
    private AccountType type = AccountType.USER;

    @Column(name = "status")
    private AccountStatus status = AccountStatus.OK;

    @Column(name = "id")
    private String id = "";

    @Column(name = "id_type")
    private IdType idType = IdType.IDENTIFICATION;

    @Column(name = "email")
    private String email = "";

    @Column(name = "mobile")
    private String mobile = "";

    @DateTimeFormat(pattern = Pattern.DATE)
    @Column(name = "add_time")
    @Convert(converter = DateTimeConverter.class)
    private Date addTime;

    @DateTimeFormat(pattern = Pattern.DATE)
    @Column(name = "mod_time")
    @Convert(converter = DateTimeConverter.class)
    private Date modTime;

    /**
     * @return the accountid
     */
    public Long getAccountid() {
        return accountid;
    }

    /**
     * @param accountid
     *            the accountid to set
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
     * @param name
     *            the name to set
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
     * @param password
     *            the password to set
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
     * @param type
     *            the type to set
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
     * @param status
     *            the status to set
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
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the idType
     */

    public IdType getIdType() {
        return idType;
    }

    /**
     * @param idType
     *            the idType to set
     */
    public void setIdType(IdType idType) {
        this.idType = idType;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile
     *            the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the addTime
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * @param addTime
     *            the addTime to set
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * @return the modTime
     */
    public Date getModTime() {
        return modTime;
    }

    /**
     * @param modTime
     *            the modTime to set
     */
    public void setModTime(Date modTime) {
        this.modTime = modTime;
    }

}
