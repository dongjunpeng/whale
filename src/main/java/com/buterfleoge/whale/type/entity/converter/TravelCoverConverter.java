package com.buterfleoge.whale.type.entity.converter;

import javax.persistence.AttributeConverter;

import com.buterfleoge.whale.Constants.DefaultValue;

/**
 * @author xiezhenzong
 *
 */
public class TravelCoverConverter implements AttributeConverter<String, String> {

    private static final String COVER_SEPARATOR = "<br/>";

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return attribute.replaceAll(COVER_SEPARATOR, DefaultValue.DB_SEPARATOR);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return dbData.replaceAll(DefaultValue.DB_SEPARATOR, COVER_SEPARATOR);
    }
}
