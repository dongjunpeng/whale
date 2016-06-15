package com.buterfleoge.whale.type.protocol.account;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.buterfleoge.whale.type.Gender;
import com.buterfleoge.whale.type.IdType;
import com.buterfleoge.whale.type.protocol.Request;

/**
 * @author Brent24
 *
 */
public class PutContactsRequest extends Request {

	@NotNull
	private Long contactid;

	@NotNull
	private Boolean isDefault;

	@NotBlank
	private String name;

	@NotBlank
	private String id;

	@NotNull
	private IdType idType;

	@NotBlank
	private String email;

	@NotBlank
	private String mobile;

	@NotNull
	private Gender gender;

	@NotNull
	private Long birthday;

	@NotBlank
	private String address;

	@NotBlank
	private String emergencyContact;

	@NotBlank
	private String emergencyMobile;

	public Long getContactid() {
		return contactid;
	}

	public void setContactid(Long contactid) {
		this.contactid = contactid;
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
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

}
