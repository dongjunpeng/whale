/**
 *
 */
package com.buterfleoge.whale.type.protocol.order;

import java.util.Map;

import com.buterfleoge.whale.type.protocol.Response;

/**
 * @author xiezhenzong
 *
 */
public class PayOrderByAlipayResponse extends Response {

    private Map<String, String> alipayFrom;
    private Map<String, Object> wxJsapiModel;

    /**
     * @return the alipayFrom
     */
    public Map<String, String> getAlipayFrom() {
        return alipayFrom;
    }

    /**
     * @param alipayFrom
     *            the alipayFrom to set
     */
    public void setAlipayFrom(Map<String, String> alipayFrom) {
        this.alipayFrom = alipayFrom;
    }

    /**
     * @return the wxJsapiModel
     */
    public Map<String, Object> getWxJsapiModel() {
        return wxJsapiModel;
    }

    /**
     * @param wxJsapiModel
     *            the wxJsapiModel to set
     */
    public void setWxJsapiModel(Map<String, Object> wxJsapiModel) {
        this.wxJsapiModel = wxJsapiModel;
    }

}
