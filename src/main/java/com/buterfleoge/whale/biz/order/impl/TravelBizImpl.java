/**
 * 
 */
package com.buterfleoge.whale.biz.order.impl;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buterfleoge.whale.Constants.Status;
import com.buterfleoge.whale.biz.travel.TravelBiz;
import com.buterfleoge.whale.dao.TravelRouteRepository;
import com.buterfleoge.whale.type.entity.TravelRoute;
import com.buterfleoge.whale.type.protocol.Request;
import com.buterfleoge.whale.type.protocol.Response;
import com.buterfleoge.whale.type.protocol.travel.GetRouteResponse;
import com.buterfleoge.whale.type.protocol.travel.GetRouteRequest;

/**
 * @author Brent24
 *
 */

@Service("travelBiz")
public class TravelBizImpl implements TravelBiz {

	private static final Logger LOG = LoggerFactory.getLogger(TravelBizImpl.class);

	@Autowired
	private TravelRouteRepository travelRouteRepository;

	@Override
	public void getRoute(GetRouteRequest request, GetRouteResponse response) throws Exception {
		Long routeid = request.getRouteid();
		List<TravelRoute> route;
		try {
			if (routeid == null) {
				route = travelRouteRepository.findAll();
			} else {
				route = travelRouteRepository.findByRouteid(routeid);
			}
			response.setRoute(route);
			response.setStatus(Status.OK);
		} catch (Exception e) {
			LOG.error("find contacts failed", e);
			response.setStatus(Status.DB_ERROR);
		}
	}

	@Override
	public void getRouteByCondition(Request request, Response response) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void getGroupByRoute(Request request, Response response) throws Exception {
		// TODO Auto-generated method stub

	}

}
