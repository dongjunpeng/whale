package com.buterfleoge.whale.type.entity.converter;

import java.math.BigDecimal;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author xiezhenzong
 *
 */
@Converter(autoApply = true)
public class PriceConverter implements AttributeConverter<BigDecimal, Long> {

    public static final long PRICE_FACTOR = 1000L;
    public static final BigDecimal PRICE_FACTOR_BIGDECIMAL = BigDecimal.valueOf(PRICE_FACTOR);

    @Override
    public Long convertToDatabaseColumn(BigDecimal attribute) {
        return attribute.multiply(PRICE_FACTOR_BIGDECIMAL).longValue();
    }

    @Override
    public BigDecimal convertToEntityAttribute(Long dbData) {
        return BigDecimal.valueOf(dbData / PRICE_FACTOR);
    }

}
