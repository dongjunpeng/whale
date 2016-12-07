package com.buterfleoge.whale.dao;

import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.type.entity.Message;

/**
 * @author xiezhenzong
 *
 */
public interface MessageRepository extends CrudRepository<Message, Long> {

}
