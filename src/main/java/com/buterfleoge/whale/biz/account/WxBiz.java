package com.buterfleoge.whale.biz.account;

import com.buterfleoge.whale.type.protocol.wx.WxAccessTokenResponse;
import com.buterfleoge.whale.type.protocol.wx.WxUserinfoResponse;

/**
 *
 * @author xiezhenzong
 *
 */
public interface WxBiz {

    String getLoginUri(String state, String redirectUri);

    WxAccessTokenResponse getAccessToken(String code);

    WxAccessTokenResponse refreshToken(String refreshToken);

    boolean isAccessTokenValid(String accessToken, String openid);

    WxUserinfoResponse getUserinfo(String accessToken, String openid);


}
