package com.buterfleoge.whale.eo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.buterfleoge.whale.BaseObject;

@Entity
@Table(name = "traveller")
public class Traveller extends BaseObject implements Cloneable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "traveller_id")
	private Long travellerId;

	@Column(name = "user_id")
	private long userid;

	@Column(name = "identification")
	private String identification;

	@Column(name = "id_type")
	private String idType;

	@Column(name = "name")
	private String name;

	@Column(name = "cellphone")
	private String cellphone;

	@Column(name = "emergency_contact")
	private String emergencyContact;

	@Column(name = "emergency_phone")
	private String emergencyPhone;

	public Long getTravellerId() {
		return travellerId;
	}

	public void setTravellerId(Long travellerId) {
		this.travellerId = travellerId;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
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

	public Traveller clone() throws CloneNotSupportedException {
		Traveller traveller = (Traveller) super.clone();
		return traveller;
	}

}
