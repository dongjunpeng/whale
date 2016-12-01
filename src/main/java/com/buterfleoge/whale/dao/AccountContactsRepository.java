package com.buterfleoge.whale.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.type.entity.AccountContact;

/**
 *
 * @author xiezhenzong
 *
 */
public interface AccountContactsRepository extends CrudRepository<AccountContact, Long> {

    AccountContact findByContactidAndAccountidAndValidTrue(Long contactid, Long accountid);

    List<AccountContact> findByAccountidAndValidTrue(Long accountid);

}
