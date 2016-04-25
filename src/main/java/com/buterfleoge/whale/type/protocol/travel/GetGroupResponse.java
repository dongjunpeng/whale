/**
 * 
 */
package com.buterfleoge.whale.type.protocol.travel;

import java.util.List;

import com.buterfleoge.whale.type.entity.TravelGroup;
import com.buterfleoge.whale.type.protocol.Response;

/**
 * @author Brent24
 *
 */
public class GetGroupResponse extends Response {

	private List<TravelGroup> group;

	public List<TravelGroup> getGroup() {
		return group;
	}

	public void setGroup(List<TravelGroup> group) {
		this.group = group;
	}

}
