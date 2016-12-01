/**
 *
 */
package com.buterfleoge.whale.biz;

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
public interface TravelBiz {

    void getRoute(GetRouteRequest request, GetRouteResponse response) throws Exception;

    void getGroups(GetGroupRequest request, GetGroupResponse response) throws Exception;

    boolean isGroupAvailable(Long groupid, Request request, Response response);

    boolean isGroupAvailable(Long groupid, int count, Request request, Response response);

}
