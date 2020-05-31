package com.qman.web.masterdata.service;

import com.qman.web.masterdata.entity.CategoryPo;

import java.util.List;

public interface CategoryService {

    CategoryPo save(CategoryPo po);

    List<CategoryPo> getAll();

    CategoryPo getById(Integer id);

    CategoryPo getByName(String name);

    CategoryPo getByViName(String viName);

    Iterable<CategoryPo> searchStartWithByName(String startName);

    Iterable<CategoryPo> searchStartWithByViName(String startViName);
}
