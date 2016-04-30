/**
 * 
 */
package com.buterfleoge.whale.type.enums;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author Brent24
 *
 */
public enum DiscountCodeStatus {
	/**
	 * 已生成，等待验证
	 */
	CREATED(0),

	/**
	 * 用户提交，验证通过，等待订单付款
	 */
	PASSED(1),

	/**
	 * 付款完成，优惠券不再有效
	 */
	USED(2),
	
	/**
	 * 已过期，优惠券不再有效
	 */
	EXPIRED(3),

	;

	private int status;

	private DiscountCodeStatus(int status) {
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
