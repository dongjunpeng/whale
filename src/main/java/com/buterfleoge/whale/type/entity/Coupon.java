/**
 *
 */
package com.buterfleoge.whale.type.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.Constants;
import com.buterfleoge.whale.Constants.ErrorMsg;
import com.buterfleoge.whale.Constants.Pattern;
import com.buterfleoge.whale.EnumObject;
import com.buterfleoge.whale.type.CouponStatus;
import com.buterfleoge.whale.type.entity.converter.DateTimeConverter;
import com.buterfleoge.whale.type.entity.converter.PriceConverter;

/**
 * 优惠券
 *
 * @author xiezhenzong
 *
 */
@Entity
@Table(name = "coupon")
public class Coupon extends BaseObject {

    public static final Boolean VERIFY = Boolean.TRUE;
    public static final Boolean UNVERIFY = Boolean.FALSE;

    public static final Map<Integer, String> ERRMSG = new HashMap<Integer, String>();

    static {
        ERRMSG.put(CouponStatus.TIMEOUT.value, ErrorMsg.DISCOUNT_CODE_TIMEOUT);
        ERRMSG.put(CouponStatus.USED.value, ErrorMsg.DISCOUNT_CODE_USED);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "couponid")
    private Long couponid;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private Integer type;

    @Column(name = "status")
    private Integer status;

    @NumberFormat(style = Style.CURRENCY)
    @Column(name = "value")
    @Convert(converter = PriceConverter.class)
    private BigDecimal value;

    @Column(name = "verify")
    private Boolean verify;

    @Column(name = "update_count")
    private Integer updateCount;

    @Column(name = "discount_code")
    private String discountCode;

    @Column(name = "accountid")
    private Long accountid;

    @Column(name = "agent")
    private Long agent;

    @DateTimeFormat(pattern = Pattern.DATE)
    @Column(name = "start_time")
    @Convert(converter = DateTimeConverter.class)
    private Date startTime;

    @DateTimeFormat(pattern = Pattern.DATE)
    @Column(name = "end_time")
    @Convert(converter = DateTimeConverter.class)
    private Date endTime;

    @DateTimeFormat(pattern = Pattern.DATE)
    @Column(name = "valide_time")
    @Convert(converter = DateTimeConverter.class)
    private Date valideTime;

    @DateTimeFormat(pattern = Pattern.DATE)
    @Column(name = "use_time")
    @Convert(converter = DateTimeConverter.class)
    private Date useTime;

    @DateTimeFormat(pattern = Pattern.DATE)
    @Column(name = "add_time")
    @Convert(converter = DateTimeConverter.class)
    private Date addTime;

    @DateTimeFormat(pattern = Pattern.DATE)
    @Column(name = "mod_time")
    @Convert(converter = DateTimeConverter.class)
    private Date modTime;

    public static Coupon createCoupon(Long accountid, String name, EnumObject type, BigDecimal value) {
        Date now = new Date();
        Coupon coupon = new Coupon();
        coupon.setName(name);
        coupon.setType(type.value);
        coupon.setStatus(CouponStatus.CREATED.value);
        coupon.setValue(value);
        coupon.setVerify(Coupon.VERIFY);
        coupon.setUpdateCount(0);
        coupon.setAccountid(accountid);
        coupon.setStartTime(now);
        coupon.setEndTime(Constants.FUTURE);
        coupon.setValideTime(now);
        coupon.setAddTime(now);
        coupon.setModTime(now);
        return coupon;
    }

    public static Coupon createDiscountCode(Long accountid, String name, EnumObject type, BigDecimal value, String discountCode) {
        Date now = new Date();
        Coupon coupon = new Coupon();
        coupon.setName(name);
        coupon.setType(type.value);
        coupon.setStatus(CouponStatus.CREATED.value);
        coupon.setValue(value);
        coupon.setVerify(Coupon.UNVERIFY);
        coupon.setUpdateCount(0);
        coupon.setDiscountCode(discountCode);
        coupon.setAccountid(accountid);
        coupon.setStartTime(now);
        coupon.setEndTime(Constants.FUTURE);
        coupon.setAddTime(now);
        coupon.setModTime(now);
        return coupon;
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

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
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
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return the value
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * @return the verify
     */
    public Boolean getVerify() {
        return verify;
    }

    /**
     * @param verify
     *            the verify to set
     */
    public void setVerify(Boolean verify) {
        this.verify = verify;
    }

    /**
     * @return the updateCount
     */
    public Integer getUpdateCount() {
        return updateCount;
    }

    /**
     * @param updateCount
     *            the updateCount to set
     */
    public void setUpdateCount(Integer updateCount) {
        this.updateCount = updateCount;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    /**
     * @return the discountCode
     */
    public String getDiscountCode() {
        return discountCode;
    }

    /**
     * @param discountCode
     *            the discountCode to set
     */
    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    /**
     * @return the accountid
     */
    public Long getAccountid() {
        return accountid;
    }

    /**
     * @param accountid
     *            the accountid to set
     */
    public void setAccountid(Long accountid) {
        this.accountid = accountid;
    }

    /**
     * @return the agent
     */
    public Long getAgent() {
        return agent;
    }

    /**
     * @param agent
     *            the agent to set
     */
    public void setAgent(Long agent) {
        this.agent = agent;
    }

    /**
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     *            the startTime to set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     *            the endTime to set
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the valideTime
     */
    public Date getValideTime() {
        return valideTime;
    }

    /**
     * @param valideTime
     *            the valideTime to set
     */
    public void setValideTime(Date valideTime) {
        this.valideTime = valideTime;
    }

    /**
     * @return the useTime
     */
    public Date getUseTime() {
        return useTime;
    }

    /**
     * @param useTime
     *            the useTime to set
     */
    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    /**
     * @return the addTime
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * @param addTime
     *            the addTime to set
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * @return the modTime
     */
    public Date getModTime() {
        return modTime;
    }

    /**
     * @param modTime
     *            the modTime to set
     */
    public void setModTime(Date modTime) {
        this.modTime = modTime;
    }
}
