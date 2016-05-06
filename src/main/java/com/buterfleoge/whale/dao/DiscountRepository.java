package com.buterfleoge.whale.dao;

import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.type.entity.Discount;
import com.buterfleoge.whale.type.enums.DiscountType;

/**
 * @author Brent24
 *
 */
public interface DiscountRepository extends CrudRepository<Discount, Long> {

    Discount findByTypeAndStartTimeLessThanAndEndTimeGreaterThan(DiscountType type, Long now1, Long now2);

    Discount findByTypeAndRouteidAndStartTimeLessThanAndEndTimeGreaterThan(DiscountType type, Long routeid, Long now1,
            Long now2);

    Discount findByDiscountid(Long discountid);

}
