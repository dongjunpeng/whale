package com.buterfleoge.whale.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.type.entity.OrderHistory;

/**
 * @author xiezhenzong
 *
 */
public interface OrderHistoryRepository extends CrudRepository<OrderHistory, Long> {

    List<OrderHistory> findByOrderid(Long orderid);

}
