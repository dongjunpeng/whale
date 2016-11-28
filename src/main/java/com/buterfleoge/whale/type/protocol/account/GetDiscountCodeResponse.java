package com.buterfleoge.whale.type.protocol.account;

import java.util.List;

import com.buterfleoge.whale.type.entity.Coupon;
import com.buterfleoge.whale.type.protocol.Response;

/**
 * @author xiezhenzong
 *
 */
public class GetDiscountCodeResponse extends Response {

    private List<Coupon> discountCodes;

    /**
     * @return the discountCodes
     */
    public List<Coupon> getDiscountCodes() {
        return discountCodes;
    }

    /**
     * @param discountCodes
     *            the discountCodes to set
     */
    public void setDiscountCodes(List<Coupon> discountCodes) {
        this.discountCodes = discountCodes;
    }

}
