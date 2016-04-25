package com.buterfleoge.whale.type.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.buterfleoge.whale.BaseObject;
import com.buterfleoge.whale.type.TravelArea;
import com.buterfleoge.whale.type.TravelType;

/**
 * @author Brent24
 *
 */
@Entity
@Table(name = "travel_route")
public class TravelRoute extends BaseObject {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "routeid")
	private Long routeid;

	@Column(name = "name")
	private String name;

	@Column(name = "title")
	private String title;

	@Column(name = "visible")
	private Boolean visible;

	@Column(name = "type")
	private TravelType type;

	@Column(name = "days")
	private Integer days;

	@Column(name = "area")
	private TravelArea area;

	@Column(name = "departure")
	private String departure;

	@Column(name = "distination")
	private String distination;

	@Column(name = "route")
	private String route;

	@Column(name = "min_price")
	private Long minPrice;

	@Column(name = "max_price")
	private Long maxPrice;

	@Column(name = "desc")
	private String desc;

	@Column(name = "imgs")
	private String imgs;

	@Column(name = "wx_link")
	private String wxLink;

	@Column(name = "imgtext")
	private String imgtext;

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	/**
	 * @return the type
	 */
	public TravelType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(TravelType type) {
		this.type = type;
	}

	/**
	 * @return the days
	 */
	public Integer getDays() {
		return days;
	}

	/**
	 * @param days
	 *            the days to set
	 */
	public void setDays(Integer days) {
		this.days = days;
	}

	/**
	 * @return the area
	 */
	public TravelArea getArea() {
		return area;
	}

	/**
	 * @param area
	 *            the area to set
	 */
	public void setArea(TravelArea area) {
		this.area = area;
	}

	/**
	 * @return the departure
	 */
	public String getDeparture() {
		return departure;
	}

	/**
	 * @param departure
	 *            the departure to set
	 */
	public void setDeparture(String departure) {
		this.departure = departure;
	}

	/**
	 * @return the distination
	 */
	public String getDistination() {
		return distination;
	}

	/**
	 * @param distination
	 *            the distination to set
	 */
	public void setDistination(String distination) {
		this.distination = distination;
	}

	/**
	 * @return the route
	 */
	public String getRoute() {
		return route;
	}

	/**
	 * @param route
	 *            the route to set
	 */
	public void setRoute(String route) {
		this.route = route;
	}

	/**
	 * @return the minPrice
	 */
	public Long getMinPrice() {
		return minPrice;
	}

	/**
	 * @param minPrice
	 *            the minPrice to set
	 */
	public void setMinPrice(Long minPrice) {
		this.minPrice = minPrice;
	}

	/**
	 * @return the maxPrice
	 */
	public Long getMaxPrice() {
		return maxPrice;
	}

	/**
	 * @param maxPrice
	 *            the maxPrice to set
	 */
	public void setMaxPrice(Long maxPrice) {
		this.maxPrice = maxPrice;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

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

	/**
	 * @return the imgs
	 */
	public String getImgs() {
		return imgs;
	}

	/**
	 * @param imgs
	 *            the imgs to set
	 */
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}

	public String getWxLink() {
		return wxLink;
	}

	public void setWxLink(String wxLink) {
		this.wxLink = wxLink;
	}

	public String getImgtext() {
		return imgtext;
	}

	public void setImgtext(String imgtext) {
		this.imgtext = imgtext;
	}

}
