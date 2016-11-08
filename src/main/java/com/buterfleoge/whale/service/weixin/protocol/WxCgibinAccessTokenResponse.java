package com.buterfleoge.whale.service.weixin.protocol;

/**
 * @author xiezhenzong
 *
 */
public class WxCgibinAccessTokenResponse extends WxResponse {

    /**
     * serial version uid
     */
    private static final long serialVersionUID = 280355255055754024L;

    private String access_token;

    private int expires_in;

    /**
     * 记录accessToken获取到的时间
     */
    private long add_time = System.currentTimeMillis();

    /**
     * 是否过期
     *
     * @return
     */
    public boolean isExpires() {
        return System.currentTimeMillis() - add_time >= expires_in;
    }

    /**
     * @return the access_token
     */
    public String getAccess_token() {
        return access_token;
    }

    /**
     * @param access_token
     *            the access_token to set
     */
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    /**
     * @return the expires_in
     */
    public int getExpires_in() {
        return expires_in;
    }

    /**
     * @param expires_in
     *            the expires_in to set
     */
    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    /**
     * @return the add_time
     */
    public long getAdd_time() {
        return add_time;
    }

    /**
     * @param add_time
     *            the add_time to set
     */
    public void setAdd_time(long add_time) {
        this.add_time = add_time;
    }

}
