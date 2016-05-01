package com.buterfleoge.whale.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.type.entity.AccountContacts;

/**
 *
 * @author xiezhenzong
 *
 */
public interface AccountContactsRepository extends CrudRepository<AccountContacts, Long> {

    AccountContacts findByContactidAndValidTrue(long contactid);

    AccountContacts findByAccountidAndValidTrueAndIsDefaultTrue(long contactid);

    List<AccountContacts> findByAccountidAndValidTrue(long accountid);

    List<AccountContacts> findByContactidInAndAccountidAndValidTrue(Set<Long> coutactid, long accountid);

}
