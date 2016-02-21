package com.buterfleoge.whale.type.protocol;

import java.util.ArrayList;
import java.util.List;

import com.buterfleoge.whale.BaseObject;

/**
 * base response
 * 
 * @author xiezhenzong
 *
 */
public class Response<T> extends BaseObject {

    /**
     * 响应状态， 如果业务失败的话，则添加Error对象
     */
    private int status;
    private List<Error> errors = new ArrayList<Error>(3);
    private List<T> data = new ArrayList<T>(3);

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the errors
     */
    public List<Error> getErrors() {
        return errors;
    }

    /**
     * @param errors the errors to set
     */
    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public void addError(Error error) {
        errors.add(error);
    }

    public boolean hasError() {
        return errors.size() > 0;
    }

    /**
     * @return the data
     */
    public List<T> getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(List<T> data) {
        this.data = data;
    }

    public void addData(T item) {
        data.add(item);
    }

}
