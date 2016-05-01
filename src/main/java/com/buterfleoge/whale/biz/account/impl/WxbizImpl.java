package com.buterfleoge.whale.biz.account.impl;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.buterfleoge.whale.biz.account.WxBiz;
import com.buterfleoge.whale.type.protocol.wx.WxAccessTokenResponse;
import com.buterfleoge.whale.type.protocol.wx.WxAuthResponse;
import com.buterfleoge.whale.type.protocol.wx.WxUserinfoResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author xiezhenzong
 *
 */
@Service("wxBiz")
public class WxbizImpl implements WxBiz {

    @Value("${wx.appid}")
    private String appid;

    @Value("${wx.appsecret}")
    private String appsecret;

    @Value("${wx.login.qrconnect}")
    private String wxLoginQrconnect;

    @Value("${wx.api.accessToken}")
    private String wxApiAccessToken;

    @Value("${wx.api.refreshToken}")
    private String wxApiRefreshToken;

    @Value("${wx.api.auth}")
    private String wxApiAuth;

    @Value("${wx.api.userinfo}")
    private String wxApiUserinfo;

    @Override
    public String getLoginUri(String state, String redirectUri) throws Exception {
        StringBuilder sb = new StringBuilder(wxLoginQrconnect) //
                .append("?appid=").append(appid) //
                .append("&redirect_uri=").append(redirectUri) //
                .append("&response_type=code&score=snsapi_login") //
                .append("&state=").append(state).append("#wechat_redirect");
        return sb.toString();
    }

    @Override
    public WxAccessTokenResponse getAccessToken(String code) throws Exception {
        String uri = new StringBuilder(wxApiAccessToken) //
                .append("?appid=").append(appid) //
                .append("&secret=").append(appsecret) //
                .append("&code=").append(code) //
                .append("&grant_type=authorization_code").toString();
        return getWx(uri, WxAccessTokenResponse.class);
    }

    @Override
    public WxAccessTokenResponse refreshToken(String refreshToken) throws Exception {
        String uri = new StringBuilder(wxApiAccessToken) //
                .append("?appid=").append(appid) //
                .append("&grant_type=refresh_token") //
                .append("&refresh_token=").append(refreshToken).toString();
        return getWx(uri, WxAccessTokenResponse.class);
    }

    @Override
    public boolean isAccessTokenValid(String accessToken, String openid) throws Exception {
        String uri = new StringBuilder(wxApiAuth) //
                .append("?access_token=").append(accessToken) //
                .append("&openid=").append(openid).toString();
        WxAuthResponse response = getWx(uri, WxAuthResponse.class);
        return response.getErrcode() == WxAuthResponse.CODE_OK;
    }

    @Override
    public WxUserinfoResponse getUserinfo(String accessToken, String openid) throws Exception {
        String uri = new StringBuilder(wxApiUserinfo) //
                .append("?access_token=").append(accessToken) //
                .append("&openid=").append(openid).toString();
        return getWx(uri, WxUserinfoResponse.class);
    }

    private <T> T getWx(String uri, Class<T> responseType) throws Exception {
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        try {
            httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(uri);
            response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(entity.getContent(), responseType);
            } else {
                throw new IllegalStateException("wx response is empty");
            }
        } finally {
            try {
                if (httpclient != null) {
                    httpclient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
            }
        }
    }

}
