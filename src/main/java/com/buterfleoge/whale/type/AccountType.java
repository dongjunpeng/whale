package com.buterfleoge.whale.type;

import com.buterfleoge.whale.Utils;

/**
 * 账户类型
 *
 * @author xiezhenzong
 *
 */
public enum AccountType {

    /**
     * 普通账户
     */
    USER(0),

    /**
     * 领队
     */
    LEADER(1),

    /**
     * 管理员
     */
    MANAGER(2),

    /**
     * 合作伙伴
     */
    PARTNER(3),

    /**
     * 代理商
     */
    AGENT(4),

    /**
     * 超级管理员
     */
    ROOT(5)

    ;

    private int type;

    private AccountType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static final AccountType valueOf(int type) {
        for (AccountType at : values()) {
            if (at.getType() == type) {
                return at;
            }
        }
        throw new IllegalArgumentException("Can't find AccountType, type: " + type);
    }

    @Override
    public String toString() {
        return Utils.enumToString(this);
    }

}
