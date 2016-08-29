package com.buterfleoge.whale.type;

import com.buterfleoge.whale.EnumObject;

/**
 * 账户类型
 *
 * @author xiezhenzong
 *
 */
public class AccountType extends EnumObject {

    /**
     * 普通账户
     */
    public static final AccountType USER = new AccountType(0);

    /**
     * 领队
     */
    public static final AccountType LEADER = new AccountType(1);

    /**
     * 管理员
     */
    public static final AccountType MANAGER = new AccountType(2);

    /**
     * 合作伙伴
     */
    public static final AccountType PARTNER = new AccountType(3);

    /**
     * 代理商
     */
    public static final AccountType AGENT = new AccountType(4);

    /**
     * 超级管理员
     */
    public static final AccountType ROOT = new AccountType(5);
    
    public static final EnumObjectHelper<AccountType> helper = EnumObjectHelper.create(USER, LEADER, MANAGER, PARTNER, AGENT, ROOT);

    private AccountType(int value) {
        super(value);
    }

}
