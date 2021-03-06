package com.buterfleoge.whale.service.weixin.protocol;

/**
 * @author xiezhenzong
 *
 */
public class WxGetTicketResponse extends WxResponse {

    /**
     * serial version uid
     */
    private static final long serialVersionUID = 5224286842355767647L;

    private String ticket;

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
        return System.currentTimeMillis() - add_time >= expires_in * 1000;
    }

    /**
     * @return the ticket
     */
    public String getTicket() {
        return ticket;
    }

    /**
     * @param ticket
     *            the ticket to set
     */
    public void setTicket(String ticket) {
        this.ticket = ticket;
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
