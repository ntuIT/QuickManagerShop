package com.qman.web.masterdata.service;

import java.util.List;

import com.qman.web.masterdata.entity.RolePo;
import com.qman.web.masterdata.repository.RoleRepository;

public interface RoleService {

    List<RolePo> getAll();

    RoleRepository getById(Long id);

    RoleRepository getByName(String name);

    RoleRepository getByCode(String code);
}
