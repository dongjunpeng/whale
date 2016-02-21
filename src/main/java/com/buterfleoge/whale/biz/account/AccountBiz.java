package com.buterfleoge.whale.biz.account;

import com.buterfleoge.whale.type.entity.AccountInfo;
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
public interface AccountBiz {

    void isEmailExist(Request<EmailExistRequestItem> request, Response<Void> response) throws Exception;

    void registerByEmail(Request<RegisterRequestItem> request, Response<Void> response) throws Exception;

    void loginByEmail(Request<LoginRequestItem> request, Response<AccountInfo> response) throws Exception;

}
