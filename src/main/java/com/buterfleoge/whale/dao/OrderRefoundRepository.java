package com.buterfleoge.whale.dao;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.type.entity.OrderRefund;

/**
 * @author Brent24
 *
 */
public interface OrderRefoundRepository extends CrudRepository<OrderRefund, Long> {

    OrderRefund findByOrderidAndStatusIn(Long orderid, Set<Integer> status);
}
