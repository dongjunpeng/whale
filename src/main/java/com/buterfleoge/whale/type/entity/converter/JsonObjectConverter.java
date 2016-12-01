package com.buterfleoge.whale.type.entity.converter;

import javax.persistence.AttributeConverter;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author xiezhenzong
 *
 */
public class JsonObjectConverter implements AttributeConverter<Object, String> {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Object attribute) {
        try {
            return mapper.writeValueAsString(attribute);
        } catch (Exception e) {
            throw new RuntimeException("Unable write value to json string, value: " + attribute);
        }
    }

    @Override
    public Object convertToEntityAttribute(String dbData) {
        try {
            return mapper.readTree(dbData);
        } catch (Exception e) {
            throw new RuntimeException("Unable read value from db to json object, dbData: " + dbData);
        }
    }

}
