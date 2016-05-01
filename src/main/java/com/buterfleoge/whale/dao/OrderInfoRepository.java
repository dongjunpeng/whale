package com.buterfleoge.whale.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.type.entity.OrderInfo;
import com.buterfleoge.whale.type.enums.OrderStatus;

/**
 * @author Brent24
 *
 */
public interface OrderInfoRepository extends CrudRepository<OrderInfo, Long> {

    List<OrderInfoRepository> findByAccountidAndStatus(Long accountid, OrderStatus status);

}
