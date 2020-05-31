package com.qman.web.masterdata.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qman.web.masterdata.entity.ProductTypePo;
import com.qman.web.masterdata.repository.ProductTypeRepository;
import com.qman.web.masterdata.service.ProductTypeService;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    ProductTypeRepository productTypeRepository;

    @Override
    public ProductTypePo save(ProductTypePo po) {
        return this.productTypeRepository.save(po);
    }

    @Override
    public void delete(ProductTypePo po) {
        this.productTypeRepository.delete(po);
    }

    @Override
    public List<ProductTypePo> getAll() { return this.productTypeRepository.findAll(); }

    @Override
    public ProductTypePo getById(Long id) {
        return this.productTypeRepository.getById(id);
    }

    @Override
    public ProductTypePo getByCode(String code) {
        return this.productTypeRepository.getByCode(code);
    }
}
