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
@Table(name = "product_type")
@Embeddable
public class ProductTypePo implements Serializable {

    private static final long serialVersionUID = 215948931693483175L;

    @Id
    @JsonProperty(value = "id")
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @JsonProperty(value = "name")
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @JsonProperty(value = "description")
    @Column(name = "description", nullable = false, length = 200)
    private String desc;

    @JsonProperty(value = "code")
    @Column(name = "code", nullable = false, length = 10)
    private String code;

    @JsonProperty(value = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @JsonProperty(value = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    @PrePersist
    public void prePersist() {
        this.id = new SplittableRandom().nextLong(CommonConstant.TRILLION);
        this.createdAt = new Date();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    private void onPreUpdate() {
        this.updatedAt = new Date();
    }

    public ProductTypePo() {
        super();
    }

    public ProductTypePo(Long id, String name, String description, String code, Date createdAt, Date updatedAt) {
        super();
        this.id = id;
        this.name = name;
        this.desc = description;
        this.code = code;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return desc; }

    public void setDescription(String description) { this.desc = description; }

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public Date getCreatedAt() { return createdAt; }

    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public Date getUpdatedAt() { return updatedAt; }

    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }
}
