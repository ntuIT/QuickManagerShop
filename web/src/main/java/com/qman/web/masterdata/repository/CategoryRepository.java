package com.qman.web.masterdata.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.qman.web.masterdata.entity.CategoryPo;

import java.util.List;

public interface CategoryRepository extends CrudRepository<CategoryPo, Integer> {

    @Override
    List<CategoryPo> findAll();

    CategoryPo getById(Integer id);

    CategoryPo getByName(String name);

    CategoryPo getByViName(String viName);

    @Query(value = "SELECT c FROM CategoryPo c WHERE name LIKE :startName%")
    Iterable<CategoryPo> searchStartWithByName(@Param("startName") String startName);

    @Query(value = "SELECT c FROM CategoryPo c WHERE vi_name LIKE :startViName%")
    Iterable<CategoryPo> searchStartWithByViName(@Param("startViName") String startViName);
}
