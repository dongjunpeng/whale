package com.buterfleoge.whale.type.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 订单替换表
 * 
 * @author Brent24
 *
 */

@Entity
@Table(name = "order_alternative")
public class OrderAlternative {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "alternativeid")
	private long alternativeid;

	@Column(name = "orderid")
	private long orderid;

	@Column(name = "alternative_travellerid")
	private long alternativeTravellerid;

	@Column(name = "remark")
	private String remark="";

	public long getAlternativeid() {
		return alternativeid;
	}

	public void setAlternativeid(long alternativeid) {
		this.alternativeid = alternativeid;
	}

	public long getOrderid() {
		return orderid;
	}

	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}

	public long getAlternativeTravellerid() {
		return alternativeTravellerid;
	}

	public void setAlternativeTravellerid(long alternativeTravellerid) {
		this.alternativeTravellerid = alternativeTravellerid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}
