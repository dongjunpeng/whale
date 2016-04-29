package com.buterfleoge.whale.type.protocol.travel.imgtext;

import java.util.List;

/**
 * @author Brent24
 *
 */
public class Day {

	private String title;
	private List<String> imgs;
	private String mdtext;
	private String distance;
	private String altitude;
	private String hotel;
	private String food;
	private String star;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getImgs() {
		return imgs;
	}

	public void setImgs(List<String> imgs) {
		this.imgs = imgs;
	}

	public String getMdtext() {
		return mdtext;
	}

	public void setMdtext(String mdtext) {
		this.mdtext = mdtext;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getAltitude() {
		return altitude;
	}

	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}

	public String getHotel() {
		return hotel;
	}

	public void setHotel(String hotel) {
		this.hotel = hotel;
	}

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}
}
