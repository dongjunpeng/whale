/**
 * 
 */
package com.buterfleoge.whale.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.type.entity.TravelGroup;
import com.buterfleoge.whale.type.enums.GroupStatus;

/**
 * @author Brent24
 *
 */
public interface TravelGroupRepository extends CrudRepository<TravelGroup, Long> {

    List<TravelGroup> findByRouteidAndEndDateGreaterThanOrderByStartDateAsc(Long routeid, Date endDate);

    List<TravelGroup> findByStatusIn(Set<GroupStatus> status);

    List<TravelGroup> findByStatus(GroupStatus status);

}
