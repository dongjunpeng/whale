package com.buterfleoge.whale.type;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 旅行类型
 * 
 * @author Brent24
 *
 */
public enum TravelType {

	/**
	 * 未知
	 */
	unknow(0),

	/**
	 * 城市
	 */
	city_walk(1),

	/**
	 * 近郊
	 */
	suburb(2),
	
	/**
	 * 短途
	 */
	short_trip(3),
	
	/**
	 * 长途
	 */
	long_trip(4),
	
	/**
	 * 国际
	 */
	international(5),

	;

	private int type;

	private TravelType(int type) {
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
