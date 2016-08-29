package com.buterfleoge.whale.service.alipay.protocol;

/**
 * @author xiezhenzong
 *
 */
public class AlipayReturnRequest extends AlipayCallbackRequest {

    public static final String SUCCESS_TAG = "T";

    /**
     * 接口调用是否成功
     */
    private String is_success;

    private String exterface;

    /**
     * @return the is_success
     */
    public String getIs_success() {
        return is_success;
    }

    /**
     * @param is_success
     *            the is_success to set
     */
    public void setIs_success(String is_success) {
        this.is_success = is_success;
    }

    /**
     * @return the exterface
     */
    public String getExterface() {
        return exterface;
    }

    /**
     * @param exterface
     *            the exterface to set
     */
    public void setExterface(String exterface) {
        this.exterface = exterface;
    }

}
