package com.buterfleoge.whale.type;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 优惠类型
 * 
 * @author Brent24
 *
 */
public enum DiscountType {

	/**
	 * 优惠券
	 */
	COUPON(0),

	/**
	 * 单身优惠
	 */
	SINGLE(1),

	/**
	 * 双人同行
	 */
	DOUBLE(2),

	/**
	 * 三人同行
	 */
	TRIPLE(3),

	/**
	 * 多人优惠
	 */
	MULTIPLE(5),
	
	/**
	 * 原价百分比折扣
	 */
	PERCENTAGE(10)

	;

	private int type;

	private DiscountType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
