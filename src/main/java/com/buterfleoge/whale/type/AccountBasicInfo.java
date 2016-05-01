package com.buterfleoge.whale.type;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.type.entity.AccountInfo;
import com.buterfleoge.whale.type.entity.AccountSetting;

/**
 * @author Brent24
 *
 */
public class AccountBasicInfo extends BaseObject {

    private AccountInfo accountInfo;
    private AccountSetting accountSetting;

    /**
     * @return the accountInfo
     */
    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    /**
     * @param accountInfo the accountInfo to set
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
     * @param accountSetting the accountSetting to set
     */
    public void setAccountSetting(AccountSetting accountSetting) {
        this.accountSetting = accountSetting;
    }

}
