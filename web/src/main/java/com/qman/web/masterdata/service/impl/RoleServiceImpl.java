package com.qman.web.masterdata.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qman.web.masterdata.entity.RolePo;
import com.qman.web.masterdata.repository.RoleRepository;
import com.qman.web.masterdata.service.RoleService;
import com.qman.web.utility.StreamUtils;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository repository;

    @Override
    public List<RolePo> getAll() { return StreamUtils.toList(repository.findAll()); }

    @Override
    public RoleRepository getById(Long id) {
        return repository.getById(id);
    }

    @Override
    public RoleRepository getByName(String name) {
        return repository.getByName(name);
    }

    @Override
    public RoleRepository getByCode(String code) {
        return repository.getByCode(code);
    }
}
