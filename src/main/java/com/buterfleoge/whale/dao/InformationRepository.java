package com.buterfleoge.whale.dao;

import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.type.entity.Information;

public interface InformationRepository extends CrudRepository<Information, Long> {
	Information findByUserId(Long userId);
}
