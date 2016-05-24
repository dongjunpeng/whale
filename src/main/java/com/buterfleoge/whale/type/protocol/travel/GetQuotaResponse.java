package com.buterfleoge.whale.type.protocol.travel;

import com.buterfleoge.whale.type.protocol.Response;

/**
 * @author Brent24
 *
 */
public class GetQuotaResponse extends Response {

    public static final int NO_QUOTA = 0;

    /**
     * 默认0, 表示没有配额
     */
    private int quota = NO_QUOTA;

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

}
