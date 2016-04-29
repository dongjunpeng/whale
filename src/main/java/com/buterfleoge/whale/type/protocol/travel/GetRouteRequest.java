/**
 * 
 */
package com.buterfleoge.whale.type.protocol.travel;

import com.buterfleoge.whale.type.protocol.Request;

/**
 * @author Brent24
 *
 */
public class GetRouteRequest extends Request {

	private Long routeid;

	private String name;

	private int isImgtextRequired;

	public Long getRouteid() {
		return routeid;
	}

	public void setRouteid(Long routeid) {
		this.routeid = routeid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIsImgtextRequired() {
		return isImgtextRequired;
	}

	public void setIsImgtextRequired(int isImgtextRequired) {
		this.isImgtextRequired = isImgtextRequired;
	}

}
