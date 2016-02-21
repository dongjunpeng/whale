package com.buterfleoge.whale.type;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 住宿状态
 * 
 * @author Brent24
 *
 */
public enum AccommodationStatus {

    /**
     * 预定
     */
    RESERVED(0),

    /**
     * 付定金
     */
    PAID(1),

    /**
     * 结束
     */
    FINISH(2),

    ;

    private int status;

    private AccommodationStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
