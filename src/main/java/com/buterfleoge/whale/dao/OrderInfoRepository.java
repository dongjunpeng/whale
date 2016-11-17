package com.buterfleoge.whale.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.type.entity.OrderInfo;

/**
 * @author Brent24
 *
 */
public interface OrderInfoRepository extends CrudRepository<OrderInfo, Long> {

    OrderInfo findByAccountidAndRouteidAndGroupidAndStatusIn(Long accountid, Long routeid, Long groupid,
            Set<Integer> status);

    OrderInfo findByOrderidAndAccountid(Long orderid, Long accountid);

    Integer countByOrderidAndAccountid(Long orderid, Long accountid);

    Integer countByAccountidAndStatusIn(Long accountid, Set<Integer> status);

    List<OrderInfo> findByAccountidAndStatusIn(Long accountid, Set<Integer> status);

    List<OrderInfo> findByRouteidAndGroupidAndStatusIn(Long routeid, Long groupid, Set<Integer> status);

    List<OrderInfo> findByStatusInAndAddTimeLessThan(Set<Integer> status, Date currentMinusTwoHour);

}
