/**
 * 
 */
package com.buterfleoge.whale.type.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.type.TravellerChannel;

/**
 * 发团价目表
 * 
 * @author Brent24
 *
 */
@Entity
@Table(name = "group_price")
public class GroupPrice extends BaseObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "priceid")
    private long priceid;

    @Column(name = "groupid")
    private long groupid;

    @Column(name = "start_place")
    private String startPlace = "";

    @Column(name = "end_place")
    private String endPlace = "";

    @Column(name = "channel")
    private TravellerChannel channel = TravellerChannel.UNKNOW;

    // 单位为分，数据库存取需要转换
    @Column(name = "price")
    private long price = 0;

    public long getPriceid() {
        return priceid;
    }

    public void setPriceid(long priceid) {
        this.priceid = priceid;
    }

    public long getGroupid() {
        return groupid;
    }

    public void setGroupid(long groupid) {
        this.groupid = groupid;
    }

    public String getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(String startPlace) {
        this.startPlace = startPlace;
    }

    public String getEndPlace() {
        return endPlace;
    }

    public void setEndPlace(String endPlace) {
        this.endPlace = endPlace;
    }

    public TravellerChannel getChannel() {
        return channel;
    }

    public void setChannel(TravellerChannel channel) {
        this.channel = channel;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

}
