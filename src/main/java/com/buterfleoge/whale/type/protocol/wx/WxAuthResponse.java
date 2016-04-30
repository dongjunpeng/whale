package com.buterfleoge.whale.type.protocol.wx;

import com.buterfleoge.whale.BaseObject;

/**
 *
 * @author xiezhenzong
 *
 */
public class WxAuthResponse extends BaseObject {

    public static final Integer CODE_OK = Integer.valueOf(0);

    private Integer errcode;

    private String errmsg;

    /**
     * @return the errcode
     */
    public Integer getErrcode() {
        return errcode;
    }

    /**
     * @param errcode the errcode to set
     */
    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    /**
     * @return the errmsg
     */
    public String getErrmsg() {
        return errmsg;
    }

    /**
     * @param errmsg the errmsg to set
     */
    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

}
