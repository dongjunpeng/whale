package com.buterfleoge.whale.type.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.type.Gender;

/**
 * 账户的设置信息
 * 
 * @author Brent24
 */
@Entity
@Table(name = "account_setting")
public class AccountSetting extends BaseObject {

	@Id
	@Column(name = "accountid")
	private Long accountid;

	@Column(name = "nickname")
	private String nickname = "";

	@Column(name = "wxid")
	private String wxid = "";

	@Column(name = "wxname")
	private String wxname = "";

	@Column(name = "qqid")
	private String qqid = "";

	@Column(name = "qqname")
	private String qqname = "";

	@Column(name = "wbid")
	private String wbid = "";

	@Column(name = "wbname")
	private String wbname = "";

	@Column(name = "gender")
	private Gender gender = Gender.UNKNOW;

	@Column(name = "birthday")
	private Long birthday = 0L;

	@Column(name = "address")
	private String address = "";

	@Column(name = "avatar_url")
	private String avatarUrl = "";

	@Column(name = "mod_time")
	private Long modTime;

	/**
	 * @return the accountid
	 */
	public Long getAccountid() {
		return accountid;
	}

	/**
	 * @param accountid
	 *            the accountid to set
	 */
	public void setAccountid(Long accountid) {
		this.accountid = accountid;
	}

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname
	 *            the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the wxid
	 */
	public String getWxid() {
		return wxid;
	}

	/**
	 * @param wxid
	 *            the wxid to set
	 */
	public void setWxid(String wxid) {
		this.wxid = wxid;
	}

	/**
	 * @return the wxname
	 */
	public String getWxname() {
		return wxname;
	}

	/**
	 * @param wxname
	 *            the wxname to set
	 */
	public void setWxname(String wxname) {
		this.wxname = wxname;
	}

	/**
	 * @return the qqid
	 */
	public String getQqid() {
		return qqid;
	}

	/**
	 * @param qqid
	 *            the qqid to set
	 */
	public void setQqid(String qqid) {
		this.qqid = qqid;
	}

	/**
	 * @return the qqname
	 */
	public String getQqname() {
		return qqname;
	}

	/**
	 * @param qqname
	 *            the qqname to set
	 */
	public void setQqname(String qqname) {
		this.qqname = qqname;
	}

	/**
	 * @return the wbid
	 */
	public String getWbid() {
		return wbid;
	}

	/**
	 * @param wbid
	 *            the wbid to set
	 */
	public void setWbid(String wbid) {
		this.wbid = wbid;
	}

	/**
	 * @return the wbname
	 */
	public String getWbname() {
		return wbname;
	}

	/**
	 * @param wbname
	 *            the wbname to set
	 */
	public void setWbname(String wbname) {
		this.wbname = wbname;
	}

	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * @return the birthday
	 */
	public Long getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(Long birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the avatarUrl
	 */
	public String getAvatarUrl() {
		return avatarUrl;
	}

	/**
	 * @param avatarUrl
	 *            the avatarUrl to set
	 */
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	/**
	 * @return the modTime
	 */
	public Long getModTime() {
		return modTime;
	}

	/**
	 * @param modTime
	 *            the modTime to set
	 */
	public void setModTime(Long modTime) {
		this.modTime = modTime;
	}

}
