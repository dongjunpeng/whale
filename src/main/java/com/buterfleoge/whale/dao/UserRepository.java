package com.buterfleoge.whale.dao;

import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.eo.User;

/**
 * 
 * repository bean for user
 * 
 * @author dongjunpeng
 *
 */
public interface UserRepository extends CrudRepository<User, Long> {

	User findOne(Long userid);

	long countByEmail(String email);

}
