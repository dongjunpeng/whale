package com.buterfleoge.whale.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.type.entity.Coupon;

/**
 * @author Brent24
 *
 */
public interface CouponRepository extends CrudRepository<Coupon, Long> {

    Coupon findByDiscountCode(String code);

    Coupon findByAccountidAndDiscountCode(Long accountid, String code);

    List<Coupon> findByAccountid(Long accountid);

    List<Coupon> findByStatusIn(Set<Integer> status);

}
