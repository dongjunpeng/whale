/**
 * 
 */
package com.buterfleoge.whale.type.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.type.Gender;
import com.buterfleoge.whale.type.IdType;

/**
 * @author Brent24
 *
 */

@Entity
@Table(name = "traveller_info")
public class TravellerInfo extends BaseObject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "travellerid")
	private long travellerid;

	@Column(name = "accountid")
	private long accountid;

	@Column(name = "name")
	private String name="";

	@Column(name = "id")
	private String id="";

	@Column(name = "id_type")
	private IdType idType=IdType.IDENTIFICATION;

	@Column(name = "gender")
	private Gender gender=Gender.UNKNOW;

	@Column(name = "birthday")
	private String birthday="";

	@Column(name = "mobile")
	private String mobile="";

	@Column(name = "email")
	private String email="";

	@Column(name = "wxid")
	private String wxid="";

	@Column(name = "address")
	private String address="";

	@Column(name = "emergency_contact")
	private String emergencyContact="";

	@Column(name = "emergency_phone")
	private String emergencyPhone="";

	@Column(name = "add_time")
	private String addTime="";

	@Column(name = "mod_time")
	private String modTime="";

	public long getTravellerid() {
		return travellerid;
	}

	public void setTravellerid(long travellerid) {
		this.travellerid = travellerid;
	}

	public long getAccountid() {
		return accountid;
	}

	public void setAccountid(long accountid) {
		this.accountid = accountid;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getWxid() {
		return wxid;
	}

	public void setWxid(String wxid) {
		this.wxid = wxid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getEmergencyPhone() {
		return emergencyPhone;
	}

	public void setEmergencyPhone(String emergencyPhone) {
		this.emergencyPhone = emergencyPhone;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getModTime() {
		return modTime;
	}

	public void setModTime(String modTime) {
		this.modTime = modTime;
	}

}
