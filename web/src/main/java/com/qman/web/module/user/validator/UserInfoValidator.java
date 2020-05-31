package com.qman.web.module.user.validator;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.javatuples.Pair;
import org.javatuples.Triplet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qman.web.constant.biz.SessionConstant;
import com.qman.web.constant.error.code.ErrorCode;
import com.qman.web.masterdata.MasterDataStorage;
import com.qman.web.module.login.model.SessionData;
import com.qman.web.module.user.model.ChangePassRequest;
import com.qman.web.module.user.model.UpdateUserInfoRequest;
import com.qman.web.orm.entity.UserPo;
import com.qman.web.orm.glossary.Gender;
import com.qman.web.orm.service.UserService;
import com.qman.web.utility.PasswordUtils;
import com.qman.web.validator.entity.UserValidation;

@Component
public class UserInfoValidator {

    @Autowired
    private MasterDataStorage mdStorage;

    @Autowired
    private UserService userService;

    public Pair<Boolean, String> validateSearchByFullName(String lastName, String middleName, String firstName) {
        if (lastName == null && middleName == null && firstName == null) return Pair.with(false, null);
        // 1)--------------------------------------------------------------------------------------
        if (lastName != null && !UserValidation.validateLastName(lastName)) return Pair.with(false, ErrorCode.User.NAME_LAST_INVALID);
        if (middleName != null && !UserValidation.validateMiddleName(middleName)) return Pair.with(false, ErrorCode.User.NAME_MIDDLE_INVALID);
        if (firstName != null && !UserValidation.validateFirstName(firstName)) return Pair.with(false, ErrorCode.User.NAME_FIRST_INVALID);
        // Finish validating
        return Pair.with(true, ErrorCode.User.SUCCESS);
    }

    public Triplet<Boolean, String, UserPo> validatePassChanging(ChangePassRequest requestModel, Long userId) {
        if (!UserValidation.validatePassword(requestModel.getCurrentPassword()) || !UserValidation.validatePassword(requestModel.getNewPassword()))
            return Triplet.with(false, ErrorCode.User.PASSWORD_INVALID, null);
        UserPo persistedObject = userService.getById(userId);
        if (!PasswordUtils.checkPassword(requestModel.getCurrentPassword(), persistedObject.getPassword()))
            return Triplet.with(false, ErrorCode.User.PASSWORD_NOT_MATCH, null);
        persistedObject.newPassword = requestModel.getNewPassword();
        return Triplet.with(true, ErrorCode.User.SUCCESS, persistedObject);
    }

    public Triplet<Boolean, String, UserPo> validateResetPassword(Long userId) {
        if (userId == null) return Triplet.with(false, ErrorCode.User.USERNAME_NOT_EXIST, null);
        UserPo matched = userService.getById(userId);
        if (matched == null) return Triplet.with(false, ErrorCode.User.USERNAME_NOT_EXIST, null);
        return Triplet.with(true, ErrorCode.User.SUCCESS, matched);
    }

    public Triplet<Boolean, String, UserPo> validateInfoChanging(UpdateUserInfoRequest requestModel, Long userId) {
        UserPo persistedObject = userService.getById(userId);
        // 1)--------------------------------------------------------------------------------------
        if (requestModel.getLastName() != null) {
            if (!UserValidation.validateLastName(requestModel.getLastName())) return Triplet.with(false, ErrorCode.User.NAME_LAST_INVALID, null);
            persistedObject.setLastName(requestModel.getLastName());
        }
        if (requestModel.getMiddleName() != null) {
            if (!UserValidation.validateMiddleName(requestModel.getMiddleName()))
                return Triplet.with(false, ErrorCode.User.NAME_MIDDLE_INVALID, null);
            persistedObject.setMiddleName(requestModel.getMiddleName());
        }
        if (requestModel.getFirstName() != null) {
            if (!UserValidation.validateFirstName(requestModel.getFirstName())) return Triplet.with(false, ErrorCode.User.NAME_FIRST_INVALID, null);
            persistedObject.setFirstName(requestModel.getFirstName());
        }
        // 2)--------------------------------------------------------------------------------------
        if (requestModel.getGender() != null) {
            Gender gender = UserValidation.validateGender(requestModel.getGender(), mdStorage.getGenderList());
            if (gender == null) return Triplet.with(false, ErrorCode.User.GENDER_INVALID, null);
            persistedObject.setGender(gender);
        }
        // 3)--------------------------------------------------------------------------------------
        Date birthday = UserValidation.validateBirthday(requestModel.getBirthday());
        if (birthday == null) return Triplet.with(false, ErrorCode.User.BIRTHDAY_INVALID, null);
        persistedObject.setBirthday(birthday);
        // 4)--------------------------------------------------------------------------------------
        if (requestModel.getPhone() != null) {
            if (!UserValidation.validatePhone(requestModel.getPhone())) return Triplet.with(false, ErrorCode.User.PHONE_INVALID, null);
            persistedObject.setPhoneNo(requestModel.getPhone());
        }
        // 5)--------------------------------------------------------------------------------------
        if (requestModel.getJoinDate() != null) {
            Date joinDate = UserValidation.validateJoinDate(requestModel.getJoinDate());
            if (joinDate != null) persistedObject.setJoinDate(joinDate);
        }
        // 6)--------------------------------------------------------------------------------------
        if (requestModel.getIdCode() != null) {
            if (UserValidation.validateIDCode(requestModel.getIdCode())) persistedObject.setIdCode(requestModel.getIdCode());
        }
        // 7)--------------------------------------------------------------------------------------
        if (requestModel.getAddress() != null) {
            if (!UserValidation.validateAddress(requestModel.getAddress())) return Triplet.with(false, ErrorCode.User.ADDESS_INVALID, null);
            persistedObject.setAddress(requestModel.getAddress());
        }
        // Finish validating
        return Triplet.with(true, ErrorCode.User.SUCCESS, persistedObject);
    }

    public boolean isRoleReadonly(HttpSession session, Long accessedUserID) {
        if (session == null) throw new NullPointerException("UserInfoValidator.isRoleReadonly(): session is null");
        SessionData si = (SessionData) session.getAttribute(SessionConstant.SESSION_INFO);
        List<String> roles = si.getRoleCodeList();
        boolean isAdmin = roles.contains("ADM");
        if (!isAdmin) return true; // not Admin, can't change, readonly
        boolean isAdminAccount = isAdmin && si.getUserId().equals(accessedUserID);
        return isAdminAccount; // is Admin but not Admin account, can be changed, not readonly
    }
}
