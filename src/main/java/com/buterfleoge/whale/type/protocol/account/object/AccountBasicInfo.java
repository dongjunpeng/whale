package com.buterfleoge.whale.type.protocol.account.object;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.type.entity.AccountInfo;

/**
 * @author Brent24
 *
 */
public class AccountBasicInfo extends BaseObject {

    private AccountInfo accountInfo;

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

}
