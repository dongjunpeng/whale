/**
 *
 */
package com.buterfleoge.whale.type;

import com.buterfleoge.whale.EnumObject;

/**
 * @author xiezhenzong
 *
 */
public class PayType extends EnumObject {

    public static final PayType ALIPAY = new PayType(0);

    public static final PayType WXPAY_JSAPI = new PayType(1);

    public static final EnumObjectHelper<PayType> HELPER = EnumObjectHelper.create(ALIPAY, WXPAY_JSAPI);

    public PayType(int value) {
        super(value);
    }

}
