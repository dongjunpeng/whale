package com.buterfleoge.whale.type.entity.converter;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import javax.persistence.AttributeConverter;

import org.springframework.format.Parser;

import com.buterfleoge.whale.Constants.DefaultValue;
import com.buterfleoge.whale.Utils;
import com.buterfleoge.whale.type.entity.converter.PointsConverter.Point;

/**
 * @author xiezhenzong
 *
 */
public class PointsConverter implements AttributeConverter<List<Point>, String> {

    @Override
    public String convertToDatabaseColumn(List<Point> attribute) {
        return Utils.join(attribute, DefaultValue.DB_SEPARATOR);
    }

    @Override
    public List<Point> convertToEntityAttribute(String dbData) {
        return Utils.split(dbData, DefaultValue.DB_SEPARATOR, new Parser<Point>() {

            @Override
            public Point parse(String text, Locale locale) throws ParseException {
                String[] items = text.split(Point.SEPARATOR);
                return new Point(items[0], items[1]);
            }
        });
    }

    public static class Point {

        public static final String SEPARATOR = ",";

        public String longitude;

        public String latitude;

        public Point() {
        }

        public Point(String longitude, String latitude) {
            this.longitude = longitude;
            this.latitude = latitude;
        }

        @Override
        public String toString() {
            return longitude + SEPARATOR + latitude;
        }
    }
}
