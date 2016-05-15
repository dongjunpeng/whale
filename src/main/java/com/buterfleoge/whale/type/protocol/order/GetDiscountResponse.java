package com.buterfleoge.whale.type.protocol.order;

import java.util.List;

import com.buterfleoge.whale.type.protocol.Response;
import com.buterfleoge.whale.type.protocol.order.object.DiscountObject;

/**
 * @author Brent24
 *
 */
public class GetDiscountResponse extends Response {

    private List<DiscountObject> policy;

    private DiscountObject studentDiscount;

    private Long defaultDiscountid;

    public List<DiscountObject> getPolicy() {
        return policy;
    }

    public void setPolicy(List<DiscountObject> policy) {
        this.policy = policy;
    }

    public DiscountObject getStudentDiscount() {
        return studentDiscount;
    }

    public void setStudentDiscount(DiscountObject studentDiscount) {
        this.studentDiscount = studentDiscount;
    }

    public Long getDefaultDiscountid() {
        return defaultDiscountid;
    }

    public void setDefaultDiscountid(Long defaultDiscountid) {
        this.defaultDiscountid = defaultDiscountid;
    }

}
