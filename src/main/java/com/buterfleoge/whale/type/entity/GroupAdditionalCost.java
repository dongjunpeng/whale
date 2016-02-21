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

/**
 * 其他支出表
 * 
 * @author Brent24
 *
 */

@Entity
@Table(name = "group_additional_cost")
public class GroupAdditionalCost extends BaseObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "costid")
    private long costid;

    @Column(name = "groupid")
    private long groupid;

    @Column(name = "item")
    private String item = "";

    @Column(name = "remark")
    private String remark = "";

    // 单位为分，数据库存取需要转换
    @Column(name = "total")
    private long total = 0;

    public long getCostid() {
        return costid;
    }

    public void setCostid(long costid) {
        this.costid = costid;
    }

    public long getGroupid() {
        return groupid;
    }

    public void setGroupid(long groupid) {
        this.groupid = groupid;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

}
