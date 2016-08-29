package com.buterfleoge.whale.dao;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.type.entity.OrderDiscount;

/**
 * @author Brent24
 *
 */
public interface OrderDiscountRepository extends CrudRepository<OrderDiscount, Long> {

    OrderDiscount findByOrderidAndTypeIn(Long orderid, Set<Integer> type);

    OrderDiscount findByOrderidAndType(Long orderid, Integer type);
}
