package com.buterfleoge.whale.biz.account.impl;

import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.buterfleoge.whale.Constants.BizCode;
import com.buterfleoge.whale.Constants.DefaultValue;
import com.buterfleoge.whale.Constants.ErrorMsg;
import com.buterfleoge.whale.Constants.Key;
import com.buterfleoge.whale.Constants.Prefix;
import com.buterfleoge.whale.Constants.Status;
import com.buterfleoge.whale.Utils;
import com.buterfleoge.whale.biz.account.AccountBiz;
import com.buterfleoge.whale.dao.AccountInfoRepository;
import com.buterfleoge.whale.service.MailService;
import com.buterfleoge.whale.service.mail.MailInfo;
import com.buterfleoge.whale.type.AccountStatus;
import com.buterfleoge.whale.type.entity.AccountInfo;
import com.buterfleoge.whale.type.protocol.Error;
import com.buterfleoge.whale.type.protocol.Response;
import com.buterfleoge.whale.type.protocol.account.EmailExistRequest;
import com.buterfleoge.whale.type.protocol.account.RegisterRequest;
import com.buterfleoge.whale.type.protocol.account.RegisterResponse;
import com.buterfleoge.whale.type.protocol.account.ValidateEmailRequest;

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

    @Autowired
    private MailService mailService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void isEmailExist(EmailExistRequest request, Response response) throws Exception {
        String email = request.getEmail();
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
    public void registerByEmail(RegisterRequest request, RegisterResponse response) throws Exception {
        EmailExistRequest emailEmailRequest = createEmailExistRequestFromRegisterRequest(request);
        isEmailExist(emailEmailRequest, response);
        if (response.hasError()) {
            return;
        }

        boolean isPasswordValid = Utils.isPasswordValid(request.getPassword());
        if (!isPasswordValid) {
            response.setStatus(Status.BIZ_ERROR);
            response.addError(new Error(BizCode.INVALID_PASSWORD, ErrorMsg.INVALID_PASSWORD));
            return;
        }

        AccountInfo info = new AccountInfo();
        info.setStatus(INIT_STATUS);
        info.setEmail(request.getEmail());
        info.setPassword(request.getPassword());
        info.setType(request.getType());
        info.setAddTime(System.currentTimeMillis());
        info.setModTime(info.getAddTime());
        try {
            AccountInfo accountInfo = accountInfoRepository.save(info);
            response.setAccountInfo(accountInfo);
            response.setStatus(Status.OK);

            InetAddress address = Utils.getAddress();
            String validKey =
                    Utils.stringMD5(address.getHostName() + "?" + accountInfo.toString() + System.currentTimeMillis());
            String validCode = validKey.substring(0, 6);
            MailInfo mailInfo = new MailInfo();
            mailInfo.addInfo(Key.VALID_MAIL_VALID_CODE, validCode);
            boolean isAdd2Pool = mailService.sendMail(mailInfo);
            if (isAdd2Pool) {
                String key = Prefix.VALID_MAIL_CACHE_PREFIX + accountInfo.getEmail();
                stringRedisTemplate.opsForValue().set(key, validCode, DefaultValue.VALID_MAIL_INVALID_PERIOD,
                        TimeUnit.MILLISECONDS);
            }
        } catch (Exception e) {
            LOG.error("Save account info to db failed", e);
            response.setStatus(Status.DB_ERROR);
        }
    }

    /**
     * @param request
     * @return
     */
    private EmailExistRequest createEmailExistRequestFromRegisterRequest(RegisterRequest request) {
        String email = request.getEmail();
        EmailExistRequest emailEmailRequest = new EmailExistRequest();
        emailEmailRequest.setEmail(email);
        return emailEmailRequest;
    }

    @Override
    public void validateEmail(ValidateEmailRequest request, Response response) throws Exception {
        String email = request.getEmail();
        String validCode = request.getValidCode();

        String key = Prefix.VALID_MAIL_CACHE_PREFIX + email;
        String validCodeInCache = stringRedisTemplate.opsForValue().get(key);
        if (validCode.equals(validCodeInCache)) {
            try {
                AccountInfo accountInfo = accountInfoRepository.findByEmail(email);
                accountInfo.setStatus(AccountStatus.OK);
                accountInfoRepository.save(accountInfo);
                response.setStatus(Status.OK);
            } catch (Exception e) {
                LOG.error("validate email failed", e);
                response.setStatus(Status.DB_ERROR);
            }
        } else {
            response.setStatus(Status.BIZ_ERROR);
            response.addError(new Error(BizCode.INVALID_VALID_CODE, ErrorMsg.INVALID_VALID_CODE));
        }
    }

    // @Override
    // public void loginByEmail(Request<LoginRequest> request, Response<AccountInfo> response) throws Exception {
    // LoginRequest requestItem = request.getFirstDataItem();
    //
    // try {
    // AccountInfo info = accountInfoRepository.findByEmail(requestItem.getEmail());
    // response.setStatus(Status.OK);
    // if (info == null) {
    // response.addError(new Error(BizCode.EMAIL_NO_EXIST, ErrorMsg.EMAIL_NO_EXIST));
    // return;
    // }
    //
    // if (!requestItem.getPassword().equals(info.getPassword())) {
    // response.addError(new Error(BizCode.WRONG_PASSWORD, ErrorMsg.WRONG_PASSWORD));
    // return;
    // }
    //
    // if (!requestItem.getType().equals(info.getType())) {
    // response.addError(new Error(BizCode.WRONG_ACCOUNT_TYPE, ErrorMsg.WRONG_ACCOUNT_TYPE));
    // return;
    // }
    // info.setPassword(null);
    // response.addData(info);
    // } catch (Exception e) {
    // LOG.error("Find account info from db failed", e);
    // response.setStatus(Status.DB_ERROR);
    // }
    // }

}
