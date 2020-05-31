package com.qman.web.module.user.validator;

import java.util.Date;
import java.util.List;

import org.javatuples.Triplet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qman.web.constant.error.code.ErrorCode;
import com.qman.web.masterdata.MasterDataStorage;
import com.qman.web.module.user.model.AddUserRequest;
import com.qman.web.orm.entity.UserPo;
import com.qman.web.orm.glossary.Gender;
import com.qman.web.orm.glossary.UserStatus;
import com.qman.web.orm.service.UserService;
import com.qman.web.validator.entity.UserValidation;

@Component
public class AddUserValidator {

    @Autowired
    private MasterDataStorage mdStorage;

    @Autowired
    private UserService userService;

    public Triplet<Boolean, String, UserPo> validate(AddUserRequest requestModel) {
        UserPo willPersistedObject = new UserPo();
        // 1)--------------------------------------------------------------------------------------
        if (!UserValidation.validateUsername(requestModel.getUsername())) return Triplet.with(false, ErrorCode.User.USERNAME_INVALID, null);
        if (userService.getByUserName(requestModel.getUsername()) != null) return Triplet.with(false, ErrorCode.User.USERNAME_EXIST, null);
        willPersistedObject.setUserName(requestModel.getUsername());
        // 2)--------------------------------------------------------------------------------------
        if (!UserValidation.validatePassword(requestModel.getPassword())) return Triplet.with(false, ErrorCode.User.PASSWORD_INVALID, null);
        willPersistedObject.setPassword(requestModel.getPassword());
        // 3)--------------------------------------------------------------------------------------
        willPersistedObject.setStatus(UserStatus.ACTIVATED);
        // 4)--------------------------------------------------------------------------------------
        List<Long> roleIdList = UserValidation.validateRoleName(requestModel.getRoleCode(), mdStorage.getRoleList());
        if (roleIdList == null) return Triplet.with(false, ErrorCode.User.ROLE_INVALID, null);
        willPersistedObject.setRoleIdList(roleIdList);
        // 5)--------------------------------------------------------------------------------------
        if (!UserValidation.validateLastName(requestModel.getLastName())) return Triplet.with(false, ErrorCode.User.NAME_LAST_INVALID, null);
        if (!UserValidation.validateMiddleName(requestModel.getMiddleName())) return Triplet.with(false, ErrorCode.User.NAME_MIDDLE_INVALID, null);
        if (!UserValidation.validateFirstName(requestModel.getFirstName())) return Triplet.with(false, ErrorCode.User.NAME_FIRST_INVALID, null);
        willPersistedObject.setLastName(requestModel.getLastName());
        willPersistedObject.setMiddleName(requestModel.getMiddleName());
        willPersistedObject.setFirstName(requestModel.getFirstName());
        // 6)--------------------------------------------------------------------------------------
        Gender gender = UserValidation.validateGender(requestModel.getGender(), mdStorage.getGenderList());
        if (gender == null) return Triplet.with(false, ErrorCode.User.GENDER_INVALID, null);
        willPersistedObject.setGender(gender);
        // 7)--------------------------------------------------------------------------------------
        Date birthday = UserValidation.validateBirthday(requestModel.getBirthday());
        if (birthday == null) return Triplet.with(false, ErrorCode.User.BIRTHDAY_INVALID, null);
        willPersistedObject.setBirthday(birthday);
        // 8)--------------------------------------------------------------------------------------
        if (!UserValidation.validatePhone(requestModel.getPhone())) return Triplet.with(false, ErrorCode.User.PHONE_INVALID, null);
        willPersistedObject.setPhoneNo(requestModel.getPhone());
        // 9)--------------------------------------------------------------------------------------
        Date joinDate = UserValidation.validateJoinDate(requestModel.getJoinDate());
        if (joinDate != null) willPersistedObject.setJoinDate(joinDate);
        // 10)--------------------------------------------------------------------------------------
        if (UserValidation.validateIDCode(requestModel.getIdCode())) willPersistedObject.setIdCode(requestModel.getIdCode());
        // 11)--------------------------------------------------------------------------------------
        if (!UserValidation.validateAddress(requestModel.getAddress())) return Triplet.with(false, ErrorCode.User.ADDESS_INVALID, null);
        willPersistedObject.setAddress(requestModel.getAddress());
        // Finish validating
        return Triplet.with(true, ErrorCode.User.SUCCESS, willPersistedObject);
    }
}
