package com.buterfleoge.whale.type.protocol;

import com.buterfleoge.whale.BaseObject;

/**
 * base request
 *
 * @author xiezhenzong
 *
 */
public class Request extends BaseObject {

    private String logid;
    private String globalid;

    /**
     * @return the logid
     */
    public String getLogid() {
        return logid;
    }

    /**
     * @param logid the logid to set
     */
    public void setLogid(String logid) {
        this.logid = logid;
    }

    /**
     * @return the globalid
     */
    public String getGlobalid() {
        return globalid;
    }

    /**
     * @param globalid the globalid to set
     */
    public void setGlobalid(String globalid) {
        this.globalid = globalid;
    }

}
