package com.buterfleoge.whale.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.type.entity.Activity;

/**
 *
 * @author xiezhenzong
 *
 */
public interface ActivityRepository extends CrudRepository<Activity, Long> {

    @Query("SELECT a FROM Activity a WHERE a.startTime <= ?1 AND a.endTime >= ?2")
    List<Activity> findOpenActivity(Date startTime, Date endTime);

}
