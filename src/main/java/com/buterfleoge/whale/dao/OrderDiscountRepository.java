package com.buterfleoge.whale.dao;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.type.DiscountType;
import com.buterfleoge.whale.type.entity.OrderDiscount;

/**
 * @author Brent24
 *
 */
public interface OrderDiscountRepository extends CrudRepository<OrderDiscount, Long> {

    OrderDiscount findByOrderidAndTypeIn(Long orderid, Set<DiscountType> type);

    OrderDiscount findByOrderidAndType(Long orderid, DiscountType type);
}
