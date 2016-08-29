package com.buterfleoge.whale.type.protocol.order;

import org.hibernate.validator.constraints.NotEmpty;

import com.buterfleoge.whale.type.protocol.Request;

/**
 * @author Brent24
 *
 */
public class ValidateCodeRequest extends Request {

    @NotEmpty(message = "")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
