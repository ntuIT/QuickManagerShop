package com.qman.web.masterdata.repository;

import org.springframework.data.repository.CrudRepository;

import com.qman.web.masterdata.entity.RolePo;

public interface RoleRepository extends CrudRepository<RolePo, Long> {

    @Override
    Iterable<RolePo> findAll();

    RoleRepository getById(Long id);

    RoleRepository getByName(String name);

    RoleRepository getByCode(String code);
}
