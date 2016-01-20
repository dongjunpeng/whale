/**
 * 
 */
package com.buterfleoge.whale.type;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 退款计算方式
 * 
 * @author Brent24
 *
 */
public enum RefoundCalculation {
	// 价格单位为分，存储类型为long，尽可能不用百分比优惠，否则计算问题处理非常麻烦

	/**
	 * 扣除后退款(退款=付款-n)
	 */
	MINUS(0),

	/**
	 * 百分比后退还(退款=付款*n%)
	 */
	PERCENTAGE(1),

	/**
	 * 扣除百分比后退还(退款=付款*(1-n%))
	 */
	PERCENTAGE_OFF(2),

	;

	private int calculation;

	private RefoundCalculation(int calculation) {
		this.calculation = calculation;
	}

	public int getCalculation() {
		return calculation;
	}

	public void setCalculation(int calculation) {
		this.calculation = calculation;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
