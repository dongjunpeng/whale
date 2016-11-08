/**
 *
 */
package com.buterfleoge.whale.type;

import com.buterfleoge.whale.EnumObject;

/**
 * @author xiezhenzong
 *
 */
public class OrderHistoryType extends EnumObject {

    public static final OrderHistoryType NEW = new OrderHistoryType(0);

    public static final OrderHistoryType SAVE = new OrderHistoryType(1);

    public static final OrderHistoryType ALIPAY = new OrderHistoryType(2);

    public static final OrderHistoryType WXPAY_JSAPI = new OrderHistoryType(3);

    public static final EnumObjectHelper<OrderHistoryType> HELPER = EnumObjectHelper.create(NEW, SAVE, ALIPAY, WXPAY_JSAPI);

    public OrderHistoryType(int value) {
        super(value);
    }

}
