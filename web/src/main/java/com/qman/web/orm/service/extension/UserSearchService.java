package com.qman.web.orm.service.extension;

import java.util.List;

import com.qman.web.orm.entity.UserPo;
import com.qman.web.orm.glossary.Gender;
import com.qman.web.orm.glossary.UserStatus;

public interface UserSearchService {

    List<UserPo> queryWithName(String lastName, String middleName, String firstName, Gender gender, UserStatus status);
}
