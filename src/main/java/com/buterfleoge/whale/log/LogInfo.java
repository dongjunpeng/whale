package com.buterfleoge.whale.log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 需要记录到日志里的信息
 *
 * @author xiezhenzong
 *
 */
public class LogInfo {

    public String majorTag;
    public String minorTag;
    public long accountid;
    public Object thisObject;
    public Object[] request;
    public Object response;
    public long startTime;
    public long endTime;
    public long timeCost;
    public int status;

    protected String createDefaultInvokeLog() {
        StringBuilder sb = new StringBuilder();
        sb.append("[MAJOR=").append(majorTag);
        sb.append("][MINOR=").append(minorTag);
        sb.append("][ACCOUNTID=").append(accountid);
        sb.append("][REQUEST=").append(getRequestJson(request));
        sb.append("][RESPONSE=").append(toJson(response));
        sb.append("][START=").append(startTime);
        sb.append("][END=").append(endTime);
        sb.append("][TIMECOST=").append(timeCost);
        sb.append("][STATUS=").append(status).append("]");
        return sb.toString();
    }


    /**
     * 
     * 返回invoke日志中request的json串
     * 
     * @param args args
     * @return json
     */
    protected String getRequestJson(Object[] args) {
        List<Object> argsList = new ArrayList<Object>(Arrays.asList(args));
        if (argsList.size() > 0 && (argsList.get(0) instanceof Long)) { // remove userid if exist
            argsList.remove(0);
        }
        if (argsList.size() == 1) {
            return toJson(argsList.get(0));
        } else {
            return toJson(argsList.toArray());
        }
    }

    private String toJson(Object obj) {
        ObjectMapper om = new ObjectMapper();
        try {
            return om.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return "object to json error";
        }
    }

}
