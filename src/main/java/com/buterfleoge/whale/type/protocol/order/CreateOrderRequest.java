package com.buterfleoge.whale.type.protocol.order;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.buterfleoge.whale.Constants;
import com.buterfleoge.whale.type.entity.OrderTravellers;

/**
 * @author Brent24
 *
 */
public class CreateOrderRequest extends OrderRequest {

    @NotNull(message = "")
    @Size(min = 1, max = Constants.DefaultValue.MAX_ORDER_TRAVELLER_COUNT, message = "")
    private List<OrderTravellers> travellers;

    private Long policyDiscountid;

    private String discountCode;

    private Long studentDiscountid;

    private int studentCount;

    @NumberFormat(style = Style.CURRENCY)
    private BigDecimal actualPrice;

    public List<OrderTravellers> getTravellers() {
        return travellers;
    }

    public void setTravellers(List<OrderTravellers> travellers) {
        this.travellers = travellers;
    }

    public Long getPolicyDiscountid() {
        return policyDiscountid;
    }

    public void setPolicyDiscountid(Long policyDiscountid) {
        this.policyDiscountid = policyDiscountid;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public Long getStudentDiscountid() {
        return studentDiscountid;
    }

    public void setStudentDiscountid(Long studentDiscountid) {
        this.studentDiscountid = studentDiscountid;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

}
