package com.buterfleoge.whale.type.protocol;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.buterfleoge.whale.BaseObject;

/**
 * base request
 *
 * @author xiezhenzong
 *
 */
public class Request<T> extends BaseObject {

    @NotBlank
    private String logid;

    private String globalid;

    @NotEmpty
    @Valid
    private List<T> data;

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

    /**
     * @return the data
     */
    public List<T> getData() {
        return data;
    }

    public T getFirstDataItem() {
        return data.get(0);
    }

    /**
     * @param data the data to set
     */
    public void setData(List<T> data) {
        this.data = data;
    }

}
