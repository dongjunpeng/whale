package com.buterfleoge.whale.type;

import org.apache.commons.beanutils.BeanUtils;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.type.entity.AccountInfo;
import com.buterfleoge.whale.type.entity.AccountSetting;
import com.buterfleoge.whale.type.enums.AccountStatus;
import com.buterfleoge.whale.type.enums.AccountType;
import com.buterfleoge.whale.type.enums.Gender;
import com.buterfleoge.whale.type.enums.IdType;

/**
 * @author Brent24
 *
 */
public class AccountBasicInfo extends BaseObject {
    private Long accountid;

    private AccountInfo accountInfo;
    private AccountSetting accountSetting;
    // info

    private String name = "";
    private String password = "";
    private AccountType type = AccountType.USER;
    private AccountStatus status = AccountStatus.OK;
    private String id = "";
    private IdType idType = IdType.IDENTIFICATION;
    private String email = "";
    private String mobile = "";

    // setting

    private String nickname = "";
    private String wxid = "";
    private String wxname = "";
    private String qqid = "";
    private String qqname = "";
    private String wbid = "";
    private String wbname = "";
    private Gender gender = Gender.UNKNOW;
    private Long birthday = 0L;
    private String address = "";
    private String avatarUrl = "";

    public Long getAccountid() {
        return accountid;
    }

    public void setAccountid(Long accountid) {
        this.accountid = accountid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public IdType getIdType() {
        return idType;
    }

    public void setIdType(IdType idType) {
        this.idType = idType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getWxid() {
        return wxid;
    }

    public void setWxid(String wxid) {
        this.wxid = wxid;
    }

    public String getWxname() {
        return wxname;
    }

    public void setWxname(String wxname) {
        this.wxname = wxname;
    }

    public String getQqid() {
        return qqid;
    }

    public void setQqid(String qqid) {
        this.qqid = qqid;
    }

    public String getQqname() {
        return qqname;
    }

    public void setQqname(String qqname) {
        this.qqname = qqname;
    }

    public String getWbid() {
        return wbid;
    }

    public void setWbid(String wbid) {
        this.wbid = wbid;
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

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public AccountInfo getAccountInfo() throws Exception {
        BeanUtils.copyProperties(accountInfo, this);
        return accountInfo;
    }

    public void setAccountInfo(AccountInfo accountInfo) throws Exception {
        this.accountInfo = accountInfo;
        BeanUtils.copyProperties(this, accountSetting);
    }

    public AccountSetting getAccountSetting() throws Exception {
        BeanUtils.copyProperties(accountSetting, this);
        return accountSetting;
    }

    public void setAccountSetting(AccountSetting accountSetting) throws Exception {
        if (this.accountid == accountSetting.getAccountid()) {
            this.accountSetting = accountSetting;
            BeanUtils.copyProperties(this, accountSetting);
        } else {
            throw new Exception("accountid不匹配");
        }
    }

    public void setBasicInfo(AccountInfo accountInfo, AccountSetting accountSetting) throws Exception {
        setAccountInfo(accountInfo);
        setAccountSetting(accountSetting);
    }

}
