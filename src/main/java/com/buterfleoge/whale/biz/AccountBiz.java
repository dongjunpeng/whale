package com.buterfleoge.whale.biz;

import com.buterfleoge.whale.type.AccountType;

/**
 * 
 *
 * @author xiezhenzong
 *
 */
public interface AccountBiz {

    boolean isEmailExist(String email) throws Exception;

    void createAccount(String email, String password, AccountType type) throws Exception;

}
