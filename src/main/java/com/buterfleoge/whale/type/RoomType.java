/**
 * 
 */
package com.buterfleoge.whale.type;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 房间类型
 * 
 * @author Brent24
 *
 */
public enum RoomType {

	/**
	 * 2人标准间
	 */
	DOUBLE_ROOM(0),

	/**
	 * 单人间
	 */
	SINGLE_ROOM(1),

	/**
	 * 三人间
	 */
	TRIPLE_ROOM(2),

	;

	private int type;

	private RoomType(int type) {
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
