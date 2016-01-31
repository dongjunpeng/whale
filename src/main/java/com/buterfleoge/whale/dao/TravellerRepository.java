package com.buterfleoge.whale.dao;

import java.util.Iterator;

import org.springframework.data.repository.CrudRepository;
import com.buterfleoge.whale.type.entity.TravellerInfo;

/**
 * @author dongjunpeng
 *
 */
public interface TravellerRepository extends CrudRepository<TravellerInfo, Long> {
	Iterable<TravellerInfo> findByAccountid(long accountid);
	
	TravellerInfo findByTravellerid(long travellerid);

}
