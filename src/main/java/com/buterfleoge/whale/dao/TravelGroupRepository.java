/**
 * 
 */
package com.buterfleoge.whale.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.type.entity.TravelGroup;

/**
 * @author Brent24
 *
 */
public interface TravelGroupRepository extends CrudRepository<TravelGroup, Long> {

    List<TravelGroup> findByRouteidAndEndDateGreaterThan(Long routeid, Long endDate);

    TravelGroup findByGroupid(Long groupid);

}
