package com.buterfleoge.whale.type.protocol.order;

import java.math.BigDecimal;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.buterfleoge.whale.type.protocol.Response;

/**
 * @author xiezhenzong
 *
 */
public class GetRefundTypeResponse extends Response {

    /**
     * 订单实际金额
     */
    @NumberFormat(style = Style.CURRENCY)
    private BigDecimal actualPrice;

    /**
     * 距离出发的时间
     */
    private Long dayLeft;

    /**
     * 退款类型
     */
    private Integer type;

    /**
     * 扣除金额
     */
    @NumberFormat(style = Style.CURRENCY)
    private BigDecimal deductPrice;

    /**
     * 退款金额
     */
    @NumberFormat(style = Style.CURRENCY)
    private BigDecimal refundPrice;

    /**
     * @return the actualPrice
     */
    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    /**
     * @param actualPrice
     *            the actualPrice to set
     */
    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    /**
     * @return the dayLeft
     */
    public Long getDayLeft() {
        return dayLeft;
    }

    /**
     * @param dayLeft
     *            the dayLeft to set
     */
    public void setDayLeft(Long dayLeft) {
        this.dayLeft = dayLeft;
    }

    /**
     * @return the type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return the deductPrice
     */
    public BigDecimal getDeductPrice() {
        return deductPrice;
    }

    /**
     * @param deductPrice
     *            the deductPrice to set
     */
    public void setDeductPrice(BigDecimal deductPrice) {
        this.deductPrice = deductPrice;
    }

    /**
     * @return the refundPrice
     */
    public BigDecimal getRefundPrice() {
        return refundPrice;
    }

    /**
     * @param refundPrice
     *            the refundPrice to set
     */
    public void setRefundPrice(BigDecimal refundPrice) {
        this.refundPrice = refundPrice;
    }

}
