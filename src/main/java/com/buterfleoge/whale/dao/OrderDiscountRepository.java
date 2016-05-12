package com.buterfleoge.whale.dao;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.type.entity.OrderDiscount;
import com.buterfleoge.whale.type.enums.DiscountType;

/**
 * @author Brent24
 *
 */
public interface OrderDiscountRepository extends CrudRepository<OrderDiscount, Long> {

    OrderDiscount findByOrderidAndTypeIn(Long orderid, Set<DiscountType> type);

    OrderDiscount findByOrderidAndType(Long orderid, DiscountType type);
}
