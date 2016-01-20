package com.buterfleoge.whale.dao;

import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.type.entity.AccountSetting;


public interface InformationRepository extends CrudRepository<AccountSetting, Long> {
	AccountSetting findByUserid(Long userid);
}
