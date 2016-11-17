package com.buterfleoge.whale.type.entity.converter;

import java.util.Objects;

import javax.persistence.AttributeConverter;

import com.buterfleoge.whale.type.entity.converter.PointsConverter.Point;

/**
 * @author xiezhenzong
 *
 */
public class PointConverter implements AttributeConverter<Point, String> {

    @Override
    public String convertToDatabaseColumn(Point attribute) {
        return Objects.toString(attribute, "");
    }

    @Override
    public Point convertToEntityAttribute(String dbData) {
        String[] items = dbData.split(Point.SEPARATOR);
        return new Point(items[0], items[1]);
    }
}
