package com.buterfleoge.whale.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.type.entity.OrderInfo;
import com.buterfleoge.whale.type.enums.OrderStatus;

/**
 * @author Brent24
 *
 */
public interface OrderInfoRepository extends CrudRepository<OrderInfo, Long> {

    OrderInfo findByOrderidAndAccountid(Long orderid, Long accountid);

    List<OrderInfo> findByAccountidAndStatusIn(Long accountid, Set<OrderStatus> status);

    Integer countByAccountidAndStatusIn(Long accountid, Set<OrderStatus> status);

    List<OrderInfo> findByStatusAndAddTimeLessThan(OrderStatus status, Long time);

}
