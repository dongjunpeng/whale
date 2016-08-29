/**
 *
 */
package com.buterfleoge.whale.type;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.buterfleoge.whale.EnumObject;

/**
 * 退款类型
 *
 * @author Brent24
 *
 */
public class RefundType extends EnumObject {

    /**
     * 21天以上退还95%
     */
    public static final RefundType LONG_PCT_95 = new RefundType(0, 95);

    /**
     * 21天-15天退还80%
     */
    public static final RefundType LONG_PCT_80 = new RefundType(1, 80);

    /**
     * 14天-8天退还80%
     */
    public static final RefundType LONG_PCT_50 = new RefundType(2, 50);

    /**
     * 7天至未出行
     */
    public static final RefundType LONG_PCT_20 = new RefundType(3, 20);

    /**
     * 8天以上退100%
     */
    public static final RefundType SHORT_PCT_100 = new RefundType(4, 100);

    /**
     * 7天-5天退80%
     */
    public static final RefundType SHORT_PCT_80 = new RefundType(5, 80);

    /**
     * 4天-2天退50%
     */
    public static final RefundType SHORT_PCT_50 = new RefundType(6, 50);

    /**
     * 1天至未出行退20%
     */
    public static final RefundType SHORT_PCT_20 = new RefundType(7, 20);

    public static final EnumObjectHelper<RefundType> HELPER = EnumObjectHelper.create(LONG_PCT_95, LONG_PCT_80, LONG_PCT_50, LONG_PCT_20,
            SHORT_PCT_100, SHORT_PCT_80, SHORT_PCT_50, SHORT_PCT_20);

    private BigDecimal percent;

    private RefundType(int value, int percent) {
        super(value);
        this.percent = BigDecimal.valueOf(percent / 100.00).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getRefund(BigDecimal actualPrice) {
        return actualPrice.multiply(percent);
    }

}
