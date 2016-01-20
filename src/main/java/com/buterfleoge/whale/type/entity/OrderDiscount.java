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
import com.buterfleoge.whale.type.DiscountCalculation;
import com.buterfleoge.whale.type.DiscountType;

/**
 * 订单优惠表
 * 
 * @author Brent24
 *
 */

@Entity
@Table(name = "order_discount")
public class OrderDiscount extends BaseObject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "discountid")
	private long discountid;

	@Column(name = "type")
	private DiscountType type;

	@Column(name = "calculation")
	private DiscountCalculation calculation;

	// 价格单位为分，存储类型为long，尽可能不用百分比优惠，否则计算问题处理非常麻烦
	// 定义为long只支持MINUS类型优惠
	// 涉及百分比优惠需要修改
	@Column(name = "value")
	private long value;

	@Column(name = "description")
	private String description;

	public long getDiscountid() {
		return discountid;
	}

	public void setDiscountid(long discountid) {
		this.discountid = discountid;
	}

	public DiscountType getType() {
		return type;
	}

	public void setType(DiscountType type) {
		this.type = type;
	}

	public DiscountCalculation getCalculation() {
		return calculation;
	}

	public void setCalculation(DiscountCalculation calculation) {
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
