package com.buterfleoge.whale.type.entity.converter;

import javax.persistence.AttributeConverter;

/**
 * @author xiezhenzong
 *
 */
public class AlipayNotifyTypeConverter implements AttributeConverter<String, Integer> {

    private static final String SYNC = "trade_status_sync";
    private static final String ASYNC = "trade_status_async";
    private static final String UNKNOW = "unknow";

    @Override
    public Integer convertToDatabaseColumn(String attribute) {
        if (SYNC.equals(attribute)) {
            return 0;
        } else if (ASYNC.equals(attribute)) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public String convertToEntityAttribute(Integer dbData) {
        if (0 == dbData) {
            return SYNC;
        } else if (1 == dbData) {
            return ASYNC;
        } else {
            return UNKNOW;
        }
    }

}
