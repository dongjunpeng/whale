package com.buterfleoge.whale.type.entity.converter;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author xiezhenzong
 *
 */
@Converter(autoApply = true)
public class PriceConverter implements AttributeConverter<BigDecimal, Long> {

    public static final BigDecimal YUAN_LI_FACTOR = BigDecimal.valueOf(1000); // 元 - 厘
    public static final BigDecimal YUAN_FEN_FACTOR = BigDecimal.valueOf(100); // 元 - 分

    @Override
    public Long convertToDatabaseColumn(BigDecimal attribute) {
        return yuanToLi(attribute);
    }

    @Override
    public BigDecimal convertToEntityAttribute(Long dbData) {
        return liToYuan(dbData);
    }

    public static final Long yuanToLi(BigDecimal yuan) {
        return yuan != null ? yuan.multiply(YUAN_LI_FACTOR).longValue() : null;
    }

    public static final BigDecimal liToYuan(Long li) {
        return li != null ? BigDecimal.valueOf(li).divide(YUAN_LI_FACTOR, 2, RoundingMode.HALF_UP) : null;
    }

    public static final Long yuanToFen(BigDecimal yuan) {
        return yuan != null ? yuan.multiply(YUAN_FEN_FACTOR).longValue() : null;
    }

    public static final BigDecimal fenToYuan(Long fen) {
        return fen != null ? BigDecimal.valueOf(fen).divide(YUAN_FEN_FACTOR, 2, RoundingMode.HALF_UP) : null;
    }

}
