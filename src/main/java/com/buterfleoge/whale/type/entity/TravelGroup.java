package com.buterfleoge.whale.type.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.type.enums.GroupStatus;

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

	@Column(name = "start_date")
	private Long startDate;

	@Column(name = "end_date")
	private Long endDate;

	@Column(name = "title")
	private String title;

	@Column(name = "price")
	private Long price;

	@Column(name = "status")
	private GroupStatus status;

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

	public Long getGroupid() {
		return groupid;
	}

	public void setGroupid(Long groupid) {
		this.groupid = groupid;
	}

	public Long getRouteid() {
		return routeid;
	}

	public void setRouteid(Long routeid) {
		this.routeid = routeid;
	}

	public Long getStartDate() {
		return startDate;
	}

	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}

	public Long getEndDate() {
		return endDate;
	}

	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public GroupStatus getStatus() {
		return status;
	}

	public void setStatus(GroupStatus status) {
		this.status = status;
	}

	public Integer getMinCount() {
		return minCount;
	}

	public void setMinCount(Integer minCount) {
		this.minCount = minCount;
	}

	public Integer getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(Integer maxCount) {
		this.maxCount = maxCount;
	}

	public Integer getActualCount() {
		return actualCount;
	}

	public void setActualCount(Integer actualCount) {
		this.actualCount = actualCount;
	}

	public String getWxQrcode() {
		return wxQrcode;
	}

	public void setWxQrcode(String wxQrcode) {
		this.wxQrcode = wxQrcode;
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
