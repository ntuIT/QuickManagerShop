package com.qman.web.masterdata.service;

import java.util.List;

import com.qman.web.masterdata.entity.ProductTypePo;

public interface ProductTypeService {

    ProductTypePo save(ProductTypePo po);

    void delete(ProductTypePo po);

    List<ProductTypePo> getAll();

    ProductTypePo getById(Long id);

    ProductTypePo getByCode(String code);
}
