package com.qman.web.orm.service;

import java.util.List;

import com.qman.web.orm.entity.UserPo;

public interface UserService {

    UserPo save(UserPo po);

    List<UserPo> getAll();

    UserPo getById(Long id);

    UserPo getByUserName(String username);

    List<UserPo> searchByFullName(String lastName, String middleName, String firstName);
}
