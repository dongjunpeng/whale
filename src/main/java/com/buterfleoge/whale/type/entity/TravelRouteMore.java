package com.buterfleoge.whale.type.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.type.entity.converter.ListConverter;
import com.buterfleoge.whale.type.formatter.ImagePathFormat;
import com.buterfleoge.whale.type.formatter.ImagePathFormat.Prefix;

/**
 * @author xiezhenzong
 *
 */
@Entity
@Table(name = "travel_route_more")
public class TravelRouteMore extends BaseObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "routeid")
    private Long routeid;

    @ImagePathFormat(prefix = Prefix.ROUTE, isComposite = true)
    @Column(name = "slider_imgs")
    @Convert(converter = ListConverter.class)
    private List<String> sliderImgs;

    @Column(name = "description")
    @Convert(converter = ListConverter.class)
    private List<String> desc;

    @Column(name = "local")
    private String local;

    @Column(name = "prepare")
    private String prepare;

    @Column(name = "traffic")
    private String traffic;

    @Column(name = "expense_include")
    private String expenseInclude;

    @Column(name = "expense_exclude")
    private String expenseExclude;

    @Column(name = "refund")
    private String refund;

    /**
     * @return the routeid
     */
    public Long getRouteid() {
        return routeid;
    }

    /**
     * @param routeid
     *            the routeid to set
     */
    public void setRouteid(Long routeid) {
        this.routeid = routeid;
    }

    /**
     * @return the sliderImgs
     */
    public List<String> getSliderImgs() {
        return sliderImgs;
    }

    /**
     * @param sliderImgs
     *            the sliderImgs to set
     */
    public void setSliderImgs(List<String> sliderImgs) {
        this.sliderImgs = sliderImgs;
    }

    /**
     * @return the desc
     */
    public List<String> getDesc() {
        return desc;
    }

    /**
     * @param desc
     *            the desc to set
     */
    public void setDesc(List<String> desc) {
        this.desc = desc;
    }

    /**
     * @return the local
     */
    public String getLocal() {
        return local;
    }

    /**
     * @param local
     *            the local to set
     */
    public void setLocal(String local) {
        this.local = local;
    }

    /**
     * @return the prepare
     */
    public String getPrepare() {
        return prepare;
    }

    /**
     * @param prepare
     *            the prepare to set
     */
    public void setPrepare(String prepare) {
        this.prepare = prepare;
    }

    /**
     * @return the traffic
     */
    public String getTraffic() {
        return traffic;
    }

    /**
     * @param traffic
     *            the traffic to set
     */
    public void setTraffic(String traffic) {
        this.traffic = traffic;
    }

    /**
     * @return the expenseInclude
     */
    public String getExpenseInclude() {
        return expenseInclude;
    }

    /**
     * @param expenseInclude
     *            the expenseInclude to set
     */
    public void setExpenseInclude(String expenseInclude) {
        this.expenseInclude = expenseInclude;
    }

    /**
     * @return the expenseExclude
     */
    public String getExpenseExclude() {
        return expenseExclude;
    }

    /**
     * @param expenseExclude
     *            the expenseExclude to set
     */
    public void setExpenseExclude(String expenseExclude) {
        this.expenseExclude = expenseExclude;
    }

    /**
     * @return the refund
     */
    public String getRefund() {
        return refund;
    }

    /**
     * @param refund
     *            the refund to set
     */
    public void setRefund(String refund) {
        this.refund = refund;
    }

    @Override
    public String toString() {
        return "{\"routeid\": " + getRouteid() + "}";
    }

}
