package com.buterfleoge.whale.dao;

import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.type.entity.AccountSetting;


public interface InformationRepository extends CrudRepository<AccountSetting, Long> {
	AccountSetting findByAccountid(Long accountid);
}
