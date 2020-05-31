package com.qman.web.orm.service;

import java.util.List;

import com.qman.web.orm.entity.UserRolePo;

public interface UserRoleService {

    UserRolePo save(UserRolePo po);

    List<UserRolePo> saveAll(List<UserRolePo> po);

    List<UserRolePo> getByUserId(Long id);

    List<UserRolePo> getByRoleId(Long id);
}
