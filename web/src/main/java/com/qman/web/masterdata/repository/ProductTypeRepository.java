package com.qman.web.masterdata.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.qman.web.masterdata.entity.ProductTypePo;

public interface ProductTypeRepository extends CrudRepository<ProductTypePo, Long> {

    @Override
    List<ProductTypePo> findAll();

    ProductTypePo getById(Long id);

    ProductTypePo getByCode(String code);
}
