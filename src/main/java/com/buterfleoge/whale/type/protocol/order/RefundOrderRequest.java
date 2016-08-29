/**
 *
 */
package com.buterfleoge.whale.type.protocol.order;

/**
 * @author xiezhenzong
 *
 */
public class RefundOrderRequest extends OrderRequest {

    private String desc;

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc
     *            the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

}
