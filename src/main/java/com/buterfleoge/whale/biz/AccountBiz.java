package com.buterfleoge.whale.biz;

import com.buterfleoge.whale.type.entity.AccountInfo;
import com.buterfleoge.whale.type.protocol.Response;
import com.buterfleoge.whale.type.protocol.account.DeleteContactsRequest;
import com.buterfleoge.whale.type.protocol.account.GetContactsRequest;
import com.buterfleoge.whale.type.protocol.account.GetContactsResponse;
import com.buterfleoge.whale.type.protocol.account.GetCouponsRequest;
import com.buterfleoge.whale.type.protocol.account.GetCouponsResponse;
import com.buterfleoge.whale.type.protocol.account.GetWxShareConfigRequest;
import com.buterfleoge.whale.type.protocol.account.GetWxShareConfigResponse;
import com.buterfleoge.whale.type.protocol.account.PostBasicInfoRequest;
import com.buterfleoge.whale.type.protocol.account.PostContactsRequest;
import com.buterfleoge.whale.type.protocol.order.ValidateCodeRequest;
import com.buterfleoge.whale.type.protocol.order.ValidateCodeResponse;

/**
 * 账户相关的操作
 *
 * @author xiezhenzong
 *
 */
public interface AccountBiz {

    AccountInfo updateBasicInfo(Long accountid, PostBasicInfoRequest request, Response response) throws Exception;

    void getContacts(Long accountid, GetContactsRequest request, GetContactsResponse response) throws Exception;

    void postContacts(Long accountid, PostContactsRequest request, Response response) throws Exception;

    void deleteContacts(Long accountid, DeleteContactsRequest request, Response response) throws Exception;

    void getCoupons(Long accountid, GetCouponsRequest request, GetCouponsResponse response) throws Exception;

    void validateDiscountCode(Long accountid, ValidateCodeRequest request, ValidateCodeResponse response) throws Exception;

    void getWxShareConfig(Long accountid, GetWxShareConfigRequest request, GetWxShareConfigResponse response) throws Exception;

}
