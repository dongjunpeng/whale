/**
 *
 */
package com.buterfleoge.whale.type.protocol.account;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.buterfleoge.whale.Constants.Pattern;
import com.buterfleoge.whale.type.protocol.Request;

/**
 * @author Brent24
 *
 */
public class PostBasicInfoRequest extends Request {

    private String name;

    private String id;

    private Integer idType;

    private String email;

    private String mobile;

    private Integer gender;

    @DateTimeFormat(pattern = Pattern.DATE)
    private Date birthday;

    private String area;

    private String address;

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
     * @return the idType
     */
    public Integer getIdType() {
        return idType;
    }

    /**
     * @param idType the idType to set
     */
    public void setIdType(Integer idType) {
        this.idType = idType;
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
     * @return the gender
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * @return the birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday the birthday to set
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the area
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area
     *            the area to set
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

}
