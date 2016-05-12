package com.buterfleoge.whale.dao;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.type.entity.OrderRefound;
import com.buterfleoge.whale.type.enums.RefoundStatus;

/**
 * @author Brent24
 *
 */
public interface OrderRefoundRepository extends CrudRepository<OrderRefound, Long> {

    OrderRefound findByOrderidAndStatusIn(Long orderid, Set<RefoundStatus> status);
}
