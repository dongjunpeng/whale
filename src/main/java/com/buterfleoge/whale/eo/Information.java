package com.buterfleoge.whale.eo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.buterfleoge.whale.BaseObject;

@Entity
@Table(name = "information")
public class Information extends BaseObject {

	@Id
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "nickname")
	private String nickname;

	@Column(name = "gender")
	private String gender;

	@Column(name = "birthday")
	private String birthday;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

}
