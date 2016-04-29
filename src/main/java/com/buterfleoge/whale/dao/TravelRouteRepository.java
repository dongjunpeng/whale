/**
 * 
 */
package com.buterfleoge.whale.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.type.entity.TravelRoute;

/**
 * @author Brent24
 *
 */
public interface TravelRouteRepository extends CrudRepository<TravelRoute, Long> {

	List<TravelRoute> findByRouteidAndVisibleTrue(Long routeid);
	
	List<TravelRoute> findByNameAndVisibleTrue(String name);

	List<TravelRoute> findByVisibleTrue();
	
	TravelRoute findByName(String name);

    List<TravelRoute> findByRouteidInAndVisibleTrue(Set<Long> routeid);


}
