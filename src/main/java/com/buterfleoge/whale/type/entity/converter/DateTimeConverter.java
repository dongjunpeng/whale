package com.buterfleoge.whale.type.entity.converter;

import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author xiezhenzong
 *
 */
@Converter(autoApply = true)
public class DateTimeConverter implements AttributeConverter<Date, Long> {

    @Override
    public Long convertToDatabaseColumn(Date attribute) {
        return attribute != null ? attribute.getTime() : null;
    }

    @Override
    public Date convertToEntityAttribute(Long dbData) {
        return dbData != null ? new Date(dbData) : null;
    }

}
