package com.buterfleoge.whale.biz.account;

import com.buterfleoge.whale.type.protocol.Request;
import com.buterfleoge.whale.type.protocol.Response;
import com.buterfleoge.whale.type.protocol.account.DeleteContactsRequest;
import com.buterfleoge.whale.type.protocol.account.GetContactsRequest;
import com.buterfleoge.whale.type.protocol.account.GetContactsResponse;
import com.buterfleoge.whale.type.protocol.account.GetDiscountCodeResponse;
import com.buterfleoge.whale.type.protocol.account.GetWxShareConfigRequest;
import com.buterfleoge.whale.type.protocol.account.GetWxShareConfigResponse;
import com.buterfleoge.whale.type.protocol.account.PostBasicInfoRequest;
import com.buterfleoge.whale.type.protocol.account.PostContactsRequest;

/**
 * 账户相关的操作
 *
 * @author xiezhenzong
 *
 */
public interface AccountBiz {

    void updateBasicInfo(Long accountid, PostBasicInfoRequest request, Response response) throws Exception;

    void getContacts(Long accountid, GetContactsRequest request, GetContactsResponse response) throws Exception;

    void postContacts(Long accountid, PostContactsRequest request, Response response) throws Exception;

    void deleteContacts(Long accountid, DeleteContactsRequest request, Response response) throws Exception;

    void getDiscountCode(Long accoutid, Request request, GetDiscountCodeResponse response) throws Exception;

    void getWxShareConfig(Long accountid, GetWxShareConfigRequest request, GetWxShareConfigResponse response) throws Exception;

}
