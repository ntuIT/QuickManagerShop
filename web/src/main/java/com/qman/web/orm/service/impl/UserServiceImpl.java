package com.qman.web.orm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qman.web.orm.entity.UserPo;
import com.qman.web.orm.repository.UserRepository;
import com.qman.web.orm.service.UserService;
import com.qman.web.orm.service.extension.UserSearchService;
import com.qman.web.utility.StreamUtils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserSearchService searchService;

    @Override
    public UserPo save(UserPo po) {
        return this.repository.save(po);
    }

    @Override
    public UserPo getById(Long id) {
        return this.repository.getById(id);
    }

    @Override
    public UserPo getByUserName(String username) {
        return this.repository.getByUserName(username);
    }

    @Override
    public List<UserPo> getAll() { return StreamUtils.toList(this.repository.findAll()); }

    @Override
    public List<UserPo> searchByFullName(String lastName, String middleName, String firstName) {
        return searchService.queryWithName(lastName, middleName, firstName, null, null);
    }
}
