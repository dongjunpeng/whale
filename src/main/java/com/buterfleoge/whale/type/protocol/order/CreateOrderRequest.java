package com.buterfleoge.whale.type.protocol.order;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.buterfleoge.whale.Constants;
import com.buterfleoge.whale.type.entity.OrderTraveller;

/**
 * @author Brent24
 *
 */
public class CreateOrderRequest extends OrderRequest {

    @NotNull(message = "")
    @Size(min = 1, max = Constants.DefaultValue.MAX_ORDER_TRAVELLER_COUNT, message = "")
    private List<OrderTraveller> travellers;

    private Long policyDiscountid;

    private Long couponid;

    private Long studentDiscountid;

    private int studentCount;

    @NumberFormat(style = Style.CURRENCY)
    private BigDecimal actualPrice;

    private String emergencyContact;

    private String emergencyMobile;

    private boolean roommate;

    public List<OrderTraveller> getTravellers() {
        return travellers;
    }

    public void setTravellers(List<OrderTraveller> travellers) {
        this.travellers = travellers;
    }

    public Long getPolicyDiscountid() {
        return policyDiscountid;
    }

    public void setPolicyDiscountid(Long policyDiscountid) {
        this.policyDiscountid = policyDiscountid;
    }

    /**
     * @return the couponid
     */
    public Long getCouponid() {
        return couponid;
    }

    /**
     * @param couponid
     *            the couponid to set
     */
    public void setCouponid(Long couponid) {
        this.couponid = couponid;
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

    /**
     * @return the emergencyContact
     */
    public String getEmergencyContact() {
        return emergencyContact;
    }

    /**
     * @param emergencyContact
     *            the emergencyContact to set
     */
    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    /**
     * @return the emergencyMobile
     */
    public String getEmergencyMobile() {
        return emergencyMobile;
    }

    /**
     * @param emergencyMobile
     *            the emergencyMobile to set
     */
    public void setEmergencyMobile(String emergencyMobile) {
        this.emergencyMobile = emergencyMobile;
    }

    /**
     * @return the roommate
     */
    public boolean getRoommate() {
        return roommate;
    }

    /**
     * @param roommate
     *            the roommate to set
     */
    public void setRoommate(boolean roommate) {
        this.roommate = roommate;
    }

}
