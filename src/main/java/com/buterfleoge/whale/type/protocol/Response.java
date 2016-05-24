package com.buterfleoge.whale.type.protocol;

import java.util.ArrayList;
import java.util.List;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.Constants.Status;

/**
 * base response
 * 
 * @author xiezhenzong
 *
 */
public class Response extends BaseObject {

    /**
     * 响应状态， 如果业务失败的话，则添加Error对象
     */
    private int status = Status.OK;
    private List<Error> errors = new ArrayList<Error>(3);

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

    /**
     * add error
     * 
     * @param error the error to add
     */
    public void addError(Error error) {
        errors.add(error);
    }

    /**
     * has any error in response
     * 
     * @return
     */
    public boolean hasError() {
        return status != Status.OK || errors.size() > 0;
    }

}
