package com.buterfleoge.whale.dao;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.type.DiscountType;
import com.buterfleoge.whale.type.entity.Discount;

/**
 * @author Brent24
 *
 */
public interface DiscountRepository extends CrudRepository<Discount, Long> {

    Discount findByTypeAndStartTimeLessThanAndEndTimeGreaterThan(DiscountType type, Date now1, Date now2);

    Discount findByTypeAndRouteidAndStartTimeLessThanAndEndTimeGreaterThan(DiscountType type, Long routeid, Date now1, Date now2);

    Discount findByDiscountid(Long discountid);

}
