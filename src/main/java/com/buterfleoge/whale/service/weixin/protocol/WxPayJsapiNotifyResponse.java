package com.buterfleoge.whale.service.weixin.protocol;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.CDATAAdapter;

/**
 * @author xiezhenzong
 *
 */
@XmlRootElement(name = "xml")
public class WxPayJsapiNotifyResponse extends BaseObject {

    private String return_code;

    private String return_msg;

    /**
     * @return the return_code
     */
    @XmlJavaTypeAdapter(CDATAAdapter.class)
    public String getReturn_code() {
        return return_code;
    }

    /**
     * @param return_code
     *            the return_code to set
     */
    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    /**
     * @return the return_msg
     */
    @XmlJavaTypeAdapter(CDATAAdapter.class)
    public String getReturn_msg() {
        return return_msg;
    }

    /**
     * @param return_msg
     *            the return_msg to set
     */
    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

}
