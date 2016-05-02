package com.buterfleoge.whale.type.protocol.order.object;

/**
 * @author Brent24
 *
 */
public class DiscountObject {

    private Long discountid;
    private String desc;
    private Long value;

    public DiscountObject(Long discountid, String desc, Long value) {
        this.discountid = discountid;
        this.desc = desc;
        this.value = value;
    }

    public Long getDiscountid() {
        return discountid;
    }

    public void setDiscountid(Long discountid) {
        this.discountid = discountid;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

}
