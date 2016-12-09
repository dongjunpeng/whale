package com.buterfleoge.whale.dao;

import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.type.entity.Postcard;

/**
 *
 * @author xiezhenzong
 *
 */
public interface PostcardRepository extends CrudRepository<Postcard, Long> {

    Long countByActivityidAndAccountid(Long activityid, Long accountid);

}
