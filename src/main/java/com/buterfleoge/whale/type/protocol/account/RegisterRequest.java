package com.buterfleoge.whale.type.protocol.account;

import java.io.Serializable;

import com.buterfleoge.whale.BaseObject;

/**
 * 
 *
 * @author xiezhenzong
 *
 */
public class RegisterRequest extends BaseObject implements Serializable {

	/**
	 * serial version uid
	 */
	private static final long serialVersionUID = 749154490420349916L;

	private String email;
	private String password;
	private String wxid;
	private String qqid;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
