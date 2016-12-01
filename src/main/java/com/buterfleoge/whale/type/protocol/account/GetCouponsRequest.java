package com.buterfleoge.whale.type.protocol.account;

import com.buterfleoge.whale.type.protocol.Request;

/**
 * @author xiezhenzong
 *
 */
public class GetCouponsRequest extends Request {

    private Integer type;
    private boolean usable;
    private boolean onlyCount;

    /**
     * @return the type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return the usable
     */
    public boolean isUsable() {
        return usable;
    }

    /**
     * @param usable
     *            the usable to set
     */
    public void setUsable(boolean usable) {
        this.usable = usable;
    }

    /**
     * @return the onlyCount
     */
    public boolean isOnlyCount() {
        return onlyCount;
    }

    /**
     * @param onlyCount
     *            the onlyCount to set
     */
    public void setOnlyCount(boolean onlyCount) {
        this.onlyCount = onlyCount;
    }
}
