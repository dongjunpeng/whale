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
import com.buterfleoge.whale.type.entity.converter.DateTimeConverter;

/**
 * 集合信息
 *
 * @author Brent24
 *
 */

@Entity
@Table(name = "assembly_info")
public class AssemblyInfo extends BaseObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "assemblyid")
    private Long assemblyid;

    @Column(name = "groupid")
    private Long groupid;

    @Column(name = "ready")
    private Boolean ready;

    @Column(name = "leader")
    private String leader;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "hotel")
    private String hotel;

    @Column(name = "address")
    private String address;

    @Column(name = "wx_qrcode")
    private String wx_qrcode;

    @Column(name = "file")
    private String file;

    @DateTimeFormat(pattern = Pattern.DATE)
    @Column(name = "add_time")
    @Convert(converter = DateTimeConverter.class)
    private Date addTime;

    @DateTimeFormat(pattern = Pattern.DATE)
    @Column(name = "mod_time")
    @Convert(converter = DateTimeConverter.class)
    private Date modTime;

    public Long getAssemblyid() {
        return assemblyid;
    }

    public void setAssemblyid(Long assemblyid) {
        this.assemblyid = assemblyid;
    }

    public Long getGroupid() {
        return groupid;
    }

    public void setGroupid(Long groupid) {
        this.groupid = groupid;
    }

    public Boolean getReady() {
        return ready;
    }

    public void setReady(Boolean ready) {
        this.ready = ready;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWx_qrcode() {
        return wx_qrcode;
    }

    public void setWx_qrcode(String wx_qrcode) {
        this.wx_qrcode = wx_qrcode;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getModTime() {
        return modTime;
    }

    public void setModTime(Date modTime) {
        this.modTime = modTime;
    }

}
