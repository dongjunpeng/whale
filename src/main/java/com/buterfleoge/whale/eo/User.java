package com.buterfleoge.whale.eo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.buterfleoge.whale.BaseObject;

/**
 * entity object for user table
 * 
 * @author dongjunpeng
 *
 */
@Entity
@Table(name="user")
public class User extends BaseObject implements Cloneable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userid;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="qq_openid")
    private String qqOpenid;
    
    @Column(name="weixin_openid")
    private String weixinOpenid;
    
    @Column(name="cellphone")
    private String cellphone;
    
    /**
     * @return the userid
     */
    public Long getUserid() {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(Long userid) {
        this.userid = userid;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

	public String getQqOpenid() {
		return qqOpenid;
	}

	public void setQqOpenid(String qqOpenid) {
		this.qqOpenid = qqOpenid;
	}

	public String getWeixinOpenid() {
		return weixinOpenid;
	}

	public void setWeixinOpenid(String weixinOpenid) {
		this.weixinOpenid = weixinOpenid;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}



	public User clone() throws CloneNotSupportedException {
		User user = (User) super.clone();
		return user;
	}
    
    
    
}
