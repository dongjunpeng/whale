/**
 *
 */
package com.buterfleoge.whale.type.protocol.order;

import com.buterfleoge.whale.type.protocol.Response;

/**
 * @author xiezhenzong
 *
 */
public class PayOrderByAlipayResponse extends Response {

    private String alipayFrom;

    /**
     * @return the alipayFrom
     */
    public String getAlipayFrom() {
        return alipayFrom;
    }

    /**
     * @param alipayFrom
     *            the alipayFrom to set
     */
    public void setAlipayFrom(String alipayFrom) {
        this.alipayFrom = alipayFrom;
    }

}
