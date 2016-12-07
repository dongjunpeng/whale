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
public interface OrderHistoryType {

    EnumObject NEW = new EnumObject(0);

    EnumObject SAVE = new EnumObject(1);

    EnumObject ALIPAY = new EnumObject(2);

    EnumObject WXPAY_JSAPI = new EnumObject(3);

    EnumObjectHelper<EnumObject> helper = EnumObjectHelper.create(NEW, SAVE, ALIPAY, WXPAY_JSAPI);
}
