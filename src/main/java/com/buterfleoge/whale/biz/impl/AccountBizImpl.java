package com.buterfleoge.whale.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buterfleoge.whale.biz.AccountBiz;
import com.buterfleoge.whale.dao.AccountInfoRepository;
import com.buterfleoge.whale.type.AccountStatus;
import com.buterfleoge.whale.type.AccountType;
import com.buterfleoge.whale.type.entity.AccountInfo;
import com.buterfleoge.whale.util.Utils;

/**
 * 
 *
 * @author xiezhenzong
 *
 */
@Service("accountBiz")
public class AccountBizImpl implements AccountBiz {

    private static final AccountStatus INIT_STATUS = AccountStatus.wait_active;

    @Autowired
    private AccountInfoRepository accountInfoRepository;

    public boolean isEmailExist(String email) throws Exception {
        Utils.rejectIfNotEmail(email, "email argument is invalid");
        long count = accountInfoRepository.countByEmail(email);
        return count > 0;
    }

    public void createAccount(String email, String password, AccountType type) throws Exception {
        Utils.rejectIfEmpty(email, "email can't be empty");
        Utils.rejectIfEmpty(password, "password can't be empty");
        Utils.rejectIfNull(type, "type can't be null");

        AccountInfo info = new AccountInfo();
        info.setAtype(type);
        info.setStatus(INIT_STATUS);
        info.setEmail(email);
        info.setPassword(password);

        accountInfoRepository.save(info);
    }

}
