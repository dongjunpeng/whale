/**
 * 
 */
package com.buterfleoge.whale.type.protocol.travel;

import com.buterfleoge.whale.type.protocol.Request;

/**
 * @author Brent24
 *
 */
public class GetGroupRequest extends Request {

	private Long groupid;
	private Long routeid;
	private String name;

	public Long getGroupid() {
		return groupid;
	}

	public void setGroupid(Long groupid) {
		this.groupid = groupid;
	}

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

}
