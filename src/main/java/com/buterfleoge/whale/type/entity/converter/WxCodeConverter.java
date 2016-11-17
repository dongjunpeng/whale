package com.buterfleoge.whale.type.entity.converter;

import javax.persistence.AttributeConverter;

import com.buterfleoge.whale.type.WxCode;

/**
 * @author xiezhenzong
 *
 */
public class WxCodeConverter implements AttributeConverter<WxCode, Integer> {

    @Override
    public Integer convertToDatabaseColumn(WxCode attribute) {
        return attribute == null ? null : attribute.getValue();
    }

    @Override
    public WxCode convertToEntityAttribute(Integer dbData) {
        return dbData == null ? null : WxCode.valueOf(dbData);
    }

}
