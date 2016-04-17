package com.buterfleoge.whale.type;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 订单中人员的状态
 *
 * @author xiezhenzong
 *
 */
public enum OrderStaffStatus {

    OK(0),

    /**
     * 删除了这个人
     */
    CANCEL(1)

    ;

    private int status;

    private OrderStaffStatus(int status) {
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
