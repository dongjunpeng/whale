package com.buterfleoge.whale.service.weixin.protocol;

import com.buterfleoge.whale.Utils;

/**
 * @author xiezhenzong
 *
 */
public enum WxLoginScope {

    SNSAPI_LOGIN("snsapi_login"),

    SNSAPI_BASE("snsapi_base"),

    SNSAPI_USERINFO("snsapi_userinfo");

    public String value;

    private WxLoginScope(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Utils.enumToString(this);
    }

}
