package com.buterfleoge.whale.dao;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.type.RefoundStatus;
import com.buterfleoge.whale.type.entity.OrderRefound;

/**
 * @author Brent24
 *
 */
public interface OrderRefoundRepository extends CrudRepository<OrderRefound, Long> {

    OrderRefound findByOrderidAndStatusIn(Long orderid, Set<RefoundStatus> status);
}
