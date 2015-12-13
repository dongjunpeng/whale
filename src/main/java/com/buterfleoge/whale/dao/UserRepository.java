package com.buterfleoge.whale.dao;

import org.springframework.data.repository.Repository;

import com.buterfleoge.whale.eo.User;

/**
 * 
 * repository bean for user
 * 
 * @author xiezhenzong
 *
 */
public interface UserRepository extends Repository<User, Long> {

    User findOne(Long userid);

}
