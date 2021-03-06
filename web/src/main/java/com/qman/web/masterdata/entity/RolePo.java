package com.qman.web.masterdata.entity;

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
@Table(name = "role")
@Embeddable
public class RolePo implements Serializable {

    private static final long serialVersionUID = -172820159762025948L;

    @Id
    @JsonProperty(value = "id")
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @JsonProperty(value = "name")
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @JsonProperty(value = "desc")
    @Column(name = "desc", nullable = false, length = 200)
    private String desc;

    @JsonProperty(value = "code")
    @Column(name = "code", nullable = false, length = 10)
    private String code;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinTable(name = "user_role", joinColumns = {
    // @JoinColumn(name = "role_id", insertable = false, updatable = false, referencedColumnName = "id")},
    // inverseJoinColumns = {
    // @JoinColumn(name = "user_id", insertable = false, updatable = false, referencedColumnName = "id")})
    // private UserPo user;
    @JsonProperty(value = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @JsonProperty(value = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    @PrePersist
    public void onPrePersist() {
        this.id = new SplittableRandom().nextLong(CommonConstant.TRILLION);
        this.createdAt = new Date();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    private void onPreUpdate() {
        this.updatedAt = new Date();
    }

    public RolePo() {
        super();
    }

    public Long getId() { return id; }

    public String getName() { return name; }

    public String getDesc() { return desc; }

    public String getCode() { return code; }

    public Date getCreatedAt() { return createdAt; }

    public Date getUpdatedAt() { return updatedAt; }

    public void setId(Long id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setDesc(String desc) { this.desc = desc; }

    public void setCode(String code) { this.code = code; }

    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }
}
