package com.qman.web.module.producttype.model;

public class ProductTypeModel {

    private Long id;

    private String name, desc, code;

    public ProductTypeModel() {}

    public ProductTypeModel(String name, String desc, String code) {
        super();
        this.name = name;
        this.desc = desc;
        this.code = code;
    }

    public Long getId() { return id; }

    public String getName() { return name; }

    public String getDesc() { return desc; }

    public String getCode() { return code; }

    public void setId(Long id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setDesc(String desc) { this.desc = desc; }

    public void setCode(String code) { this.code = code; }
}