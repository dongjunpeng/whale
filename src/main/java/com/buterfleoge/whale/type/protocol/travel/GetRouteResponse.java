/**
 * 
 */
package com.buterfleoge.whale.type.protocol.travel;

import java.util.List;

import com.buterfleoge.whale.type.entity.TravelRoute;
import com.buterfleoge.whale.type.protocol.Response;

/**
 * @author Brent24
 *
 */
public class GetRouteResponse extends Response {

	private List<TravelRoute> route;

	public List<TravelRoute> getRoute() {
		return route;
	}

	public void setRoute(List<TravelRoute> route) {
		this.route = route;
	}

}
