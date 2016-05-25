/**
 * 
 */
package com.buterfleoge.whale.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.type.entity.TravelRoute;

/**
 * @author Brent24
 *
 */
public interface TravelRouteRepository extends CrudRepository<TravelRoute, Long> {

    TravelRoute findByRouteidAndVisibleTrue(Long routeid);

    List<TravelRoute> findByRouteidInAndVisibleTrue(Collection<Long> routeids);

    List<TravelRoute> findByVisibleTrue();

    TravelRoute findByName(String name);

}
