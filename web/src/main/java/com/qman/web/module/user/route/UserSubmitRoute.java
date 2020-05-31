package com.qman.web.module.user.route;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.javatuples.Triplet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.qman.web.constant.AuthRoute;
import com.qman.web.constant.biz.SessionConstant;
import com.qman.web.constant.error.code.ErrorCode;
import com.qman.web.masterdata.MasterDataStorage;
import com.qman.web.module.login.model.SessionData;
import com.qman.web.module.user.biz.AddUserBiz;
import com.qman.web.module.user.biz.UserInfoBiz;
import com.qman.web.module.user.model.AddUserRequest;
import com.qman.web.module.user.model.ChangePassRequest;
import com.qman.web.module.user.model.UpdateUserInfoRequest;
import com.qman.web.module.user.validator.AddUserValidator;
import com.qman.web.module.user.validator.UserInfoValidator;
import com.qman.web.orm.entity.UserPo;
import com.qman.web.utility.RandomStringUtility;

@Controller
public class UserSubmitRoute {

    @Autowired
    private MasterDataStorage mdStorage;

    @Autowired
    private AddUserValidator addUserValidator;

    @Autowired
    private UserInfoValidator userInfoValidator;

    @Autowired
    private AddUserBiz addUserBiz;

    @Autowired
    private UserInfoBiz userInfoBiz;

    @PostMapping(value = AuthRoute.User.ADD)
    public String addUser(@ModelAttribute("request") AddUserRequest reqBody, Model model) {
        Triplet<Boolean, String, UserPo> validationResult = addUserValidator.validate(reqBody);
        String errorCode = validationResult.getValue1();
        if (validationResult.getValue0()) { errorCode = addUserBiz.doAddUser(validationResult.getValue2()) ? errorCode : ErrorCode.INTERNAL_ERROR; }
        return "redirect:" + AuthRoute.User.ADD + "?error=" + errorCode;
    }

    @PostMapping(value = AuthRoute.User.CHANGE_PASS)
    public String changePass(@ModelAttribute("request") ChangePassRequest reqBody, Model model, HttpSession session) {
        Long userId = ((SessionData) session.getAttribute(SessionConstant.SESSION_INFO)).getUserId();
        Triplet<Boolean, String, UserPo> validationResult = userInfoValidator.validatePassChanging(reqBody, userId);
        String errorCode = validationResult.getValue1();
        if (validationResult.getValue0()) {
            errorCode = userInfoBiz.doChangePassword(validationResult.getValue2()) ? errorCode : ErrorCode.INTERNAL_ERROR;
        }
        return "redirect:" + AuthRoute.User.MY_PROFILE + "?error=" + errorCode;
    }

    @PostMapping(value = AuthRoute.User.UPDATE_INFO)
    public String updateInfo(@ModelAttribute("request") UpdateUserInfoRequest reqBody, Model model, HttpSession session) {
        Long userId = ((SessionData) session.getAttribute(SessionConstant.SESSION_INFO)).getUserId();
        Triplet<Boolean, String, UserPo> validationResult = userInfoValidator.validateInfoChanging(reqBody, userId);
        String errorCode = validationResult.getValue1();
        if (validationResult.getValue0()) {
            errorCode = userInfoBiz.doChangePassword(validationResult.getValue2()) ? errorCode : ErrorCode.INTERNAL_ERROR;
        }
        return "redirect:" + AuthRoute.User.MY_PROFILE + "?error=" + errorCode;
    }

    @PostMapping(value = AuthRoute.User.RESET_PASS)
    public String resetPassword(@ModelAttribute("request") Map<String, Long> reqBody, Model model, HttpSession session) {
        Triplet<Boolean, String, UserPo> validationResult = userInfoValidator.validateResetPassword(reqBody.get("userId"));
        String errorCode = validationResult.getValue1();
        String newPassword = new RandomStringUtility(6).nextString();
        if (validationResult.getValue0()) {
            UserPo po = validationResult.getValue2();
            po.newPassword = newPassword;
            errorCode = userInfoBiz.doResetPassword(po) ? errorCode : ErrorCode.INTERNAL_ERROR;
        }
        return "redirect:" + AuthRoute.User.MY_PROFILE + "?error=" + errorCode
            + (errorCode.equals(ErrorCode.User.SUCCESS) ? ("&reset_pass=" + newPassword) : "");
    }
}
