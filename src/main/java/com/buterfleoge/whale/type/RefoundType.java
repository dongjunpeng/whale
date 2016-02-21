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
     * 个人原因
     */
    PERSONAL(0),

    /**
     * 形成取消
     */
    CANCELlED(1),

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
