/**
 *
 */
package com.buterfleoge.whale.type;

import com.buterfleoge.whale.EnumObject;

/**
 * 退款类型
 *
 * @author Brent24
 *
 */
public class RefoundType extends EnumObject {

    /**
     * 21天以上退还95%
     */
    public static final RefoundType LONG_PCT_95 = new RefoundType(0);

    /**
     * 21天-15天退还80%
     */
    public static final RefoundType LONG_PCT_80 = new RefoundType(1);

    /**
     * 14天-8天退还80%
     */
    public static final RefoundType LONG_PCT_50 = new RefoundType(2);

    /**
     * 7天至未出行
     */
    public static final RefoundType LONG_PCT_20 = new RefoundType(3);

    /**
     * 8天以上退100%
     */
    public static final RefoundType SHORT_PCT_100 = new RefoundType(4);

    /**
     * 7天-5天退80%
     */
    public static final RefoundType SHORT_PCT_80 = new RefoundType(5);

    /**
     * 4天-2天退50%
     */
    public static final RefoundType SHORT_PCT_50 = new RefoundType(6);

    /**
     * 1天至未出行退20%
     */
    public static final RefoundType SHORT_PCT_20 = new RefoundType(7);

    
    public static final EnumObjectHelper<RefoundType> HELPER = EnumObjectHelper.create(LONG_PCT_95, LONG_PCT_80, LONG_PCT_50, LONG_PCT_20,SHORT_PCT_100, SHORT_PCT_80, SHORT_PCT_50, SHORT_PCT_20);

    private RefoundType(int value) {
        super(value);
    }

}
