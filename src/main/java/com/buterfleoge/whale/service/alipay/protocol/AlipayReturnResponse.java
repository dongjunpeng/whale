package com.buterfleoge.whale.service.alipay.protocol;

import com.buterfleoge.whale.type.protocol.Response;

/**
 * @author xiezhenzong
 *
 */
public class AlipayReturnResponse extends Response {

    private String htmlContent;

    /**
     * @return the htmlContent
     */
    public String getHtmlContent() {
        return htmlContent;
    }

    /**
     * @param htmlContent
     *            the htmlContent to set
     */
    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

}
