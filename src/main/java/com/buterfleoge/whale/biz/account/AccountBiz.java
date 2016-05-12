package com.buterfleoge.whale.biz.account;

import com.buterfleoge.whale.type.protocol.Response;
import com.buterfleoge.whale.type.protocol.account.DeleteContactsRequest;
import com.buterfleoge.whale.type.protocol.account.GetContactsRequest;
import com.buterfleoge.whale.type.protocol.account.GetContactsResponse;
import com.buterfleoge.whale.type.protocol.account.PostBasicInfoRequest;
import com.buterfleoge.whale.type.protocol.account.PostContactsRequest;

/**
 * 账户相关的操作
 *
 * @author xiezhenzong
 *
 */
public interface AccountBiz {

    void updateBasicInfo(PostBasicInfoRequest request, Response response) throws Exception;

    void getContacts(GetContactsRequest request, GetContactsResponse response) throws Exception;

    void postContacts(PostContactsRequest request, Response response) throws Exception;

    void deleteContacts(DeleteContactsRequest request, Response response) throws Exception;


}
