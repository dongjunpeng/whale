package com.buterfleoge.whale.type.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.type.Gender;

/**
 * @author Brent24
 *
 */
@Entity
@Table(name = "account_setting")
public class AccountSetting extends BaseObject {

    @Id
    @Column(name = "accountid")
    private long accountid;

    @Column(name = "nickname")
    private String nickname = "";

    @Column(name = "wxname")
    private String wxname = "";

    @Column(name = "qqname")
    private String qqname = "";

    @Column(name = "wbname")
    private String wbname = "";

    @Column(name = "gender")
    private Gender gender = Gender.UNKNOW;

    @Column(name = "birthday")
    private String birthday = "";

    @Column(name = "avatar_url")
    private String avatarUrl = "";

    @Column(name = "mod_time")
    private String modTime = "";

    public long getAccountid() {
        return accountid;
    }

    public void setAccountid(long accountid) {
        this.accountid = accountid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getWxname() {
        return wxname;
    }

    public void setWxname(String wxname) {
        this.wxname = wxname;
    }

    public String getQqname() {
        return qqname;
    }

    public void setQqname(String qqname) {
        this.qqname = qqname;
    }

    public String getWbname() {
        return wbname;
    }

    public void setWbname(String wbname) {
        this.wbname = wbname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getModTime() {
        return modTime;
    }

    public void setModTime(String modTime) {
        this.modTime = modTime;
    }

}
