package com.buterfleoge.whale.type.entity.converter;

import javax.persistence.AttributeConverter;

import com.buterfleoge.whale.type.AlipayRefundStatus;

/**
 * @author xiezhenzong
 *
 */
public class AlipayRefundStatusConverter implements AttributeConverter<AlipayRefundStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(AlipayRefundStatus attribute) {
        return attribute == null ? null : attribute.getValue();
    }

    @Override
    public AlipayRefundStatus convertToEntityAttribute(Integer dbData) {
        return dbData == null ? null : AlipayRefundStatus.value(dbData);
    }

}
