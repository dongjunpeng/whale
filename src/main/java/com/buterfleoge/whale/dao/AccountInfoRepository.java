package com.buterfleoge.whale.dao;

import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.type.entity.AccountInfo;

/**
 * repository for account_info table
 *
 * @author xiezhenzong
 *
 */
public interface AccountInfoRepository extends CrudRepository<AccountInfo, Long> {

    long countByEmail(String email);

    AccountInfo findByEmail(String email);

}
