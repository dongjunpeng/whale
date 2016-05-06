package com.buterfleoge.whale.type.enums;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 发团状态
 * 
 * @author Brent24
 *
 */
public enum GroupStatus {

    /**
     * 未发布
     */
    UNPUBLISHED(0),

    /**
     * 招募中
     */
    OPEN(1),

    /**
     * 未成行关闭
     */
    CLOSE(2),

    /**
     * 满员
     */
    FULL(3),

    /**
     * 出团中
     */
    TRAVELLING(4),

    /**
     * 结束
     */
    FINISHED(5)

    ;

    private int status;

    private GroupStatus(int status) {
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
