package com.buterfleoge.whale.type.entity.converter;

import java.util.List;

import javax.persistence.AttributeConverter;

import com.buterfleoge.whale.Constants.DefaultValue;
import com.buterfleoge.whale.Utils;

/**
 * @author xiezhenzong
 *
 */
public class ListConverter implements AttributeConverter<List<String>, String> {

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        return Utils.join(attribute, DefaultValue.DB_SEPARATOR);
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        return Utils.split(dbData, DefaultValue.DB_SEPARATOR);
    }

}
