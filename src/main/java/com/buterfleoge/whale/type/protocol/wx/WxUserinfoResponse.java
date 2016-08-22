package com.buterfleoge.whale.type.protocol.wx;

import java.util.List;

import com.buterfleoge.whale.type.Gender;

/**
 *
 * @author xiezhenzong
 *
 */
public class WxUserinfoResponse extends WxResponse {

    /**
     * serial version uid
     */
    private static final long serialVersionUID = 6805578374010812366L;

    public static final int SEX_UNKNOWN = 0;
    public static final int SEX_MALE = 1;
    public static final int SEX_FEMALE = 2;

    private String openid;

    private String nickname;

    private int sex;

    private String province;

    private String city;

    private String country;

    private String headimgurl;

    private List<String> privilege;

    private String unionid;

    private String language;

    /**
     * @return the opendid
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * @param openid
     *            the openid to set
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    /**
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname the nickname to set
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return the sex
     */
    public int getSex() {
        if (sex == SEX_MALE) {
            return Gender.MALE.value;
        } else if (sex == SEX_FEMALE) {
            return Gender.FEMALE.value;
        } else {
            return Gender.UNKNOW.value;
        }
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(int sex) {
        if (sex == Gender.MALE.value) {
            this.sex = SEX_MALE;
        } else if (sex == Gender.FEMALE.value) {
            this.sex = SEX_FEMALE;
        } else {
            this.sex = SEX_UNKNOWN;
        }
    }

    /**
     * @return the province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province the province to set
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the headimgurl
     */
    public String getHeadimgurl() {
        return headimgurl;
    }

    /**
     * @param headimgurl the headimgurl to set
     */
    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    /**
     * @return the privilege
     */
    public List<String> getPrivilege() {
        return privilege;
    }

    /**
     * @param privilege the privilege to set
     */
    public void setPrivilege(List<String> privilege) {
        this.privilege = privilege;
    }

    /**
     * @return the unionid
     */
    public String getUnionid() {
        return unionid;
    }

    /**
     * @param unionid the unionid to set
     */
    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    /**
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language
     *            the language to set
     */
    public void setLanguage(String language) {
        this.language = language;
    }

}
