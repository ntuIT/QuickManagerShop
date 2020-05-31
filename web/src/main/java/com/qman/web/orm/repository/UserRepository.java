package com.qman.web.orm.repository;

import org.springframework.data.repository.CrudRepository;

import com.qman.web.orm.entity.UserPo;

public interface UserRepository extends CrudRepository<UserPo, Long> {

    UserPo getById(Long id);

    UserPo getByUserName(String username);
}
