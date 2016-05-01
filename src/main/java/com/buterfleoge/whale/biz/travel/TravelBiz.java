/**
 * 
 */
package com.buterfleoge.whale.biz.travel;

import com.buterfleoge.whale.type.protocol.Request;
import com.buterfleoge.whale.type.protocol.travel.GetGroupRequest;
import com.buterfleoge.whale.type.protocol.travel.GetGroupResponse;
import com.buterfleoge.whale.type.protocol.travel.GetRouteRequest;
import com.buterfleoge.whale.type.protocol.travel.GetRouteResponse;

/**
 * @author Brent24
 *
 */
public interface TravelBiz {

    void getRoute(GetRouteRequest request, GetRouteResponse response) throws Exception;

    void getRouteByCondition(Request request, GetRouteResponse response) throws Exception;

    void getGroup(GetGroupRequest request, GetGroupResponse response) throws Exception;
}
