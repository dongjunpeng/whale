package com.buterfleoge.whale.biz.account;

import com.buterfleoge.whale.type.entity.AccountInfo;
import com.buterfleoge.whale.type.entity.AccountSetting;
import com.buterfleoge.whale.type.protocol.Response;
import com.buterfleoge.whale.type.protocol.account.DeleteContactsRequest;
import com.buterfleoge.whale.type.protocol.account.EmailExistRequest;
import com.buterfleoge.whale.type.protocol.account.GetBasicInfoRequest;
import com.buterfleoge.whale.type.protocol.account.GetBasicInfoResponse;
import com.buterfleoge.whale.type.protocol.account.GetContactsRequest;
import com.buterfleoge.whale.type.protocol.account.GetContactsResponse;
import com.buterfleoge.whale.type.protocol.account.PostBasicInfoRequest;
import com.buterfleoge.whale.type.protocol.account.PostContactsRequest;
import com.buterfleoge.whale.type.protocol.account.PutContactsRequest;
import com.buterfleoge.whale.type.protocol.account.RegisterRequest;
import com.buterfleoge.whale.type.protocol.account.RegisterResponse;
import com.buterfleoge.whale.type.protocol.account.ValidateEmailRequest;

/**
 * 账户相关的操作
 *
 * @author xiezhenzong
 *
 */
public interface AccountBiz {

    void isEmailExist(EmailExistRequest request, Response response) throws Exception;

    void registerByEmail(RegisterRequest request, RegisterResponse response) throws Exception;

    void validateEmail(ValidateEmailRequest request, Response response) throws Exception;

    Long createBasicInfo() throws Exception;

    Long createBasicInfo(AccountInfo info, AccountSetting setting) throws Exception;

    void getBasicInfo(GetBasicInfoRequest request, GetBasicInfoResponse response) throws Exception;

    void updateBasicInfo(PostBasicInfoRequest request, Response response) throws Exception;

    void getContacts(GetContactsRequest request, GetContactsResponse response) throws Exception;

    void postContacts(PostContactsRequest request, Response response) throws Exception;

    void putContacts(PutContactsRequest request, Response response) throws Exception;

    void deleteContacts(DeleteContactsRequest request, Response response) throws Exception;

    // void loginByEmail(LoginRequest request, Response response) throws
    // Exception;

}
