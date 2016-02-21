package com.buterfleoge.whale.type.protocol;

import com.buterfleoge.whale.BaseObject;

/**
 * 
 * error object
 * 
 * @author xiezhenzong
 *
 */
public class Error extends BaseObject {

    private int code;
    private String message;

    /**
     * 
     */
    public Error() {
    }

    /**
     * @param code
     */
    public Error(int code) {
        this.code = code;
    }

    /**
     * @param message
     */
    public Error(String message) {
        this.message = message;
    }

    /**
     * @param code
     * @param message
     */
    public Error(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
