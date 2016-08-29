package com.buterfleoge.whale.type.entity.converter;

import javax.persistence.AttributeConverter;

import com.buterfleoge.whale.type.AlipayTradeStatus;

/**
 * @author xiezhenzong
 *
 */
public class AlipayTradeStatusConverter implements AttributeConverter<AlipayTradeStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(AlipayTradeStatus attribute) {
        return attribute.getValue();
    }

    @Override
    public AlipayTradeStatus convertToEntityAttribute(Integer dbData) {
        return AlipayTradeStatus.value(dbData);
    }

}
