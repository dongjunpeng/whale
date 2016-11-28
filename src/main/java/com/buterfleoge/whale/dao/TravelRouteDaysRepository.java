/**
 *
 */
package com.buterfleoge.whale.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.type.entity.TravelRouteDay;

/**
 * @author xiezhenzong
 *
 */
public interface TravelRouteDaysRepository extends CrudRepository<TravelRouteDay, Long> {

    List<TravelRouteDay> findByRouteid(Long routeid);
}
