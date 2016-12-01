package com.buterfleoge.whale.type.protocol.account;

import java.util.List;

import com.buterfleoge.whale.type.entity.Coupon;
import com.buterfleoge.whale.type.protocol.Response;

/**
 * @author xiezhenzong
 *
 */
public class GetCouponsResponse extends Response {

    private List<Coupon> coupons;
    private Integer count;

    /**
     * @return the coupons
     */
    public List<Coupon> getCoupons() {
        return coupons;
    }

    /**
     * @param coupons
     *            the coupons to set
     */
    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    /**
     * @return the count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * @param count
     *            the count to set
     */
    public void setCount(Integer count) {
        this.count = count;
    }
}
