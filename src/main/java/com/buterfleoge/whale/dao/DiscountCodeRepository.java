package com.buterfleoge.whale.dao;

import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.type.entity.DiscountCode;

/**
 * @author Brent24
 *
 */
public interface DiscountCodeRepository extends CrudRepository<DiscountCode, Long> {

    DiscountCode findByDiscountCode(Long code);
}
