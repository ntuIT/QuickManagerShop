package com.qman.web.module.login.model;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.qman.web.masterdata.entity.RolePo;

public class SessionData implements Serializable {

    private static final long serialVersionUID = 8493696911606279466L;

    private String username;

    private Long userId;

    private String fullName;

    private String avatarUrl;

    private List<RolePo> roleList;

    private List<String> roleCodeList;

    public SessionData() {
        super();
    }

    public SessionData(String username, long userId, String fullName, String avatarUrl, List<RolePo> roleList) {
        super();
        this.username = username;
        this.userId = userId;
        this.fullName = fullName;
        this.avatarUrl = avatarUrl;
        this.roleList = roleList;
        this.roleCodeList = roleList.stream().map(item -> item.getCode()).collect(Collectors.toList());
    }

    public Long getUserId() { return userId; }

    public String getUsername() { return username; }

    public String getFullName() { return fullName; }

    public String getAvatarUrl() { return avatarUrl; }

    public List<RolePo> getRoleList() { return roleList; }

    public List<String> getRoleCodeList() { return roleCodeList; }

    public void setUserId(long userId) { this.userId = userId; }

    public void setUsername(String username) { this.username = username; }

    public void setFullName(String fullName) { this.fullName = fullName; }

    public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }

    public void setRoleList(List<RolePo> roleList) {
        this.roleList = roleList;
        this.roleCodeList = roleList.stream().map(item -> item.getCode()).collect(Collectors.toList());
    }

    public void setRoleCodeList(List<String> roleCodeList) { this.roleCodeList = roleCodeList; }
}
