/**
 *
 */
package com.buterfleoge.whale.service;

import com.buterfleoge.whale.service.weixin.protocol.WxAccessTokenResponse;
import com.buterfleoge.whale.service.weixin.protocol.WxLoginScope;
import com.buterfleoge.whale.service.weixin.protocol.WxUserinfoResponse;

/**
 * @author xiezhenzong
 *
 */
public interface WeixinWebService {

    String getLoginUri(String state, String redirectUri, WxLoginScope scope);

    WxAccessTokenResponse getAccessToken(String code);

    WxAccessTokenResponse refreshToken(String refreshToken);

    boolean isAccessTokenValid(String accessToken, String openid);

    WxUserinfoResponse getUserinfo(String accessToken, String openid);

}
