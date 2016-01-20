package com.buterfleoge.whale.type.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.type.AccountStatus;
import com.buterfleoge.whale.type.AccountType;

/**
 * entity class for account_info table
 *
 * @author xiezhenzong
 *
 */

@Entity
@Table(name = "account_info")
public class AccountInfo extends BaseObject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "accountid")
	private long accountid;

	@Column(name = "name")
	private String name;

	@Column(name = "password")
	private String password;

	@Column(name = "type")
	private AccountType type;

	@Column(name = "status")
	private AccountStatus status;

	@Column(name = "id")
	private String id;

	@Column(name = "email")
	private String email;

	@Column(name = "wxid")
	private String wxid;

	@Column(name = "qqid")
	private String qqid;

	@Column(name = "wbid")
	private String wbid;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "add_time")
	private String addTime;

	@Column(name = "mod_time")
	private String modTime;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWxid() {
		return wxid;
	}

	public void setWxid(String wxid) {
		this.wxid = wxid;
	}

	public String getQqid() {
		return qqid;
	}

	public void setQqid(String qqid) {
		this.qqid = qqid;
	}

	public String getWbid() {
		return wbid;
	}

	public void setWbid(String wbid) {
		this.wbid = wbid;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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
