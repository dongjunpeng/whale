package com.buterfleoge.whale.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.type.entity.OrderTraveller;

/**
 * @author Brent24
 *
 */
public interface OrderTravellersRepository extends CrudRepository<OrderTraveller, Long> {

    List<OrderTraveller> findByOrderid(Long orderid);

    List<OrderTraveller> findByOrderidAndAccountid(Long orderid, Long accountid);

}
