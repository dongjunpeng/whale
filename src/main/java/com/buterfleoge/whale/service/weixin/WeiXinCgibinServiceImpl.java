package com.buterfleoge.whale.service.weixin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.buterfleoge.whale.service.WeixinWebService;
import com.buterfleoge.whale.service.weixin.protocol.WxAccessTokenResponse;
import com.buterfleoge.whale.service.weixin.protocol.WxLoginScope;

/**
 * @author xiezhenzong
 *
 */
@Service("weixinCgibinService")
public class WeiXinCgibinServiceImpl extends WeixinWebServiceImpl implements WeixinWebService {

    @Value("${wx.cgi-bin.appid}")
    private String appid;

    @Value("${wx.cgi-bin.appsecret}")
    private String appsecret;

    @Value("${wx.login.oauth}")
    private String wxOauth;

    @Override
    public String getLoginUri(String state, String redirectUri, WxLoginScope scope) {
        StringBuilder sb = new StringBuilder(wxOauth) //
                .append("?appid=").append(appid) //
                .append("&redirect_uri=").append(redirectUri) //
                .append("&response_type=code&scope=").append(scope.value) //
                .append("&state=").append(state).append("#wechat_redirect");
        return sb.toString();
    }

    @Override
    public WxAccessTokenResponse getAccessToken(String code) {
        String uri = new StringBuilder(wxApiAccessToken) //
                .append("?appid=").append(appid) //
                .append("&secret=").append(appsecret) //
                .append("&code=").append(code) //
                .append("&grant_type=authorization_code").toString();
        return queryWeixin(wxApiAccessToken, uri, WxAccessTokenResponse.class);
    }

    @Override
    public WxAccessTokenResponse refreshToken(String refreshToken) {
        String uri = new StringBuilder(wxApiRefreshToken) //
                .append("?appid=").append(appid) //
                .append("&grant_type=refresh_token") //
                .append("&refresh_token=").append(refreshToken).toString();
        return queryWeixin(wxApiRefreshToken, uri, WxAccessTokenResponse.class);
    }

}
