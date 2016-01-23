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
import com.buterfleoge.whale.type.RefoundCalculation;
import com.buterfleoge.whale.type.RefoundType;

/**
 * 订单退款表
 * 
 * @author Brent24
 *
 */

@Entity
@Table(name = "order_refound")
public class OrderRefound extends BaseObject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "refoundid")
	private long refoundid;

	@Column(name = "type")
	private RefoundType type=RefoundType.PERSONAL;

	@Column(name = "calculation")
	private RefoundCalculation calculation=RefoundCalculation.MINUS;

	// 价格单位为分，存储类型为long，尽可能不用百分比计算，否则计算问题处理非常麻烦
	// 定义为long只支持MINUS类型优惠
	// 涉及百分比优惠需要修改
	@Column(name = "value")
	private long value=0;

	@Column(name = "description")
	private String description="";

	public long getRefoundid() {
		return refoundid;
	}

	public void setRefoundid(long refoundid) {
		this.refoundid = refoundid;
	}

	public RefoundType getType() {
		return type;
	}

	public void setType(RefoundType type) {
		this.type = type;
	}

	public RefoundCalculation getCalculation() {
		return calculation;
	}

	public void setCalculation(RefoundCalculation calculation) {
		this.calculation = calculation;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
