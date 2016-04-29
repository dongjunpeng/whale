package com.buterfleoge.whale.biz.travel.impl;

import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buterfleoge.whale.Constants.Status;
import com.buterfleoge.whale.biz.travel.TravelBiz;
import com.buterfleoge.whale.dao.TravelGroupRepository;
import com.buterfleoge.whale.dao.TravelRouteRepository;
import com.buterfleoge.whale.type.entity.TravelGroup;
import com.buterfleoge.whale.type.entity.TravelRoute;
import com.buterfleoge.whale.type.protocol.Error;
import com.buterfleoge.whale.type.protocol.Request;
import com.buterfleoge.whale.type.protocol.travel.GetGroupRequest;
import com.buterfleoge.whale.type.protocol.travel.GetGroupResponse;
import com.buterfleoge.whale.type.protocol.travel.GetRouteRequest;
import com.buterfleoge.whale.type.protocol.travel.GetRouteResponse;

/**
 * @author Brent24
 *
 */

@Service("travelBiz")
public class TravelBizImpl implements TravelBiz {

    private static final Logger LOG = LoggerFactory.getLogger(TravelBizImpl.class);

    @Autowired
    private TravelRouteRepository travelRouteRepository;

    @Autowired
    private TravelGroupRepository travelGroupRepository;

    @Override
    public void getRoute(GetRouteRequest request, GetRouteResponse response) throws Exception {
        List<Long> routeids = request.getRouteids();
        String name = request.getName();
        List<TravelRoute> routes = null;
        try {
            if (routeids != null) {
                if (routeids.size() == 1) {
                    routes = travelRouteRepository.findByRouteidAndVisibleTrue(routeids.get(0));
                } else {
                    routes = travelRouteRepository.findByRouteidInAndVisibleTrue(new HashSet<Long>(routeids));
                }
            } else {
                if (name != null) {
                    routes = travelRouteRepository.findByNameAndVisibleTrue(name);
                } else {
                    routes = travelRouteRepository.findByVisibleTrue();
                }
            }

            if (routes.isEmpty()) {
                response.setStatus(Status.PARAM_ERROR);
            } else {
                response.setRoutes(routes);
                response.setStatus(Status.OK);
            }

        } catch (Exception e) {
            LOG.error("find route failed", e);
            response.setStatus(Status.DB_ERROR);
        }
    }

    @Override
    public void getRouteByCondition(Request request, GetRouteResponse response) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void getGroup(GetGroupRequest request, GetGroupResponse response) throws Exception {

        Long routeid = request.getRouteid();
        Long groupid = request.getGroupid();
        String name = request.getName();

        // Long now=new Date().getTime()/1000;
        Long now = 20160425L;// 先写死测试，后面用系统获取时间

        List<TravelGroup> group = null;

        try {
            if (groupid != null) {
                group = travelGroupRepository.findByGroupid(groupid);
            } else {
                if (routeid != null) {
                    group = travelGroupRepository.findByRouteidAndEndDateGreaterThan(routeid, now);
                } else {
                    if (name != null) {
                        group = travelGroupRepository.findByRouteidAndEndDateGreaterThan(
                                travelRouteRepository.findByName(name).getRouteid(), now);
                    } else {
                        response.setStatus(Status.PARAM_ERROR);
                    }
                }
            }

            if (group != null) {
                response.setGroup(group);
                response.setStatus(Status.OK);
            } else {
                response.setStatus(Status.PARAM_ERROR);
                response.addError(new Error());
            }
        } catch (Exception e) {
            LOG.error("find group failed", e);
            response.setStatus(Status.DB_ERROR);
        }

    }

}
