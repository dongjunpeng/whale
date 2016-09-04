package com.buterfleoge.whale.type.protocol.account;

import java.util.List;

import com.buterfleoge.whale.type.entity.DiscountCode;
import com.buterfleoge.whale.type.protocol.Response;

/**
 * @author xiezhenzong
 *
 */
public class GetDiscountCodeResponse extends Response {

    private List<DiscountCode> discountCodes;

    /**
     * @return the discountCodes
     */
    public List<DiscountCode> getDiscountCodes() {
        return discountCodes;
    }

    /**
     * @param discountCodes
     *            the discountCodes to set
     */
    public void setDiscountCodes(List<DiscountCode> discountCodes) {
        this.discountCodes = discountCodes;
    }

}
