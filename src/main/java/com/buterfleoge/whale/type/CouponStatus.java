/**
 *
 */
package com.buterfleoge.whale.type;

import com.buterfleoge.whale.EnumObject;
import com.buterfleoge.whale.EnumObject.EnumObjectHelper;

/**
 *
 * <ol>
 * <li>CREATED -> TIMEOUT
 * <li>CREATED -> VERIFIED -> TIMEOUT
 * <li>CREATED -> VERIFIED -> USED
 * </ol>
 *
 * @author Brent24
 *
 */
public interface CouponStatus {

    /**
     * 已生成，等待验证
     */
    EnumObject CREATED = new EnumObject(0);

    /**
     * 已过期
     */
    EnumObject TIMEOUT = new EnumObject(1);

    /**
     * 已经被占用
     */
    EnumObject USED = new EnumObject(2);

    EnumObjectHelper<EnumObject> helper = EnumObjectHelper.create(CREATED, TIMEOUT, USED);
}
