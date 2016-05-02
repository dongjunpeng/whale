package com.buterfleoge.whale.type.protocol.order;

import com.buterfleoge.whale.type.protocol.Response;

/**
 * @author Brent24
 *
 */
public class ValidateCodeResponse extends Response {

    private String message;
    private Long value;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

}
