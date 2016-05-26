package com.buterfleoge.whale.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.type.entity.AccountContacts;

/**
 *
 * @author xiezhenzong
 *
 */
public interface AccountContactsRepository extends CrudRepository<AccountContacts, Long> {

    AccountContacts findByContactidAndValidTrue(Long contactid);

    List<AccountContacts> findByAccountidAndValidTrueAndIsDefaultFalse(Long accountid);

    List<AccountContacts> findByAccountidAndValidTrue(Long accountid);

}
