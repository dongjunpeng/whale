package com.buterfleoge.whale.type;

import com.buterfleoge.whale.Utils;

/**
 * 账户状态
 *
 * @author xiezhenzong
 *
 */
public enum AccountStatus {

    /**
     * 刚注册
     */
    WAIT_COMPLETE_INFO(0),

    /**
     * 账户正常
     */
    OK(1),

    /**
     * 账户注销
     */
    DELETE(2)

    ;

    private int status;

    private AccountStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public static final AccountStatus valueOf(int status) {
        for (AccountStatus as : values()) {
            if (as.getStatus() == status) {
                return as;
            }
        }
        throw new IllegalArgumentException("Can't find AccountStatus, status: " + status);
    }

    @Override
    public String toString() {
        return Utils.enumToString(this);
    }

}
