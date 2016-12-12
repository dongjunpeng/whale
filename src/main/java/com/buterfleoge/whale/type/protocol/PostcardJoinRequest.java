package com.buterfleoge.whale.type.protocol;

/**
 * @author xiezhenzong
 *
 */
public class PostcardJoinRequest extends Request {

    private Long activityid;
    private String address;

    /**
     * @return the activityid
     */
    public Long getActivityid() {
        return activityid;
    }

    /**
     * @param activityid
     *            the activityid to set
     */
    public void setActivityid(Long activityid) {
        this.activityid = activityid;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     *            the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

}
