package com.buterfleoge.whale.type;

import com.buterfleoge.whale.Utils;

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

    public static final GroupStatus valueOf(int status) {
        for (GroupStatus as : values()) {
            if (as.getStatus() == status) {
                return as;
            }
        }
        throw new IllegalArgumentException("Can't find GroupStatus, status: " + status);
    }

    @Override
    public String toString() {
        return Utils.enumToString(this);
    }

}
