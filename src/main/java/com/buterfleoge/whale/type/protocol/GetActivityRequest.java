package com.buterfleoge.whale.type.protocol;

/**
 * @author xiezhenzong
 *
 */
public class GetActivityRequest extends Request {

    private Long activityid;

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

}
