package com.qman.web.orm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qman.web.orm.entity.UserRolePo;
import com.qman.web.orm.repository.UserRoleRepository;
import com.qman.web.orm.service.UserRoleService;
import com.qman.web.utility.StreamUtils;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository repository;

    @Override
    public UserRolePo save(UserRolePo po) {
        return repository.save(po);
    }

    @Override
    public List<UserRolePo> saveAll(List<UserRolePo> po) {
        return StreamUtils.toList(repository.saveAll(po));
    }

    @Override
    public List<UserRolePo> getByUserId(Long id) {
        return StreamUtils.toList(repository.getByUserId(id));
    }

    @Override
    public List<UserRolePo> getByRoleId(Long id) {
        return StreamUtils.toList(repository.getByRoleId(id));
    }
}
