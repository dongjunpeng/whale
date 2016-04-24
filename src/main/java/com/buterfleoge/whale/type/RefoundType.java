/**
 * 
 */
package com.buterfleoge.whale.type;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 退款类型
 * 
 * @author Brent24
 *
 */
public enum RefoundType {

    /**
     * 全额退款
     */
    FULL(0),

    /**
     * 20天以上退还95%
     */
    PERCENTAGE_95(1),

    /**
     * 20天-7天退还80
     */
    PERCENTAGE_80(2),
    
    /**
     * 7天退还50
     */
    PERCENTAGE_50(3),
    
    /**
     * 未到达不退
     */
    NONE(4)

    ;

    private int type;

    private RefoundType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
