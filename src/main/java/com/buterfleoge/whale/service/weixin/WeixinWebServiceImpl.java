/**
 *
 */
package com.buterfleoge.whale.service.weixin;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.buterfleoge.whale.Constants.Status;
import com.buterfleoge.whale.log.InvokeLogger;
import com.buterfleoge.whale.service.WeixinWebService;
import com.buterfleoge.whale.service.weixin.protocol.WxAccessTokenResponse;
import com.buterfleoge.whale.service.weixin.protocol.WxAuthResponse;
import com.buterfleoge.whale.service.weixin.protocol.WxLoginScope;
import com.buterfleoge.whale.service.weixin.protocol.WxResponse;
import com.buterfleoge.whale.service.weixin.protocol.WxUserinfoResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author xiezhenzong
 *
 */
@Service("weixinWebService")
public class WeixinWebServiceImpl implements WeixinWebService {

    private static final Logger LOG = LoggerFactory.getLogger(WeixinWebServiceImpl.class);

    @Value("${wx.appid}")
    private String appid;

    @Value("${wx.appsecret}")
    private String appsecret;

    @Value("${wx.login.qrconnect}")
    private String wxLoginQrconnect;

    @Value("${wx.api.accessToken}")
    protected String wxApiAccessToken;

    @Value("${wx.api.refreshToken}")
    private String wxApiRefreshToken;

    @Value("${wx.api.auth}")
    private String wxApiAuth;

    @Value("${wx.api.userinfo}")
    private String wxApiUserinfo;

    @Override
    public String getLoginUri(String state, String redirectUri, WxLoginScope scope) {
        StringBuilder sb = new StringBuilder(wxLoginQrconnect) //
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

    @Override
    public boolean isAccessTokenValid(String accessToken, String openid) {
        String uri = new StringBuilder(wxApiAuth) //
                .append("?access_token=").append(accessToken) //
                .append("&openid=").append(openid).toString();
        WxAuthResponse response = queryWeixin(wxApiAuth, uri, WxAuthResponse.class);
        return response != null && response.getErrcode() == WxResponse.CODE_OK;
    }

    @Override
    public WxUserinfoResponse getUserinfo(String accessToken, String openid) {
        String uri = new StringBuilder(wxApiUserinfo) //
                .append("?access_token=").append(accessToken) //
                .append("&openid=").append(openid).toString();
        return queryWeixin(wxApiUserinfo, uri, WxUserinfoResponse.class);
    }

    protected <T> T queryWeixin(String tag, String uri, Class<T> responseType) {
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse httpResponse = null;
        T response = null;
        long start = System.currentTimeMillis();
        int status = Status.OK;
        try {
            httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(uri);
            httpResponse = httpclient.execute(httpget);
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                ObjectMapper mapper = new ObjectMapper();
                response = mapper.readValue(entity.getContent(), responseType);
                return response;
            } else {
                throw new IllegalStateException("wx response is empty");
            }
        } catch (Exception e) {
            status = Status.INVOKE_ERROR;
            LOG.error("call wx failed, uri: " + uri, e);
            return null;
        } finally {
            try {
                if (httpclient != null) {
                    httpclient.close();
                }
                if (httpResponse != null) {
                    httpResponse.close();
                }
            } catch (IOException e) {
            }
            InvokeLogger.log("weixin", tag, uri, response != null ? response : httpResponse, start,
                    System.currentTimeMillis() - start, status);
        }
    }

}
