package com.buterfleoge.whale.biz.travel.impl;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
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
import com.buterfleoge.whale.biz.travel.TravelBiz;
import com.buterfleoge.whale.dao.TravelGroupRepository;
import com.buterfleoge.whale.dao.TravelRouteRepository;
import com.buterfleoge.whale.type.entity.TravelGroup;
import com.buterfleoge.whale.type.entity.TravelRoute;
import com.buterfleoge.whale.type.protocol.Error;
import com.buterfleoge.whale.type.protocol.Request;
import com.buterfleoge.whale.type.protocol.Response;
import com.buterfleoge.whale.type.protocol.travel.GetGroupRequest;
import com.buterfleoge.whale.type.protocol.travel.GetGroupResponse;
import com.buterfleoge.whale.type.protocol.travel.GetQuotaRequest;
import com.buterfleoge.whale.type.protocol.travel.GetQuotaResponse;
import com.buterfleoge.whale.type.protocol.travel.GetRouteRequest;
import com.buterfleoge.whale.type.protocol.travel.GetRouteResponse;
import com.buterfleoge.whale.type.protocol.travel.imgtext.Imgtext;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Brent24
 *
 */
@Service("travelBiz")
public class TravelBizImpl implements TravelBiz {

    private static final Logger LOG = LoggerFactory.getLogger(TravelBizImpl.class);

    @Value("${route.imgtext.path}")
    private String routeImgtextPath;

    @Autowired
    private TravelRouteRepository travelRouteRepository;

    @Autowired
    private TravelGroupRepository travelGroupRepository;

    @Override
    public void getRoute(GetRouteRequest request, GetRouteResponse response) throws Exception {
        List<Long> routeids = request.getRouteids();
        String name = request.getName();
        Boolean isImgtextRequired = request.getIsImgtextRequired();

        List<TravelRoute> routes = null;
        Imgtext imgtext = null;
        try {
            if (CollectionUtils.isEmpty(routeids)) {
                if (StringUtils.hasText(name)) {
                    TravelRoute route = travelRouteRepository.findByNameAndVisibleTrue(name);
                    routes = route == null ? null : Arrays.asList(route);
                } else {
                    routes = travelRouteRepository.findByVisibleTrue();
                }
            } else {
                if (routeids.size() == 1) {
                    TravelRoute route = travelRouteRepository.findByRouteidAndVisibleTrue(routeids.get(0));
                    routes = route == null ? null : Arrays.asList(route);
                } else {
                    routes = travelRouteRepository.findByRouteidInAndVisibleTrue(new HashSet<Long>(routeids));
                }
            }
            if (CollectionUtils.isEmpty(routes)) {
                return;
            }
        } catch (Exception e) {
            LOG.error("find route failed, reqid: " + request.getReqid(), e);
            response.setStatus(Status.DB_ERROR);
        }
        try {
            if (isImgtextRequired) {
                imgtext = getImgtextInJson(routes.get(0).getImgtext());
            }
        } catch (Exception e) {
            LOG.error("get imgtext failed, reqid: " + request.getReqid(), e);
            response.setStatus(Status.PARTLY_OK);
        }
        response.setRoutes(routes);
        response.setImgtext(imgtext);
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
                        DateUtils.addWeeks(new Date(), -1).getTime());
                response.setGroups(groups);
                return;
            }
            if (StringUtils.hasText(name)) {
                TravelRoute route = travelRouteRepository.findByName(name);
                if (route != null) {
                    groups = travelGroupRepository.findByRouteidAndEndDateGreaterThanOrderByStartDateAsc(
                            route.getRouteid(), DateUtils.addWeeks(new Date(), -1).getTime());
                    response.setGroups(groups);
                }
            }
        } catch (Exception e) {
            LOG.error("find group failed, reqid: " + request.getReqid(), e);
            response.setStatus(Status.DB_ERROR);
        }
    }

    @Override
    public void getQuota(GetQuotaRequest request, GetQuotaResponse response) {
        try {
            TravelGroup group = travelGroupRepository.findOne(request.getGroupid());
            if (group != null) {
                response.setQuota(group.getMaxCount() - group.getActualCount());
            } else {
                response.setStatus(Status.BIZ_ERROR);
                response.addError(new Error(BizCode.GROUP_NOT_EXIST, ErrorMsg.GROUP_NOT_EXIST));
            }
        } catch (Exception e) {
            LOG.error("get group's quota failed, reqid: " + request.getReqid(), e);
            response.setStatus(Status.DB_ERROR);
        }
    }

    @Override
    public int getQuota(Long groupid, Request request, Response response) {
        GetQuotaRequest getQuotaRequest = new GetQuotaRequest();
        GetQuotaResponse getQuotaResponse = new GetQuotaResponse();
        getQuotaRequest.setGroupid(groupid);
        getQuotaRequest.setReqid(request.getReqid());

        getQuota(getQuotaRequest, getQuotaResponse);

        response.setStatus(getQuotaResponse.getStatus());
        response.getErrors().addAll(getQuotaResponse.getErrors());
        
        return getQuotaResponse.getQuota();
    }

    private Imgtext getImgtextInJson(String jsonName) throws JsonParseException, JsonMappingException, IOException {
        File file = new File(routeImgtextPath + jsonName);
        if (file.exists()) {
            String content = FileUtils.readFileToString(file, "UTF-8");
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(content, Imgtext.class);
        } else {
            throw new RuntimeException("can't find json file, " + routeImgtextPath + jsonName);
        }
    }

}
