package com.buterfleoge.whale.type.entity.converter;

import java.text.ParseException;

import javax.persistence.AttributeConverter;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.buterfleoge.whale.Constants.Pattern;

/**
 *
 * @author xiezhenzong
 *
 */
public class BirthdayAttributeConverter implements AttributeConverter<String, Long> {

    private static final Logger LOG = LoggerFactory.getLogger(BirthdayAttributeConverter.class);
    private static final String[] PARSE_PATTERNS = new String[] { Pattern.DATE, Pattern.DATE_TIME };

    @Override
    public Long convertToDatabaseColumn(String attribute) {
        try {
            return DateUtils.parseDate(attribute, PARSE_PATTERNS).getTime();
        } catch (ParseException e) {
            LOG.error("Illegal birthday argument", e);
            throw new IllegalArgumentException("Illegal birthday argument: " + attribute);
        }
    }

    @Override
    public String convertToEntityAttribute(Long dbData) {
        return DateFormatUtils.format(dbData, PARSE_PATTERNS[0]);
    }

}
