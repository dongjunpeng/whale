/**
 * 
 */
package com.buterfleoge.whale.type.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.type.Gender;
import com.buterfleoge.whale.type.IdType;
import com.buterfleoge.whale.type.OrderStatus;
import com.buterfleoge.whale.type.TravellerChannel;

/**
 * 订单
 * 
 * @author Brent24
 *
 */

@Entity
@Table(name = "travel_order")
public class TravelOrder extends BaseObject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderid")
	private long orderid;

	@Column(name = "travellerid")
	private long travellerid;

	@Column(name = "name")
	private String name;

	@Column(name = "id")
	private String id;

	@Column(name = "id_type")
	private IdType idType;

	@Column(name = "gender")
	private Gender gender;

	@Column(name = "birthday")
	private String birthday;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "email")
	private String email;

	@Column(name = "wxid")
	private String wxid;

	@Column(name = "address")
	private String address;

	@Column(name = "emergency_contact")
	private String emergencyContact;

	@Column(name = "emergency_phone")
	private String emergencyPhone;

	@Column(name = "groupid")
	private long groupid;

	@Column(name = "start_date")
	private String startDate;

	@Column(name = "end_date")
	private String endDate;

	@Column(name = "priceid")
	private long priceid;

	@Column(name = "start_place")
	private String startPlace;

	@Column(name = "end_place")
	private String endPlace;

	@Column(name = "channel")
	private TravellerChannel channel;

	// 单位为分，数据库存取需要转换
	@Column(name = "price")
	private long price;

	@Column(name = "status")
	private OrderStatus status;

	@Column(name = "discountid")
	private long discountid;

	@Column(name = "refoundid")
	private long refoundid;

	// 单位为分，数据库存取需要转换
	@Column(name = "actual_price")
	private long actualPrice;

	public long getOrderid() {
		return orderid;
	}

	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}

	public long getTravellerid() {
		return travellerid;
	}

	public void setTravellerid(long travellerid) {
		this.travellerid = travellerid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public IdType getIdType() {
		return idType;
	}

	public void setIdType(IdType idType) {
		this.idType = idType;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWxid() {
		return wxid;
	}

	public void setWxid(String wxid) {
		this.wxid = wxid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmergencyContact() {
		return emergencyContact;
	}

	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	public String getEmergencyPhone() {
		return emergencyPhone;
	}

	public void setEmergencyPhone(String emergencyPhone) {
		this.emergencyPhone = emergencyPhone;
	}

	public long getGroupid() {
		return groupid;
	}

	public void setGroupid(long groupid) {
		this.groupid = groupid;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public long getPriceid() {
		return priceid;
	}

	public void setPriceid(long priceid) {
		this.priceid = priceid;
	}

	public String getStartPlace() {
		return startPlace;
	}

	public void setStartPlace(String startPlace) {
		this.startPlace = startPlace;
	}

	public String getEndPlace() {
		return endPlace;
	}

	public void setEndPlace(String endPlace) {
		this.endPlace = endPlace;
	}

	public TravellerChannel getChannel() {
		return channel;
	}

	public void setChannel(TravellerChannel channel) {
		this.channel = channel;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public long getDiscountid() {
		return discountid;
	}

	public void setDiscountid(long discountid) {
		this.discountid = discountid;
	}

	public long getRefoundid() {
		return refoundid;
	}

	public void setRefoundid(long refoundid) {
		this.refoundid = refoundid;
	}

	public long getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(long actualPrice) {
		this.actualPrice = actualPrice;
	}

}
