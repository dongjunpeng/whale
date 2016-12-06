package com.buterfleoge.whale.type.protocol.order;

import java.util.ArrayList;
import java.util.List;

import com.buterfleoge.whale.type.entity.Discount;
import com.buterfleoge.whale.type.protocol.Response;

/**
 * @author Brent24
 *
 */
public class GetDiscountResponse extends Response {

    private List<Discount> policy = new ArrayList<Discount>(5);;

    private Discount studentDiscount;

    /**
     * @return the policy
     */
    public List<Discount> getPolicy() {
        return policy;
    }

    /**
     * @param policy the policy to set
     */
    public void setPolicy(List<Discount> policy) {
        this.policy = policy;
    }

    /**
     * @return the studentDiscount
     */
    public Discount getStudentDiscount() {
        return studentDiscount;
    }

    /**
     * @param studentDiscount the studentDiscount to set
     */
    public void setStudentDiscount(Discount studentDiscount) {
        this.studentDiscount = studentDiscount;
    }

}
