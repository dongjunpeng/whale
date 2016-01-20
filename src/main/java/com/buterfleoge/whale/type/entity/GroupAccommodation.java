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
import com.buterfleoge.whale.type.AccommodationStatus;
import com.buterfleoge.whale.type.RoomType;


/**
 * 住宿信息
 * 
 * @author Brent24
 *
 */

@Entity
@Table(name = "group_accommodation")
public class GroupAccommodation extends BaseObject{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "accommodationid")
	private long accommodationid;

	@Column(name = "groupid")
	private long groupid;

	@Column(name = "status")
	private AccommodationStatus status;

	@Column(name = "date")
	private String date;

	@Column(name = "city")
	private String city;

	@Column(name = "name")
	private String name;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "room_type")
	private RoomType roomType;
	
	//单位为分，数据库存取需要转换
	@Column(name = "room_price")
	private long roomPrice;
	
	@Column(name = "room_quantity")
	private int roomQuantity;
	
	//单位为分，数据库存取需要转换
	@Column(name = "total")
	private long total;
	
	@Column(name = "remark")
	private String remark;

	public long getAccommodationid() {
		return accommodationid;
	}

	public void setAccommodationid(long accommodationid) {
		this.accommodationid = accommodationid;
	}

	public long getGroupid() {
		return groupid;
	}

	public void setGroupid(long groupid) {
		this.groupid = groupid;
	}

	public AccommodationStatus getStatus() {
		return status;
	}

	public void setStatus(AccommodationStatus status) {
		this.status = status;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public long getRoomPrice() {
		return roomPrice;
	}

	public void setRoomPrice(long roomPrice) {
		this.roomPrice = roomPrice;
	}

	public int getRoomQuantity() {
		return roomQuantity;
	}

	public void setRoomQuantity(int roomQuantity) {
		this.roomQuantity = roomQuantity;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
