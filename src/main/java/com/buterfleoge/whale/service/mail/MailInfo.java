package com.buterfleoge.whale.service.mail;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.Assert;

/**
 *
 * @author xiezhenzong
 *
 */
public class MailInfo {

    private long creatTime = System.currentTimeMillis();

    private String email;
    private long accountid;

    private Map<String, String> infos = new HashMap<String, String>();

    /**
     * @return the creatTime
     */
    public long getCreatTime() {
        return creatTime;
    }

    /**
     * @param creatTime the creatTime to set
     */
    public void setCreatTime(long creatTime) {
        this.creatTime = creatTime;
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
     * @return the infos
     */
    public Map<String, String> getInfos() {
        return infos;
    }

    /**
     * @param infos the infos to set
     */
    public void setInfos(Map<String, String> infos) {
        this.infos = infos;
    }

    public void addInfo(String key, String value) {
        Assert.hasText(key, "key can't be empty");
        Assert.hasText(value, "value can't be empty");
        this.infos.put(key, value);
    }


}
