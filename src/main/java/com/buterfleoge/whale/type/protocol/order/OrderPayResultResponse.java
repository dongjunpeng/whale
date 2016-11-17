package com.buterfleoge.whale.type.protocol.order;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Convert;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.buterfleoge.whale.type.entity.OrderInfo;
import com.buterfleoge.whale.type.entity.converter.PriceConverter;
import com.buterfleoge.whale.type.protocol.Response;

/**
 * @author xiezhenzong
 *
 */
public class OrderPayResultResponse extends Response {

    /**
     * @see OrderInfo#getStatus()
     */
    private Integer orderStatus;

    @NumberFormat(style = Style.CURRENCY)
    @Column(name = "price")
    @Convert(converter = PriceConverter.class)
    private BigDecimal price = BigDecimal.ZERO;

    @NumberFormat(style = Style.CURRENCY)
    @Column(name = "actual_price")
    @Convert(converter = PriceConverter.class)
    private BigDecimal actualPrice = BigDecimal.ZERO;

    /**
     * @return the orderStatus
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * @param orderStatus
     *            the orderStatus to set
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price
     *            the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

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

}
