package com.qman.web.orm.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.SplittableRandom;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.qman.web.constant.biz.CommonConstant;

@Entity
@Table(name = "user_role")
@Embeddable
public class UserRolePo implements Serializable {

    private static final long serialVersionUID = -5335986510520956500L;

    @Id
    @JsonProperty(value = "id")
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @JsonProperty(value = "user_id")
    @Column(name = "user_id", nullable = false)
    private long userId;

    @JsonProperty(value = "role_id")
    @Column(name = "role_id", nullable = false)
    private long roleId;

    @JsonProperty(value = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @JsonProperty(value = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    @PreUpdate
    private void onPreUpdate() {
        this.updatedAt = new Date();
    }

    @PrePersist
    public void onPrePersist() {
        this.id = new SplittableRandom().nextLong(CommonConstant.TRILLION);
        this.createdAt = new Date();
        this.updatedAt = this.createdAt;
    }

    public UserRolePo() {
        super();
    }

    public UserRolePo(Long id, Long userId, Long roleId, Date createdAt, Date updatedAt) {
        super();
        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UserRolePo(Long userId, Long roleId) {
        super();
        this.userId = userId;
        this.roleId = roleId;
    }

    public Long getId() { return id; }

    public Long getUserId() { return userId; }

    public Long getRoleId() { return roleId; }

    public Date getCreatedAt() { return createdAt; }

    public Date getUpdatedAt() { return updatedAt; }

    public void setId(Long id) { this.id = id; }

    public void setUserId(Long userId) { this.userId = userId; }

    public void setRoleId(Long roleId) { this.roleId = roleId; }

    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }
}
