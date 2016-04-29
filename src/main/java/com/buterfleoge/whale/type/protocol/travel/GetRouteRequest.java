/**
 * 
 */
package com.buterfleoge.whale.type.protocol.travel;

import java.util.List;

import com.buterfleoge.whale.type.protocol.Request;

/**
 * @author Brent24
 *
 */
public class GetRouteRequest extends Request {

	private List<Long> routeids;

	private String name;

	private int isImgtextRequired;

	public List<Long> getRouteids() {
		return routeids;
	}

	public void setRouteids(List<Long> routeids) {
		this.routeids = routeids;
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
