package com.buterfleoge.whale.type.protocol.order;

import java.math.BigDecimal;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

/**
 * @author xiezhenzong
 *
 */
public class PreviewContractRequest extends OrderRequest {

    private String travellers;

    private Integer count;

    @NumberFormat(style = Style.CURRENCY)
    private BigDecimal price;

    @NumberFormat(style = Style.CURRENCY)
    private BigDecimal actualPrice;

    /**
     * @return the travellers
     */
    public String getTravellers() {
        return travellers;
    }

    /**
     * @param travellers
     *            the travellers to set
     */
    public void setTravellers(String travellers) {
        this.travellers = travellers;
    }

    /**
     * @return the count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * @param count
     *            the count to set
     */
    public void setCount(Integer count) {
        this.count = count;
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
