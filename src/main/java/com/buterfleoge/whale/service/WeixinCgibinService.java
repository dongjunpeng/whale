package com.buterfleoge.whale.service;

import com.buterfleoge.whale.service.weixin.protocol.WxCgibinAccessTokenResponse;
import com.buterfleoge.whale.service.weixin.protocol.WxGetTicketResponse;

/**
 * @author xiezhenzong
 *
 */
public interface WeixinCgibinService {

    WxCgibinAccessTokenResponse getCgibinAccessToken();

    WxGetTicketResponse getTicket(String accessToken, String type);

}
