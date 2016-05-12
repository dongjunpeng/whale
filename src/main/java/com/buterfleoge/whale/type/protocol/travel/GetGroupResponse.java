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

    private List<TravelGroup> groups;

    public List<TravelGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<TravelGroup> groups) {
        this.groups = groups;
    }

}
