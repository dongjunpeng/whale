package com.buterfleoge.whale.type.protocol.account;

import com.buterfleoge.whale.type.entity.AccountInfo;
import com.buterfleoge.whale.type.entity.AccountSetting;
import com.buterfleoge.whale.type.protocol.Response;

/**
 *
 * @author xiezhenzong
 *
 */
public class RegisterResponse extends Response {

	private AccountInfo accountInfo;
	private AccountSetting accountSetting;
	private String redirect = "/account";

	/**
	 * @return the accountInfo
	 */
	public AccountInfo getAccountInfo() {
		return accountInfo;
	}

	/**
	 * @param accountInfo
	 *            the accountInfo to set
	 */
	public void setAccountInfo(AccountInfo accountInfo) {
		this.accountInfo = accountInfo;
	}

	/**
	 * @return the redirect
	 */
	public String getRedirect() {
		return redirect;
	}

	/**
	 * @param redirect
	 *            the redirect to set
	 */
	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}

	public AccountSetting getAccountSetting() {
		return accountSetting;
	}

	public void setAccountSetting(AccountSetting accountSetting) {
		this.accountSetting = accountSetting;
	}

}
