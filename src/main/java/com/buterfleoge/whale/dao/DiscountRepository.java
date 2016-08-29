package com.buterfleoge.whale.dao;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.type.entity.Discount;

/**
 * @author Brent24
 *
 */
public interface DiscountRepository extends CrudRepository<Discount, Long> {

    Discount findByTypeAndStartTimeLessThanAndEndTimeGreaterThan(Integer type, Date now1, Date now2);

    Discount findByTypeAndRouteidAndStartTimeLessThanAndEndTimeGreaterThan(Integer type, Long routeid, Date now1,
            Date now2);

    Discount findByTypeAndRouteid(Integer type, Long routeid);

}
