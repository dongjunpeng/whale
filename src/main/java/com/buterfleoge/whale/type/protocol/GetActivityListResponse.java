package com.buterfleoge.whale.type.protocol;

import java.util.List;

import com.buterfleoge.whale.type.entity.Activity;

/**
 * @author xiezhenzong
 *
 */
public class GetActivityListResponse extends Response {

    private List<Activity> activities;

    /**
     * @return the activities
     */
    public List<Activity> getActivities() {
        return activities;
    }

    /**
     * @param activities
     *            the activities to set
     */
    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

}
