package com.qman.web.orm.service.utils;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.qman.web.constant.biz.SQLQueryKey;
import com.qman.web.orm.glossary.Gender;
import com.qman.web.orm.glossary.UserStatus;
import com.qman.web.validator.entity.UserValidation;

@Component
public class UserServiceUtils {

    /**
     * Reusable function for same logic of each query type
     */
    public void makeQueryWithOptionalCriteria(StringBuilder originalSB, String lastName, String middleName, String firstName, Gender gender,
        UserStatus userStatus) {
        String space = " ";
        if (lastName != null && UserValidation.validateLastName(lastName))
            originalSB.append("AND u.last_name like :param".replace(SQLQueryKey.param, SQLQueryKey.User.last_name)).append(space);
        if (middleName != null && UserValidation.validateMiddleName(middleName))
            originalSB.append("AND u.middle_name like :param".replace(SQLQueryKey.param, SQLQueryKey.User.middle_name)).append(space);
        if (firstName != null && UserValidation.validateFirstName(firstName))
            originalSB.append("AND u.first_name like :param".replace(SQLQueryKey.param, SQLQueryKey.User.first_name)).append(space);
        if (gender != null) originalSB.append("AND u.gender = :param".replace(SQLQueryKey.param, SQLQueryKey.User.gender)).append(space);
        if (userStatus != null) originalSB.append("AND u.status = :param".replace(SQLQueryKey.param, SQLQueryKey.User.status)).append(space);
    }

    /**
     * Reusable function for same logic of each query type
     */
    public void setQueryParamWithOptionalCriteria(Query originalQuery, String lastName, String middleName, String firstName, Gender gender,
        UserStatus userStatus) {
        String suffix = "%";
        if (lastName != null && UserValidation.validateLastName(lastName)) originalQuery.setParameter(SQLQueryKey.User.last_name, lastName + suffix);
        if (middleName != null && UserValidation.validateMiddleName(middleName))
            originalQuery.setParameter(SQLQueryKey.User.middle_name, middleName + suffix);
        if (firstName != null && UserValidation.validateFirstName(firstName))
            originalQuery.setParameter(SQLQueryKey.User.first_name, firstName + suffix);
        if (gender != null) originalQuery.setParameter(SQLQueryKey.User.gender, gender.getValue());
        if (userStatus != null) originalQuery.setParameter(SQLQueryKey.User.status, userStatus.getValue());
    }
}
