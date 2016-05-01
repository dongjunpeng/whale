package com.buterfleoge.whale.biz.account;

import com.buterfleoge.whale.type.protocol.wx.WxAccessTokenResponse;
import com.buterfleoge.whale.type.protocol.wx.WxUserinfoResponse;

/**
 *
 * @author xiezhenzong
 *
 */
public interface WxBiz {

    String getLoginUri(String state, String redirectUri) throws Exception;

    WxAccessTokenResponse getAccessToken(String code) throws Exception;

    WxAccessTokenResponse refreshToken(String refreshToken) throws Exception;

    boolean isAccessTokenValid(String accessToken, String openid) throws Exception;

    WxUserinfoResponse getUserinfo(String accessToken, String openid) throws Exception;


}
