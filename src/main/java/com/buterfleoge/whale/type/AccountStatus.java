package com.buterfleoge.whale.type;

import com.buterfleoge.whale.EnumObject;

/**
 * 账户状态
 *
 * @author xiezhenzong
 *
 */
public class AccountStatus extends EnumObject {

    /**
     * 刚注册
     */
    public static final AccountStatus WAIT_COMPLETE_INFO = new AccountStatus(0);

    /**
     * 账户正常
     */
    public static final AccountStatus OK = new AccountStatus(1);

    /**
     * 账户注销
     */
    public static final AccountStatus DELETE = new AccountStatus(2);

    public static final EnumObjectHelper<AccountStatus> helper = EnumObjectHelper.create(WAIT_COMPLETE_INFO, OK, DELETE);

    private AccountStatus(int value) {
        super(value);
    }

}
