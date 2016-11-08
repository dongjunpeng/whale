package com.buterfleoge.whale.type;

/**
 * @author xiezhenzong
 *
 */
public enum WxCode {

    SUCCESS(0, "SUCCESS"),

    FAIL(1, "FAIL");

    private int value;
    public String code;

    private WxCode(int  value, String code) {
        this.value = value;
        this.code = code;
    }

    public int getValue() {
        return value;
    }

    public static WxCode valueOf(int value) {
        return value == 0 ? SUCCESS : FAIL;
    }

    public static WxCode parse(String code) {
        return SUCCESS.code.equals(code) ? SUCCESS : FAIL;
    }

}
