package com.qman.web.orm.repository;

import org.springframework.data.repository.CrudRepository;

import com.qman.web.orm.entity.UserRolePo;

public interface UserRoleRepository extends CrudRepository<UserRolePo, Long> {

    Iterable<UserRolePo> getByUserId(Long id);

    Iterable<UserRolePo> getByRoleId(Long id);
}
