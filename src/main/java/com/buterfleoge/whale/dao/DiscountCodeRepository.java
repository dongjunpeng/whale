package com.buterfleoge.whale.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.type.entity.DiscountCode;
import com.buterfleoge.whale.type.enums.DiscountCodeStatus;

/**
 * @author Brent24
 *
 */
public interface DiscountCodeRepository extends CrudRepository<DiscountCode, Long> {

    DiscountCode findByDiscountCode(Long code);

    List<DiscountCode> findByStatusIn(Set<DiscountCodeStatus> status);

}
