/**
 * 
 */
package com.buterfleoge.whale.type.protocol.travel;

import java.util.List;

import com.buterfleoge.whale.type.entity.TravelRoute;
import com.buterfleoge.whale.type.protocol.Response;
import com.buterfleoge.whale.type.protocol.travel.imgtext.Imgtext;

/**
 * @author Brent24
 *
 */
public class GetRouteResponse extends Response {

	private List<TravelRoute> route;
	private Imgtext imgtext;

	public List<TravelRoute> getRoute() {
		return route;
	}

	public void setRoute(List<TravelRoute> route) {
		this.route = route;
	}

	public Imgtext getImgtext() {
		return imgtext;
	}

	public void setImgtext(Imgtext imgtext) {
		this.imgtext = imgtext;
	}

}
