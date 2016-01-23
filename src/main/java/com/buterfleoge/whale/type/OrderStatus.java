/**
 * 
 */
package com.buterfleoge.whale.type;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 订单状态
 * 
 * @author Brent24
 *
 */
public enum OrderStatus {
	/**
	 * 生成
	 */
	CREATED(0),

	/**
	 * 已付款
	 */
	PAID(1),
	
	/**
	 * 换人
	 */
	ALTERNATIVE(2),

	/**
	 * 退款
	 */
	REFOUNDED(3),
	
	/**
	 * 结束
	 */
	FINISH(4),

	;

	private int status;

	private OrderStatus(int status) {
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
