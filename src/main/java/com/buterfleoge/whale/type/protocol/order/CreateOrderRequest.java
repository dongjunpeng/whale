package com.buterfleoge.whale.type.protocol.order;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.buterfleoge.whale.type.entity.OrderTravellers;
import com.buterfleoge.whale.type.protocol.Request;

/**
 * @author Brent24
 *
 */
public class CreateOrderRequest extends Request {

    private Long orderid;

    private List<OrderTravellers> travellers;

    private Long policyDiscountid;

    private String discountCode;

    private Long studentDiscountid;

    private int studentCount;

    @NumberFormat(style = Style.CURRENCY)
    private BigDecimal actualPrice;

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

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
