package com.buterfleoge.whale.service.weixin;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.buterfleoge.whale.Constants.CacheKey;
import com.buterfleoge.whale.service.WeixinCgibinService;
import com.buterfleoge.whale.service.WeixinWebService;
import com.buterfleoge.whale.service.weixin.protocol.WxAccessTokenResponse;
import com.buterfleoge.whale.service.weixin.protocol.WxCgibinAccessTokenResponse;
import com.buterfleoge.whale.service.weixin.protocol.WxGetTicketResponse;
import com.buterfleoge.whale.service.weixin.protocol.WxLoginScope;

/**
 * @author xiezhenzong
 *
 */
@Service("weixinCgibinService")
public class WeixinCgibinServiceImpl extends WeixinWebServiceImpl implements WeixinWebService, WeixinCgibinService {

    private static final Logger LOG = LoggerFactory.getLogger(WeixinCgibinServiceImpl.class);

    @Value("${wx.cgi-bin.appid}")
    private String appid;

    @Value("${wx.cgi-bin.appsecret}")
    private String appsecret;

    @Value("${wx.login.oauth}")
    private String wxOauth;

    @Value("${wx.cgi-bin.accessToken}")
    private String cgibinAccessTokenUrl;

    @Value("${wx.cgi-bin.getTicket}")
    private String getTicketUrl;

    @Autowired
    @Resource(name = "cacheTemplate")
    private ValueOperations<String, Object> operations;

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
        return queryWeixin("wxApiAccessToken", uri, WxAccessTokenResponse.class);
    }

    @Override
    public WxAccessTokenResponse refreshToken(String refreshToken) {
        String uri = new StringBuilder(wxApiRefreshToken) //
                .append("?appid=").append(appid) //
                .append("&grant_type=refresh_token") //
                .append("&refresh_token=").append(refreshToken).toString();
        return queryWeixin("wxApiRefreshToken", uri, WxAccessTokenResponse.class);
    }

    @Override
    public synchronized WxCgibinAccessTokenResponse getCgibinAccessToken() {
        WxCgibinAccessTokenResponse response = getCgibinAccessTokenFromCache();
        if (response == null || response.isExpires()) {
            String uri = new StringBuilder(cgibinAccessTokenUrl) //
                    .append("&appid=").append(appid) //
                    .append("&secret=").append(appsecret).toString();
            response = queryWeixin("cgibinAccessToken", uri, WxCgibinAccessTokenResponse.class);
            if (response.isOk()) {
                setCgibinAccessTokenToCache(response);
            } else {
                return null;
            }
        }
        return response;
    }

    @Override
    public synchronized WxGetTicketResponse getTicket(String accessToken, String type) {
        WxGetTicketResponse response = getCgibinJsapiTicketFromCache();
        if (response == null || response.isExpires()) {
            String uri = new StringBuilder(getTicketUrl) //
                    .append("?access_token=").append(accessToken) //
                    .append("&type=").append(type).toString();
            response = queryWeixin("getTicketUrl", uri, WxGetTicketResponse.class);
            if (response.isOk()) {
                setCgibinJsapiTicketToCache(response);
            } else {
                return null;
            }
        }
        return response;
    }

    private WxCgibinAccessTokenResponse getCgibinAccessTokenFromCache() {
        try {
            return (WxCgibinAccessTokenResponse) operations.get(CacheKey.CGIBIN_ACCESSTOKEN_KEY);
        } catch (Exception e) {
            LOG.error("get cgibinAccessToken from cache failed", e);
            return null;
        }
    }

    private void setCgibinAccessTokenToCache(WxCgibinAccessTokenResponse accessToken) {
        try {
            operations.set(CacheKey.CGIBIN_ACCESSTOKEN_KEY, accessToken);
        } catch (Exception e) {
            LOG.error("set cgibinAccessToken to cache failed", e);
        }
    }

    private WxGetTicketResponse getCgibinJsapiTicketFromCache() {
        try {
            return (WxGetTicketResponse) operations.get(CacheKey.CGIBIN_JSAPI_TICKET_KEY);
        } catch (Exception e) {
            LOG.error("get cgibinJsapiTicket from cache failed", e);
            return null;
        }
    }

    private void setCgibinJsapiTicketToCache(WxGetTicketResponse jsapiTicket) {
        try {
            operations.set(CacheKey.CGIBIN_JSAPI_TICKET_KEY, jsapiTicket);
        } catch (Exception e) {
            LOG.error("set cgibinJsapiTicket to cache failed", e);
        }
    }

}
