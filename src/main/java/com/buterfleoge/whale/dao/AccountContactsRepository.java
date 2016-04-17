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

    AccountContacts findByContactidAndAccountid(long contactid, long accountid);

    List<AccountContacts> findByAccountid(long accountid);

    List<AccountContacts> findByContactidInAndAccountid(Set<Long> coutactid, long accountid);

}
