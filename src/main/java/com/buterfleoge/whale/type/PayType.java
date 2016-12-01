/**
 *
 */
package com.buterfleoge.whale.type;

import com.buterfleoge.whale.EnumObject;
import com.buterfleoge.whale.EnumObject.EnumObjectHelper;

/**
 * @author xiezhenzong
 *
 */
public interface PayType {

    EnumObject ALIPAY = new EnumObject(0);

    EnumObject WXPAY_JSAPI = new EnumObject(1);

    EnumObjectHelper<EnumObject> helper = EnumObjectHelper.create(ALIPAY, WXPAY_JSAPI);
}
