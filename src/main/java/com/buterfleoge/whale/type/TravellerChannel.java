/**
 * 
 */
package com.buterfleoge.whale.type;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author Brent24
 *
 */
public enum TravellerChannel {

	/**
	 * 未知
	 */
	UNKNOW(0),

	/**
	 * 代理商0001
	 */
	AGENT_0001(1),
	/**
	 * 代理商0002
	 */
	AGENT_0002(2),

	;

	private int channel;

	private TravellerChannel(int channel) {
		this.channel = channel;
	}

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
