package com.buterfleoge.whale.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.type.entity.Coupon;

/**
 * @author Brent24
 *
 */
public interface CouponRepository extends CrudRepository<Coupon, Long> {

    List<Coupon> findByStatus(Integer status);

    List<Coupon> findByAccountidAndVerify(Long accountid, Boolean verify);

    List<Coupon> findByAccountidAndTypeAndVerify(Long accountid, Integer type, Boolean verify);

    List<Coupon> findByAccountidAndTypeAndStatusAndVerify(Long accountid, Integer type, Integer status, Boolean verify);

    Coupon findByCouponidAndAccountid(Long couponid, Long accountid);

    Coupon findByDiscountCode(String code);

    Coupon findByAccountidAndDiscountCode(Long accountid, String code);

}
