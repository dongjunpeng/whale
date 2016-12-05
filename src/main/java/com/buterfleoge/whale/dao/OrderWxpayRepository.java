package com.buterfleoge.whale.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.type.entity.OrderWxpay;

/**
 * @author xiezhenzong
 *
 */
public interface OrderWxpayRepository extends CrudRepository<OrderWxpay, Long> {

    List<OrderWxpay> findByOrderid(Long orderid);

}
