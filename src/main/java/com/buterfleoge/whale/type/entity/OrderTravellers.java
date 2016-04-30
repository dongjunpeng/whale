package com.buterfleoge.whale.type.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.type.enums.OrderStaffStatus;

/**
 *
 * @author xiezhenzong
 *
 */
@Entity(name = "order_travellers")
public class OrderTravellers extends BaseObject {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "travellerid")
	private Long travellerid;

	@Column(name = "orderid")
	private Long orderid;

	@Column(name = "contactid")
	private Long contactid;

	@Column(name = "roommate")
	private String roommate;

	/**
	 * @return the travellerid
	 */
	public Long getTravellerid() {
		return travellerid;
	}

	/**
	 * @param travellerid
	 *            the travellerid to set
	 */
	public void setTravellerid(Long travellerid) {
		this.travellerid = travellerid;
	}

	/**
	 * @return the orderid
	 */
	public Long getOrderid() {
		return orderid;
	}

	/**
	 * @param orderid
	 *            the orderid to set
	 */
	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}

	/**
	 * @return the contactid
	 */
	public Long getContactid() {
		return contactid;
	}

	/**
	 * @param contactid
	 *            the contactid to set
	 */
	public void setContactid(Long contactid) {
		this.contactid = contactid;
	}

	/**
	 * @return the roommate
	 */
	public String getRoommate() {
		return roommate;
	}

	/**
	 * @param roommate
	 *            the roommate to set
	 */
	public void setRoommate(String roommate) {
		this.roommate = roommate;
	}

}
