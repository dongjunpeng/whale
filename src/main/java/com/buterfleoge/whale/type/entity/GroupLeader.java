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
import com.buterfleoge.whale.type.LeaderRole;

/**
 * 发团领队表
 * 
 * @author Brent24
 *
 */

@Entity
@Table(name = "group_leader")
public class GroupLeader extends BaseObject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "busid")
	private long busid;

	@Column(name = "groupid")
	private long groupid;

	@Column(name = "leaderid")
	private long leaderid;

	@Column(name = "role")
	private LeaderRole role = LeaderRole.LEADER;

	// 单位为分，数据库存取需要转换
	@Column(name = "traffic")
	private long traffic = 0;

	// 单位为分，数据库存取需要转换
	@Column(name = "room")
	private long room = 0;

	// 单位为分，数据库存取需要转换
	@Column(name = "ticket")
	private long ticket = 0;

	// 单位为分，数据库存取需要转换
	@Column(name = "food")
	private long food = 0;

	// 单位为分，数据库存取需要转换
	@Column(name = "equipment")
	private long equipment = 0;

	// 单位为分，数据库存取需要转换
	@Column(name = "salary")
	private long salary = 0;

	// 单位为分，数据库存取需要转换
	@Column(name = "other")
	private long other = 0;

	// 单位为分，数据库存取需要转换
	@Column(name = "total")
	private long total = 0;

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

	public long getLeaderid() {
		return leaderid;
	}

	public void setLeaderid(long leaderid) {
		this.leaderid = leaderid;
	}

	public LeaderRole getRole() {
		return role;
	}

	public void setRole(LeaderRole role) {
		this.role = role;
	}

	public long getTraffic() {
		return traffic;
	}

	public void setTraffic(long traffic) {
		this.traffic = traffic;
	}

	public long getRoom() {
		return room;
	}

	public void setRoom(long room) {
		this.room = room;
	}

	public long getTicket() {
		return ticket;
	}

	public void setTicket(long ticket) {
		this.ticket = ticket;
	}

	public long getFood() {
		return food;
	}

	public void setFood(long food) {
		this.food = food;
	}

	public long getEquipment() {
		return equipment;
	}

	public void setEquipment(long equipment) {
		this.equipment = equipment;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
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

}
