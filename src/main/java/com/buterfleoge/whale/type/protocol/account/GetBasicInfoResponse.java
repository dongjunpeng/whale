package com.buterfleoge.whale.type.protocol.account;

import com.buterfleoge.whale.type.protocol.Response;
import com.buterfleoge.whale.type.protocol.account.object.AccountBasicInfo;

/**
 *
 * @author xiezhenzong
 *
 */
public class GetBasicInfoResponse extends Response {

    private AccountBasicInfo accountBasicInfo;

    /**
     * @return the accountBasicInfo
     */
    public AccountBasicInfo getAccountBasicInfo() {
        return accountBasicInfo;
    }

    /**
     * @param accountBasicInfo
     *            the accountBasicInfo to set
     */
    public void setAccountBasicInfo(AccountBasicInfo accountBasicInfo) {
        this.accountBasicInfo = accountBasicInfo;
    }

}
