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
import com.buterfleoge.whale.type.Gender;
import com.buterfleoge.whale.type.IdType;
import com.buterfleoge.whale.type.entity.converter.DateTimeConverter;
import com.buterfleoge.whale.type.entity.converter.EnumConverters.GenderConverter;
import com.buterfleoge.whale.type.entity.converter.EnumConverters.IdTypeConverter;

/**
 * 账户的联系人
 *
 * @author xiezhenzong
 *
 */
@Entity
@Table(name = "account_contacts")
public class AccountContacts extends BaseObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contactid")
    private Long contactid;

    @Column(name = "accountid")
    private Long accountid;

    @Column(name = "valid")
    private Boolean valid;

    @Column(name = "name")
    private String name;

    @Column(name = "id")
    private String id;

    @Column(name = "id_type")
    @Convert(converter = IdTypeConverter.class)
    private IdType idType;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "gender")
    @Convert(converter = GenderConverter.class)
    private Gender gender;

    @DateTimeFormat(pattern = Pattern.DATE)
    @Column(name = "birthday")
    @Convert(converter = DateTimeConverter.class)
    private Date birthday;

    @Column(name = "address")
    private String address;

    @Column(name = "emergency_contact")
    private String emergencyContact;

    @Column(name = "emergency_mobile")
    private String emergencyMobile;

    @DateTimeFormat(pattern = Pattern.DATE)
    @Column(name = "add_time")
    @Convert(converter = DateTimeConverter.class)
    private Date addTime;

    @DateTimeFormat(pattern = Pattern.DATE)
    @Column(name = "mod_time")
    @Convert(converter = DateTimeConverter.class)
    private Date modTime;

    /**
     * @return the contactid
     */
    public Long getContactid() {
        return contactid;
    }

    /**
     * @param contactid the contactid to set
     */
    public void setContactid(Long contactid) {
        this.contactid = contactid;
    }

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
     * @return the valid
     */
    public Boolean getValid() {
        return valid;
    }

    /**
     * @param valid the valid to set
     */
    public void setValid(Boolean valid) {
        this.valid = valid;
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
    public IdType getIdType() {
        return idType;
    }

    /**
     * @param idType the idType to set
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
    public Gender getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(Gender gender) {
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

    /**
     * @return the emergencyContact
     */
    public String getEmergencyContact() {
        return emergencyContact;
    }

    /**
     * @param emergencyContact the emergencyContact to set
     */
    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    /**
     * @return the emergencyMobile
     */
    public String getEmergencyMobile() {
        return emergencyMobile;
    }

    /**
     * @param emergencyMobile the emergencyMobile to set
     */
    public void setEmergencyMobile(String emergencyMobile) {
        this.emergencyMobile = emergencyMobile;
    }

    /**
     * @return the addTime
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * @param addTime the addTime to set
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
     * @param modTime the modTime to set
     */
    public void setModTime(Date modTime) {
        this.modTime = modTime;
    }

}