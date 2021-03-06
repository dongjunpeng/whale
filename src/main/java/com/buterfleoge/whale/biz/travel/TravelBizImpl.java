package com.buterfleoge.whale.biz.travel;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.buterfleoge.whale.Constants.BizCode;
import com.buterfleoge.whale.Constants.ErrorMsg;
import com.buterfleoge.whale.Constants.Status;
import com.buterfleoge.whale.biz.TravelBiz;
import com.buterfleoge.whale.dao.TravelGroupRepository;
import com.buterfleoge.whale.dao.TravelRouteDaysRepository;
import com.buterfleoge.whale.dao.TravelRouteMoreRepository;
import com.buterfleoge.whale.dao.TravelRoutePcInfoRepository;
import com.buterfleoge.whale.dao.TravelRouteRepository;
import com.buterfleoge.whale.dao.TravelRouteWapInfoRepository;
import com.buterfleoge.whale.type.GroupStatus;
import com.buterfleoge.whale.type.entity.TravelGroup;
import com.buterfleoge.whale.type.entity.TravelRoute;
import com.buterfleoge.whale.type.protocol.Error;
import com.buterfleoge.whale.type.protocol.Request;
import com.buterfleoge.whale.type.protocol.Response;
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

    @Value("${route.mdtext.path}")
    private String mdtextPath;

    @Autowired
    private TravelRouteRepository travelRouteRepository;

    @Autowired
    private TravelRouteMoreRepository travelRouteMoreRepository;

    @Autowired
    private TravelRouteDaysRepository travelRouteDaysRepository;

    @Autowired
    private TravelRoutePcInfoRepository travelRoutePcInfoRepository;

    @Autowired
    private TravelRouteWapInfoRepository travelRouteWapInfoRepository;

    @Autowired
    private TravelGroupRepository travelGroupRepository;

    @Override
    public void getRoute(GetRouteRequest request, GetRouteResponse response) throws Exception {
        List<Long> routeids = request.getRouteids();

        List<TravelRoute> routes = Collections.<TravelRoute> emptyList();
        try {
            if (CollectionUtils.isEmpty(routeids)) {
                routes = travelRouteRepository.findByVisibleTrue();
            } else if (routeids.size() == 1) {
                TravelRoute route = travelRouteRepository.findByRouteidAndVisibleTrue(routeids.get(0));
                if (route != null) {
                    routes = Arrays.asList(route);
                }
            } else {
                routes = travelRouteRepository.findByRouteidInAndVisibleTrue(routeids);
            }
            if (routes.isEmpty()) {
                response.setStatus(Status.PARAM_ERROR);
            } else {
                response.setRoutes(routes);
            }
        } catch (Exception e) {
            LOG.error("find route failed, reqid: " + request.getReqid(), e);
            response.setStatus(Status.DB_ERROR);
        }
        try {
            if (routeids != null && routeids.size() == 1 && request.getIsImgtextRequired()) {
                Long routeid = routeids.get(0);
                response.setMore(travelRouteMoreRepository.findOne(routeid));
                response.setDays(travelRouteDaysRepository.findByRouteid(routeid));
                if (request.isFromWx()) {
                    response.setMdtext(getMdtext(routeids.get(0)));
                    response.setWapInfo(travelRouteWapInfoRepository.findOne(routeid));
                } else {
                    response.setPcInfo(travelRoutePcInfoRepository.findOne(routeid));

                    response.setMdtext(getMdtext(routeids.get(0)));
                    response.setWapInfo(travelRouteWapInfoRepository.findOne(routeid));
                }
            } else {
                Collections.sort(response.getRoutes(), TravelRoute.getComparator(request.isFromWx()));
            }
        } catch (Exception e) {
            LOG.error("get imgtext failed, reqid: " + request.getReqid(), e);
            response.setStatus(Status.SYSTEM_ERROR);
        }
    }

    @Override
    public void getGroups(GetGroupRequest request, GetGroupResponse response) throws Exception {
        Long routeid = request.getRouteid();
        Long groupid = request.getGroupid();
        String name = request.getName();

        List<TravelGroup> groups = null;
        try {
            if (groupid != null) {
                TravelGroup group = travelGroupRepository.findOne(groupid);
                groups = group == null ? null : Arrays.asList(group);
                response.setGroups(groups);
                return;
            }
            if (routeid != null) {
                groups = travelGroupRepository.findByRouteidAndEndDateGreaterThanOrderByStartDateAsc(routeid,
                        DateUtils.addWeeks(new Date(), -4));
                response.setGroups(groups);
                return;
            }
            if (StringUtils.hasText(name)) {
                TravelRoute route = travelRouteRepository.findByName(name);
                if (route != null) {
                    groups = travelGroupRepository.findByRouteidAndEndDateGreaterThanOrderByStartDateAsc(
                            route.getRouteid(), DateUtils.addWeeks(new Date(), -1));
                    response.setGroups(groups);
                }
            }
        } catch (Exception e) {
            LOG.error("find group failed, reqid: " + request.getReqid(), e);
            response.setStatus(Status.DB_ERROR);
        }
    }

    @Override
    public boolean isGroupAvailable(Long groupid, Request request, Response response) {
        TravelGroup travelGroup = getTravelGroup(groupid, request, response);
        return travelGroup != null && travelGroup.getStatus() == GroupStatus.OPEN.value;
    }

    @Override
    public boolean isGroupAvailable(Long groupid, int count, Request request, Response response) {
        boolean isAvailable = isGroupAvailable(groupid, request, response);
        return isAvailable && getQuota(groupid, request, response) >= count;
    }

    private int getQuota(Long groupid, Request request, Response response) {
        TravelGroup group = getTravelGroup(groupid, request, response);
        return group != null ? group.getMaxCount() - group.getActualCount() : -1;
    }

    private TravelGroup getTravelGroup(Long groupid, Request request, Response response) {
        try {
            TravelGroup group = travelGroupRepository.findOne(groupid);
            if (group == null) {
                response.setStatus(Status.BIZ_ERROR);
                response.addError(new Error(BizCode.GROUP_NOT_EXIST, ErrorMsg.GROUP_NOT_EXIST));
            }
            return group;
        } catch (Exception e) {
            LOG.error("get group's quota failed, reqid: " + request.getReqid(), e);
            response.setStatus(Status.DB_ERROR);
            return null;
        }
    }

    private String getMdtext(Long routeid) throws IOException {
        String filePath = routeid < 10 ? "p0" + routeid + ".md" : "p" + routeid + ".md";
        File file = new File(mdtextPath + filePath);
        if (file.exists()) {
            return FileUtils.readFileToString(file, "UTF-8");
        } else {
            throw new RuntimeException("can't find md file, " + mdtextPath + filePath);
        }
    }

}
