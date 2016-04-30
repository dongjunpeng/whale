package com.buterfleoge.whale.type.protocol.account;

import com.buterfleoge.whale.type.entity.AccountInfo;
import com.buterfleoge.whale.type.entity.AccountSetting;
import com.buterfleoge.whale.type.protocol.Response;

/**
 *
 * @author xiezhenzong
 *
 */
public class GetBasicInfoResponse extends Response {

	private AccountInfo accountInfo;
	private AccountSetting accountSetting;

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
	 * @return the accountSetting
	 */
	public AccountSetting getAccountSetting() {
		return accountSetting;
	}

	/**
	 * @param accountSetting
	 *            the accountSetting to set
	 */
	public void setAccountSetting(AccountSetting accountSetting) {
		this.accountSetting = accountSetting;
	}

}
