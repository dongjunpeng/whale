package com.buterfleoge.whale.type.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.Constants.Pattern;
import com.buterfleoge.whale.type.entity.converter.DateTimeConverter;

/**
 * 账户的设置信息
 *
 * @author Brent24
 */
@Entity
@Table(name = "account_binding")
public class AccountBinding extends BaseObject {

    @Id
    @Column(name = "accountid")
    private Long accountid;

    @Column(name = "wxid")
    private String wxid = "";

    @Column(name = "qqid")
    private String qqid = "";

    @Column(name = "wbid")
    private String wbid = "";

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
     * @return the wxid
     */
    public String getWxid() {
        return wxid;
    }

    /**
     * @param wxid
     *            the wxid to set
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
     * @param qqid
     *            the qqid to set
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
     * @param wbid
     *            the wbid to set
     */
    public void setWbid(String wbid) {
        this.wbid = wbid;
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
