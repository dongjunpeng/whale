package com.buterfleoge.whale.type.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 领队信息
 * 
 * @author Brent24
 *
 */

@Entity
@Table(name = "leader_info")
public class LeadInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "leaderid")
	private long leaderid;

	@Column(name = "accountid")
	private long accountid;

	@Column(name = "special")
	private String special = "";

	@Column(name = "remark")
	private String remark = "";

	public long getLeaderid() {
		return leaderid;
	}

	public void setLeaderid(long leaderid) {
		this.leaderid = leaderid;
	}

	public long getAccountid() {
		return accountid;
	}

	public void setAccountid(long accountid) {
		this.accountid = accountid;
	}

	public String getSpecial() {
		return special;
	}

	public void setSpecial(String special) {
		this.special = special;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
