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
 * 大巴表
 * 
 * @author Brent24
 *
 */

@Entity
@Table(name = "group_bus")
public class GroupBus extends BaseObject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "busid")
	private long busid;

	@Column(name = "groupid")
	private long groupid;

	@Column(name = "plate_number")
	private String plateNumber = "";

	@Column(name = "manager")
	private String manager = "";

	@Column(name = "manager_mobile")
	private String managerMobile = "";

	@Column(name = "driver")
	private String driver = "";

	@Column(name = "driver_mobile")
	private String driverMobile = "";

	@Column(name = "assistant")
	private String assistant = "";

	@Column(name = "assistant_mobile")
	private String assistantMobile = "";

	// 单位为分，数据库存取需要转换
	@Column(name = "daily_rent")
	private long dailyRent = 0;

	@Column(name = "days")
	private int days = 0;

	// 单位为分，数据库存取需要转换
	@Column(name = "gas")
	private long gas = 0;

	// 单位为分，数据库存取需要转换
	@Column(name = "road_toll")
	private long roadToll = 0;

	// 单位为分，数据库存取需要转换
	@Column(name = "food")
	private long food = 0;

	// 单位为分，数据库存取需要转换
	@Column(name = "room")
	private long room = 0;

	// 单位为分，数据库存取需要转换
	@Column(name = "other")
	private long other = 0;

	// 单位为分，数据库存取需要转换
	@Column(name = "total")
	private long total = 0;

	@Column(name = "remark")
	private String remark = "";

	public long getBusid() {
		return busid;
	}

	public void setBusid(long busid) {
		this.busid = busid;
	}

	public long getGroupid() {
		return groupid;
	}

	public void setGroupid(long groupid) {
		this.groupid = groupid;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getManagerMobile() {
		return managerMobile;
	}

	public void setManagerMobile(String managerMobile) {
		this.managerMobile = managerMobile;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getDriverMobile() {
		return driverMobile;
	}

	public void setDriverMobile(String driverMobile) {
		this.driverMobile = driverMobile;
	}

	public String getAssistant() {
		return assistant;
	}

	public void setAssistant(String assistant) {
		this.assistant = assistant;
	}

	public String getAssistantMobile() {
		return assistantMobile;
	}

	public void setAssistantMobile(String assistantMobile) {
		this.assistantMobile = assistantMobile;
	}

	public long getDailyRent() {
		return dailyRent;
	}

	public void setDailyRent(long dailyRent) {
		this.dailyRent = dailyRent;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public long getGas() {
		return gas;
	}

	public void setGas(long gas) {
		this.gas = gas;
	}

	public long getRoadToll() {
		return roadToll;
	}

	public void setRoadToll(long roadToll) {
		this.roadToll = roadToll;
	}

	public long getFood() {
		return food;
	}

	public void setFood(long food) {
		this.food = food;
	}

	public long getRoom() {
		return room;
	}

	public void setRoom(long room) {
		this.room = room;
	}

	public long getOther() {
		return other;
	}

	public void setOther(long other) {
		this.other = other;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
