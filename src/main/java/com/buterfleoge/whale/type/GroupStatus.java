package com.buterfleoge.whale.type;

import com.buterfleoge.whale.EnumObject;

/**
 * 发团状态
 *
 * @author Brent24
 *
 */
public class GroupStatus extends EnumObject {

    /**
     * 未发布
     */
    public static final GroupStatus UNPUBLISHED = new GroupStatus(0);

    /**
     * 招募中
     */
    public static final GroupStatus OPEN = new GroupStatus(1);

    /**
     * 未成行关闭
     */
    public static final GroupStatus CLOSE = new GroupStatus(2);

    /**
     * 满员
     */
    public static final GroupStatus FULL = new GroupStatus(3);

    /**
     * 出团中
     */
    public static final GroupStatus TRAVELLING = new GroupStatus(4);

    /**
     * 结束
     */
    public static final GroupStatus FINISHED = new GroupStatus(5);

    public static final EnumObjectHelper<GroupStatus> HELPER = EnumObjectHelper.create(UNPUBLISHED, OPEN, CLOSE, FULL,
            TRAVELLING, FINISHED);

    private GroupStatus(int value) {
        super(value);
    }

}
