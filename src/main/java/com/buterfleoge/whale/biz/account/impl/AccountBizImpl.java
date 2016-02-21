package com.buterfleoge.whale.biz.account.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buterfleoge.whale.biz.account.AccountBiz;
import com.buterfleoge.whale.constant.Constants.BizCode;
import com.buterfleoge.whale.constant.Constants.ErrorMsg;
import com.buterfleoge.whale.constant.Constants.Status;
import com.buterfleoge.whale.dao.AccountInfoRepository;
import com.buterfleoge.whale.type.AccountStatus;
import com.buterfleoge.whale.type.entity.AccountInfo;
import com.buterfleoge.whale.type.protocol.Error;
import com.buterfleoge.whale.type.protocol.Request;
import com.buterfleoge.whale.type.protocol.Response;
import com.buterfleoge.whale.type.protocol.account.EmailExistRequestItem;
import com.buterfleoge.whale.type.protocol.account.LoginRequestItem;
import com.buterfleoge.whale.type.protocol.account.RegisterRequestItem;

/**
 * 账户相关的操作
 *
 * @author xiezhenzong
 *
 */
@Service("accountBiz")
public class AccountBizImpl implements AccountBiz {

    private static final Logger LOG = LoggerFactory.getLogger(AccountBizImpl.class);
    private static final AccountStatus INIT_STATUS = AccountStatus.WAIT_ACTIVE;

    @Autowired
    private AccountInfoRepository accountInfoRepository;

    @Override
    public void isEmailExist(Request<EmailExistRequestItem> request, Response<Void> response) throws Exception {
        String email = request.getFirstDataItem().getEmail();
        try {
            if (accountInfoRepository.countByEmail(email) == 0) {
                response.setStatus(Status.OK);
            } else {
                response.setStatus(Status.BIZ_ERROR);
                response.addError(new Error(BizCode.EMAIL_EXIST, ErrorMsg.EMAIL_EXIST));
            }
        } catch (Exception e) {
            LOG.error("Check email exist from db failed", e);
            response.setStatus(Status.DB_ERROR);
        }
    }

    @Override
    public void registerByEmail(Request<RegisterRequestItem> request, Response<Void> response) throws Exception {
        RegisterRequestItem requestItem = request.getFirstDataItem();
        AccountInfo info = new AccountInfo();
        info.setStatus(INIT_STATUS);
        info.setEmail(requestItem.getEmail());
        info.setPassword(requestItem.getPassword());
        info.setType(requestItem.getType());
        info.setAddTime(System.currentTimeMillis());
        info.setModTime(info.getAddTime());
        try {
            accountInfoRepository.save(info);
            response.setStatus(Status.OK);
        } catch (Exception e) {
            LOG.error("Save account info to db failed", e);
            response.setStatus(Status.DB_ERROR);
        }
    }

    @Override
    public void loginByEmail(Request<LoginRequestItem> request, Response<AccountInfo> response) throws Exception {
        LoginRequestItem requestItem = request.getFirstDataItem();
       
        try {
            AccountInfo info = accountInfoRepository.findByEmail(requestItem.getEmail());
            response.setStatus(Status.OK);
            if (info == null) {
                response.addError(new Error(BizCode.EMAIL_NO_EXIST, ErrorMsg.EMAIL_NO_EXIST));
                return;
            }

            if (!requestItem.getPassword().equals(info.getPassword())) {
                response.addError(new Error(BizCode.WRONG_PASSWORD, ErrorMsg.WRONG_PASSWORD));
                return;
            }

            if (!requestItem.getType().equals(info.getType())) {
                response.addError(new Error(BizCode.WRONG_ACCOUNT_TYPE, ErrorMsg.WRONG_ACCOUNT_TYPE));
                return;
            }
            info.setPassword(null);
            response.addData(info);
        } catch (Exception e) {
            LOG.error("Find account info from db failed", e);
            response.setStatus(Status.DB_ERROR);
        }
    }

}
