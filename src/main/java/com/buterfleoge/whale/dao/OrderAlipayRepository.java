package com.buterfleoge.whale.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.type.entity.OrderAlipay;

/**
 * @author xiezhenzong
 *
 */
public interface OrderAlipayRepository extends CrudRepository<OrderAlipay, Long> {

    List<OrderAlipay> findByOrderidAndTradeNo(Long orderid, String trade_no);

    List<OrderAlipay> findByOrderid(Long orderid);

}
