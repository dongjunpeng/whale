/**
 * 
 */
package com.buterfleoge.whale.type;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 领队角色
 * 
 * @author Brent24
 *
 */
public enum LeaderRole {

    /**
     * 主领队
     */
    LEADER(0),

    /**
     * 副领队
     */
    ASSISTANT(1),

    /**
     * 实习领队
     */
    INTERNSHIP(2),

    /**
     * 摄影师
     */
    PHOTOGRAPHER(3),

    /**
     * 亲友
     */
    FRIEND(4),;

    private int role;

    private LeaderRole(int role) {
        this.role = role;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
