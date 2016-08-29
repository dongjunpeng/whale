package com.buterfleoge.whale.dao;

import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.type.entity.AccountBinding;

/**
 *
 * @author xiezhenzong
 *
 */
public interface AccountBindingRepository extends CrudRepository<AccountBinding, Long> {

    AccountBinding findByWxid(String wxid);

}
