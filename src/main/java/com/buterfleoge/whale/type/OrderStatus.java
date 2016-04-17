package com.buterfleoge.whale.type;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 订单状态，几种流程：有可能需要更加细化一点
 * 
 * <ol>
 * <li>CREATE -> CANCEL
 * <li>CREATE -> PAID -> FINISH
 * <li>CREATE -> PAID -> REFOUNDING -> REFOUNDED
 * <li>
 * </ol>
 * 
 * @author Brent24
 *
 */
public enum OrderStatus {

    /**
     * 生成
     */
    CREATE(0),

    /**
     * 已付款
     */
    PAID(1),

    /**
     * 结束
     */
    FINISH(2),

    /**
     * 退款中，表示之前要付款，现在取消了
     */
    REFOUNDING(3),

    /**
     * 退款成功
     */
    REFOUNDED(4),

    /**
     * 取消
     */
    CANCEL(5)

    ;

    private int status;

    private OrderStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
