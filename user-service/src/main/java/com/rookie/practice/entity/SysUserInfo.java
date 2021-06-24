package com.rookie.practice.entity;


import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * @author niezhiqiang
 */
public class SysUserInfo implements Serializable {

    @ApiModelProperty("登录名")
    private String loginName;

    @ApiModelProperty("用户GUID")

    private String userGuid;

    @ApiModelProperty("角色ID")
    private long roleId;

    @ApiModelProperty("用户名")
    private String username;

    @Id
    private long id;

    public SysUserInfo(String loginName, String userGuid, long roleId, String username, long id) {
        this.loginName = loginName;
        this.userGuid = userGuid;
        this.roleId = roleId;
        this.username = username;
        this.id = id;
    }

    @Override
    public String toString() {
        return "SysUserInfo{" +
                "loginName='" + loginName + '\'' +
                ", userGuid='" + userGuid + '\'' +
                ", roleId=" + roleId +
                ", username='" + username + '\'' +
                ", id=" + id +
                '}';
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SysUserInfo() {
    }
}
