package com.buterfleoge.whale.dao;

import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.eo.Information;

public interface InformationRepository extends CrudRepository<Information, Long> {
	Information findByUserid(Long userid);
}
