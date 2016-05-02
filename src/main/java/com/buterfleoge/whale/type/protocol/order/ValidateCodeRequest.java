package com.buterfleoge.whale.type.protocol.order;

import com.buterfleoge.whale.type.protocol.Request;

/**
 * @author Brent24
 *
 */
public class ValidateCodeRequest extends Request {

    private Long code;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

}
