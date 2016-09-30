/**
 *
 */
package com.buterfleoge.whale.dao;

import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.type.entity.WxIdMapping;

/**
 * @author Brent24
 *
 */
public interface WxIdMappingRepository extends CrudRepository<WxIdMapping, Long> {

    WxIdMapping findByOpenid(String openid);

}
