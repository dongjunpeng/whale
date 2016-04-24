package com.buterfleoge.whale.type.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.type.GroupStatus;

/**
 * 发团信息
 * 
 * @author Brent24
 *
 */
@Entity
@Table(name = "travel_group")
public class TravelGroup extends BaseObject {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "groupid")
	private Long groupid;

	@Column(name = "routeid")
	private Long routeid;

	@Column(name = "price")
	private Long price;

	@Column(name = "status")
	private GroupStatus status;

	@Column(name = "start_date")
	private Long startDate;

	@Column(name = "end_date")
	private Long endDate;

	@Column(name = "min_count")
	private Integer minCount;

	@Column(name = "max_count")
	private Integer maxCount = 0;

	@Column(name = "actual_count")
	private Integer actualCount = 0;

	@Column(name = "wx_qrcode")
	private String wxQrcode;

	@Column(name = "add_time")
	private Long addTime;

	@Column(name = "mod_time")
	private Long modTime;

	/**
	 * @return the groupid
	 */
	public Long getGroupid() {
		return groupid;
	}

	/**
	 * @param groupid
	 *            the groupid to set
	 */
	public void setGroupid(Long groupid) {
		this.groupid = groupid;
	}

	/**
	 * @return the routeid
	 */
	public Long getRouteid() {
		return routeid;
	}

	/**
	 * @param routeid
	 *            the routeid to set
	 */
	public void setRouteid(Long routeid) {
		this.routeid = routeid;
	}

	/**
	 * @return the status
	 */
	public GroupStatus getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(GroupStatus status) {
		this.status = status;
	}

	/**
	 * @return the startDate
	 */
	public Long getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Long getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the minCount
	 */
	public Integer getMinCount() {
		return minCount;
	}

	/**
	 * @param minCount
	 *            the minCount to set
	 */
	public void setMinCount(Integer minCount) {
		this.minCount = minCount;
	}

	/**
	 * @return the maxCount
	 */
	public Integer getMaxCount() {
		return maxCount;
	}

	/**
	 * @param maxCount
	 *            the maxCount to set
	 */
	public void setMaxCount(Integer maxCount) {
		this.maxCount = maxCount;
	}

	/**
	 * @return the actualCount
	 */
	public Integer getActualCount() {
		return actualCount;
	}

	/**
	 * @param actualCount
	 *            the actualCount to set
	 */
	public void setActualCount(Integer actualCount) {
		this.actualCount = actualCount;
	}

	/**
	 * @return the wxQrcode
	 */
	public String getWxQrcode() {
		return wxQrcode;
	}

	/**
	 * @param wxQrcode
	 *            the wxQrcode to set
	 */
	public void setWxQrcode(String wxQrcode) {
		this.wxQrcode = wxQrcode;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getAddTime() {
		return addTime;
	}

	public void setAddTime(Long addTime) {
		this.addTime = addTime;
	}

	public Long getModTime() {
		return modTime;
	}

	public void setModTime(Long modTime) {
		this.modTime = modTime;
	}

}
