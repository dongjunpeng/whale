package com.buterfleoge.whale.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.type.DiscountCodeStatus;
import com.buterfleoge.whale.type.entity.DiscountCode;

/**
 * @author Brent24
 *
 */
public interface DiscountCodeRepository extends CrudRepository<DiscountCode, Long> {

    DiscountCode findByDiscountCode(String code);

    DiscountCode findByAccountidAndDiscountCode(Long accountid, String code);

    List<DiscountCode> findByStatusIn(Set<DiscountCodeStatus> status);

}
