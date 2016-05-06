package com.buterfleoge.whale.type.protocol.travel;

import com.buterfleoge.whale.type.protocol.Response;

/**
 * @author Brent24
 *
 */
public class GetQuotaResponse extends Response {
    private int quota;

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

}
