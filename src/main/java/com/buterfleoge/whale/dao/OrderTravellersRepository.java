package com.buterfleoge.whale.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.type.entity.OrderTravellers;

/**
 * @author Brent24
 *
 */
public interface OrderTravellersRepository extends CrudRepository<OrderTravellers, Long> {

    List<OrderTravellers> findByOrderid(Long orderid);

    List<OrderTravellers> findByOrderidAndAccountid(Long orderid, Long accountid);

}
