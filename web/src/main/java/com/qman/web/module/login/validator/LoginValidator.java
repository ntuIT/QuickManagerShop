package com.qman.web.module.login.validator;

import org.javatuples.Triplet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qman.web.module.login.model.LoginRequest;
import com.qman.web.orm.entity.UserPo;
import com.qman.web.orm.glossary.UserStatus;
import com.qman.web.orm.service.UserService;
import com.qman.web.utility.PasswordUtils;
import com.qman.web.validator.entity.UserValidation;

@Component
public class LoginValidator {

    @Autowired
    private UserService userService;

    public Triplet<Boolean, String, UserPo> validate(LoginRequest requestModel) {
        UserPo willPersistedObject;
        if (!UserValidation.validateUsername(requestModel.getUsername()))
            return Triplet.with(false, "Tên đăng nhập chỉ bao gồm số và chữ cái, từ 6 - 10 ký tự!", null);
        willPersistedObject = userService.getByUserName(requestModel.getUsername());
        if (willPersistedObject == null) return Triplet.with(false, "Tài khoản không tồn tại!", null);
        Boolean isCorrectPassword = PasswordUtils.checkPassword(requestModel.getPassword(), willPersistedObject.getPassword());
        if (!isCorrectPassword) return Triplet.with(false, "Mật khẩu không đúng!", null);
        if (willPersistedObject.getStatus() == UserStatus.DISABLED) return Triplet.with(false, "Tài khoản đã bị khóa!", null);
        willPersistedObject.isKeepLogin = requestModel.isKeepLogin();
        return Triplet.with(true, "Thành công", willPersistedObject);
    }
}
