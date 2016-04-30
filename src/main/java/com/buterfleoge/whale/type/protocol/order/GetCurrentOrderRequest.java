package com.buterfleoge.whale.type.protocol.order;

import com.buterfleoge.whale.type.protocol.Request;

/**
 * @author Brent24
 *
 */
public class GetCurrentOrderRequest extends Request {

    private Long accoutid;

    public Long getAccoutid() {
        return accoutid;
    }

    public void setAccoutid(Long accoutid) {
        this.accoutid = accoutid;
    }

}
