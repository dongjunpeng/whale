package com.buterfleoge.whale.type.protocol.order;

import java.math.BigDecimal;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.buterfleoge.whale.type.protocol.Response;

/**
 * @author Brent24
 *
 */
public class ValidateCodeResponse extends Response {

    private String message;

    @NumberFormat(style = Style.CURRENCY)
    private BigDecimal value;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

}
