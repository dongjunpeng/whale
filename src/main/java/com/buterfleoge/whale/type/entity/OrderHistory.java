package com.buterfleoge.whale.type.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.Constants.Pattern;
import com.buterfleoge.whale.type.OrderStatus;
import com.buterfleoge.whale.type.entity.converter.DateTimeConverter;

/**
 * @author xiezhenzong
 *
 */
@Entity
@Table(name = "order_history")
public class OrderHistory extends BaseObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "historyid")
    private Long historyid;

    @Column(name = "orderid")
    private Long orderid;

    /**
     * 包含OrderStatus的所有取值，表示记录订单的状态变更
     */
    @Column(name = "type")
    private Integer type;

    @Column(name = "attach")
    private String attach;

    @DateTimeFormat(pattern = Pattern.DATE)
    @Column(name = "add_time")
    @Convert(converter = DateTimeConverter.class)
    private Date add_time;

    public static OrderHistory newInstance(Integer oldOrderStatus, OrderInfo orderInfo) {
        return newInstance(OrderStatus.HELPER.valueOf(oldOrderStatus), orderInfo);
    }

    public static OrderHistory newInstance(OrderStatus oldOrderStatus, OrderInfo orderInfo) {
        return newInstance(orderInfo.getOrderid(), orderInfo.getStatus(),
                "订单状态变更： " + oldOrderStatus.desc + " -> " + OrderStatus.HELPER.valueOf(orderInfo.getStatus()).desc);
    }

    public static OrderHistory newInstance(Long orderid, OrderStatus type) {
        return newInstance(orderid, type.value, type.desc);
    }

    public static OrderHistory newInstance(Long orderid, Integer type, String attach) {
        OrderHistory history = new OrderHistory();
        history.setOrderid(orderid);
        history.setType(type);
        history.setAttach(attach);
        history.setAdd_time(new Date());
        return history;
    }

    /**
     * @return the historyid
     */
    public Long getHistoryid() {
        return historyid;
    }

    /**
     * @param historyid
     *            the historyid to set
     */
    public void setHistoryid(Long historyid) {
        this.historyid = historyid;
    }

    /**
     * @return the orderid
     */
    public Long getOrderid() {
        return orderid;
    }

    /**
     * @param orderid
     *            the orderid to set
     */
    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    /**
     * @return the type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return the attach
     */
    public String getAttach() {
        return attach;
    }

    /**
     * @param attach
     *            the attach to set
     */
    public void setAttach(String attach) {
        this.attach = attach;
    }

    /**
     * @return the add_time
     */
    public Date getAdd_time() {
        return add_time;
    }

    /**
     * @param add_time
     *            the add_time to set
     */
    public void setAdd_time(Date add_time) {
        this.add_time = add_time;
    }

}
