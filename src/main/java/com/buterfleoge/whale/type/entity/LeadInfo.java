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
	private String special;

	@Column(name = "remark")
	private String remark;

}
