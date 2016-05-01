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

    // 查询用
    TravelRoute findByRouteidAndVisibleTrue(Long routeid);

    TravelRoute findByNameAndVisibleTrue(String name);

    List<TravelRoute> findByRouteidInAndVisibleTrue(Set<Long> routeid);

    List<TravelRoute> findByVisibleTrue();

    // 订单用
    TravelRoute findByRouteid(Long routeid);

    TravelRoute findByName(String name);

}
