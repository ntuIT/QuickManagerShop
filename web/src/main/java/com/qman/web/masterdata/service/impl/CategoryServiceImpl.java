package com.qman.web.masterdata.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qman.web.masterdata.entity.CategoryPo;
import com.qman.web.masterdata.repository.CategoryRepository;
import com.qman.web.masterdata.service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryPo save(CategoryPo po) {
        return this.categoryRepository.save(po);
    }

    @Override
    public List<CategoryPo> getAll() { return this.categoryRepository.findAll(); }

    @Override
    public CategoryPo getById(Integer id) {
        return this.categoryRepository.getById(id);
    }

    @Override
    public CategoryPo getByName(String name) {
        return this.categoryRepository.getByName(name);
    }

    @Override
    public CategoryPo getByViName(String viName) {
        return this.categoryRepository.getByViName(viName);
    }

    @Override
    public Iterable<CategoryPo> searchStartWithByName(String startName) {
        return this.categoryRepository.searchStartWithByName(startName);
    }

    @Override
    public Iterable<CategoryPo> searchStartWithByViName(String startViName) {
        return this.categoryRepository.searchStartWithByViName(startViName);
    }
}
