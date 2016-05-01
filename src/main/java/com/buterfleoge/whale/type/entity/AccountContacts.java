package com.buterfleoge.whale.type.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.type.enums.Gender;
import com.buterfleoge.whale.type.enums.IdType;

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

    @Column(name = "is_default")
    private Boolean isDefault;

    @Column(name = "valid")
    private Boolean valid;

    @Column(name = "name")
    private String name;

    @Column(name = "id")
    private String id;

    @Column(name = "id_type")
    private IdType idType;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "birthday")
    private Long birthday;

    @Column(name = "address")
    private String address;

    @Column(name = "emergency_contact")
    private String emergencyContact;

    @Column(name = "emergency_mobile")
    private String emergencyMobile;

    @Column(name = "add_time")
    private Long addTime;

    @Column(name = "mod_time")
    private Long modTime;

    public Long getContactid() {
        return contactid;
    }

    public void setContactid(Long contactid) {
        this.contactid = contactid;
    }

    public Long getAccountid() {
        return accountid;
    }

    public void setAccountid(Long accountid) {
        this.accountid = accountid;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getEmergencyMobile() {
        return emergencyMobile;
    }

    public void setEmergencyMobile(String emergencyMobile) {
        this.emergencyMobile = emergencyMobile;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Long getModTime() {
        return modTime;
    }

    public void setModTime(Long modTime) {
        this.modTime = modTime;
    }

}