package com.buterfleoge.whale.type.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.buterfleoge.whale.BaseObject;

@Entity
@Table(name = "information")
public class Information extends BaseObject implements Cloneable {

	@Id
	@Column(name = "userid")
	private Long userid;

	@Column(name = "nickname")
	private String nickname;

	@Column(name = "gender")
	private String gender;

	@Column(name = "birthday")
	private String birthday;

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Information clone() throws CloneNotSupportedException {
		Information information = (Information) super.clone();
		return information;
	}
}
