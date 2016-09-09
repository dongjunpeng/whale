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
     * 20天-8天退还80%
     */
    public static final RefundType LONG_PCT_80 = new RefundType(1, 80);

    /**
     * 7天-1天退还50%
     */
    public static final RefundType LONG_PCT_50 = new RefundType(2, 50);

    /**
     * 集合日当天退20%
     */
    public static final RefundType LONG_PCT_20 = new RefundType(3, 20);

    /**
     * 7天以上退100%
     */
    public static final RefundType SHORT_PCT_100 = new RefundType(4, 100);

    /**
     * 6天-4天退80%
     */
    public static final RefundType SHORT_PCT_80 = new RefundType(5, 80);

    /**
     * 3天-1天退50%
     */
    public static final RefundType SHORT_PCT_50 = new RefundType(6, 50);

    /**
     * 出行日当天
     */
    public static final RefundType SHORT_PCT_20 = new RefundType(7, 20);

    public static final EnumObjectHelper<RefundType> HELPER = EnumObjectHelper.create(LONG_PCT_95, LONG_PCT_80,
            LONG_PCT_50, LONG_PCT_20, SHORT_PCT_100, SHORT_PCT_80, SHORT_PCT_50, SHORT_PCT_20);

    private static final BigDecimal HUNDRED_PERCENT = BigDecimal.ONE;

    private BigDecimal percent;

    private RefundType(int value, int percent) {
        super(value);
        this.percent = BigDecimal.valueOf(percent / 100.00).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getDeductPrice(BigDecimal actualPrice) {
        return actualPrice.multiply(HUNDRED_PERCENT.subtract(percent));
    }

    public BigDecimal getRefundPrice(BigDecimal actualPrice) {
        return actualPrice.multiply(percent);
    }

    /**
     * @return the percent
     */
    public BigDecimal getPercent() {
        return percent;
    }

}
