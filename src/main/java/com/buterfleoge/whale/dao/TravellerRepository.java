package com.buterfleoge.whale.dao;

import java.util.Iterator;

import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.type.entity.Traveller;

/**
 * @author dongjunpeng
 *
 */
public interface TravellerRepository extends CrudRepository<Traveller, Long> {
	Iterable<Traveller> findByUserid(long userid);
	
	Traveller findByTravellerId(long travellerId);

}
