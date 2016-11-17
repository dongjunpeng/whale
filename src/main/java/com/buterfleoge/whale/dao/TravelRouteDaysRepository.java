/**
 *
 */
package com.buterfleoge.whale.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.type.entity.TravelRouteDays;

/**
 * @author xiezhenzong
 *
 */
public interface TravelRouteDaysRepository extends CrudRepository<TravelRouteDays, Long> {

    List<TravelRouteDays> findByRouteid(Long routeid);
}
