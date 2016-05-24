package com.buterfleoge.whale.type.entity.converter;

import java.util.Date;

import javax.persistence.AttributeConverter;

/**
 *
 * @author xiezhenzong
 *
 */
public class DateTimeConverter implements AttributeConverter<Date, Long> {

    @Override
    public Long convertToDatabaseColumn(Date attribute) {
        return attribute.getTime();
    }

    @Override
    public Date convertToEntityAttribute(Long dbData) {
        return new Date(dbData);
    }

}
