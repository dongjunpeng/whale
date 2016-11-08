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

    /**
     * wx_id_mapping 这个表现在只存着公众平台的openid映射
     *
     * @param accountid
     *            accountid
     * @return
     */
    WxIdMapping findByAccountid(Long accountid);

}
