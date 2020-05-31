package com.qman.web.masterdata.entity;

import static javax.persistence.GenerationType.IDENTITY;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "md_category")
@Embeddable
public class CategoryPo implements Serializable {

    private static final long serialVersionUID = -1753363520930895514L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @JsonProperty(value = "id")
    @Column(name = "id", unique = true, nullable = false, insertable = false, updatable = false)
    private Integer id;

    @JsonProperty(value = "name")
    @Column(name = "name")
    private String name;

    @JsonProperty(value = "vi_name")
    @Column(name = "vi_name")
    private String viName;

    @JsonProperty(value = "parent_category_id")
    @Column(name = "parent_category_id")
    private Integer parentCategoryId;

    @JsonProperty(value = "description")
    @Column(name = "description")
    private String description;

    @JsonProperty(value = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @JsonProperty(value = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    @PrePersist
    private void onPrePersist() {
        this.createdAt = new Date();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    private void onPreUpdate() {
        this.updatedAt = new Date();
    }

    public CategoryPo() {
        super();
    }

    public CategoryPo(Integer categoryId, String name, String viName, Integer parentCategoryId, String description, Date createdAt, Date updatedAt) {
        this.id = categoryId;
        this.name = name;
        this.viName = viName;
        this.parentCategoryId = parentCategoryId;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static CategoryPo buildForInsert(String name, String viName, Integer parentCategoryId, String description) {
        return new CategoryPo(name, viName, parentCategoryId, description);
    }

    private CategoryPo(String name, String viName, Integer parentCategoryId, String description) {
        this.name = name;
        this.viName = viName;
        this.parentCategoryId = parentCategoryId;
        this.description = description;
    }

    public Integer getId() { return id; }

    public void setId(Integer categoryId) { this.id = categoryId; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getViName() { return viName; }

    public void setViName(String viName) { this.viName = viName; }

    public Integer getParentCategoryId() { return parentCategoryId; }

    public void setParentCategoryId(Integer parentCategoryId) { this.parentCategoryId = parentCategoryId; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public Date getCreatedAt() { return createdAt; }

    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public Date getUpdatedAt() { return updatedAt; }

    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }
}
