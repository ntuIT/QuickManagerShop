package com.qman.web.orm.service.impl.extension;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qman.web.orm.entity.UserPo;
import com.qman.web.orm.glossary.Gender;
import com.qman.web.orm.glossary.UserStatus;
import com.qman.web.orm.service.extension.UserSearchService;
import com.qman.web.orm.service.utils.UserServiceUtils;

@Service
public class UserSearchServiceImpl implements UserSearchService {

    @Autowired
    private UserServiceUtils userServiceUtils;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<UserPo> queryWithName(String lastName, String middleName, String firstName, Gender gender, UserStatus status) {
        // Prepare basic query string
        String space = " ";
        StringBuilder sb = new StringBuilder("SELECT u.* FROM user u WHERE true").append(space);
        // Prepare query with optional criteria
        userServiceUtils.makeQueryWithOptionalCriteria(sb, lastName, middleName, firstName, gender, status);
        // Make query object then fetch data with parameters, input type of UserPo to map result to desired Entity
        Query processedQuery = entityManager.createNativeQuery(sb.toString(), UserPo.class);
        userServiceUtils.setQueryParamWithOptionalCriteria(processedQuery, lastName, middleName, firstName, gender, status);
        @SuppressWarnings("unchecked")
        List<UserPo> userList = processedQuery.getResultList();
        return userList;
    }
}
