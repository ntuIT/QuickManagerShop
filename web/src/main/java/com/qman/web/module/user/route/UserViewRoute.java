package com.qman.web.module.user.route;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qman.web.constant.AuthRoute;
import com.qman.web.constant.biz.SessionConstant;
import com.qman.web.masterdata.ErrorMessageHolder;
import com.qman.web.masterdata.MasterDataStorage;
import com.qman.web.module.login.model.SessionData;
import com.qman.web.module.user.biz.UserInfoBiz;
import com.qman.web.module.user.model.AddUserRequest;
import com.qman.web.module.user.model.ChangePassRequest;
import com.qman.web.module.user.model.UpdateUserInfoRequest;
import com.qman.web.module.user.model.UpdateUserInfoRequest.Builder;
import com.qman.web.module.user.validator.UserInfoValidator;
import com.qman.web.orm.entity.UserPo;
import com.qman.web.utility.DateUtils;

@Controller
@RequestMapping(value = "/")
public class UserViewRoute {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserViewRoute.class);

    @Autowired
    private MasterDataStorage mdStorage;

    @Autowired
    private UserInfoValidator userInfoValidator;

    @Autowired
    private UserInfoBiz biz;

    @Autowired
    private ErrorMessageHolder emh;

    @GetMapping(value = AuthRoute.User.LIST)
    public String list(@RequestParam(value = "last_name", required = false) String lastName,
        @RequestParam(value = "middle_name", required = false) String middleName,
        @RequestParam(value = "first_name", required = false) String firstName, @RequestParam(value = "error", required = false) String error,
        Model model, HttpSession session) {
        if (error != null) {
            LOGGER.debug(error);
            boolean errorState = error.contains("success") ? true : false;
            model.addAttribute("error_message", emh.getMessByKey(error));
            model.addAttribute("error_state", errorState);
        }
        if (userInfoValidator.validateSearchByFullName(lastName, middleName, firstName).getValue0()) {
            model.addAttribute("user_list", biz.searchByFullName(lastName, middleName, firstName));
        } else {
            model.addAttribute("user_list", biz.getAll());
        }
        model.addAttribute("header_title", "Danh sách người dùng");
        model.addAttribute("title", "Quick Man - Danh sách người dùng");
        return "user/list";
    }

    @GetMapping(value = AuthRoute.User.MY_PROFILE)
    public String me(@RequestParam(value = "error", required = false) String error,
        @RequestParam(value = "reset_pass", required = false) String resetPass, Model model, HttpSession session) {
        if (error != null) {
            LOGGER.debug(error);
            boolean errorState = error.contains("success") ? true : false;
            model.addAttribute("error_message", emh.getMessByKey(error));
            model.addAttribute("error_state", errorState);
        }
        if (resetPass != null) {
            LOGGER.debug(resetPass);
            model.addAttribute("reset_pass", "Mật khẩu mới là: " + resetPass + ". Xin vui lòng đổi mật khẩu khi đăng nhập lại!");
        }
        Long userId = ((SessionData) session.getAttribute(SessionConstant.SESSION_INFO)).getUserId();
        UserPo currentUser = biz.getById(userId);
        Builder builder = UpdateUserInfoRequest.builder();
        builder.roleCode(currentUser.getRoleCodesForDisplay()).lastName(currentUser.getLastName()).middleName(currentUser.getMiddleName())
            .firstName(currentUser.getFirstName()).address(currentUser.getAddress()).gender(currentUser.getGender().getDisplayViName())
            .birthday(DateUtils.formatTo(currentUser.getBirthday())).phone(currentUser.getPhoneNo())
            .joinDate(DateUtils.formatTo(currentUser.getJoinDate())).idCode(currentUser.getIdCode());
        model.addAttribute("role_readonly", userInfoValidator.isRoleReadonly(session, userId));
        model.addAttribute("change_pass_request", new ChangePassRequest());
        model.addAttribute("update_infor_request", builder.build());
        model.addAttribute("username", currentUser.getUserName());
        model.addAttribute("header_title", "Thông tin người dùng");
        model.addAttribute("title", currentUser.getFullName());
        return "user/me";
    }

    @GetMapping(value = AuthRoute.User.DETAIL)
    public String detail(@RequestParam("userId") Long userId, @RequestParam(value = "error", required = false) String error, Model model,
        HttpSession session) {
        if (error != null) {
            LOGGER.debug(error);
            boolean errorState = error.contains("success") ? true : false;
            model.addAttribute("error_message", emh.getMessByKey(error));
            model.addAttribute("error_state", errorState);
        }
        UserPo currentUser = biz.getById(userId);
        Builder builder = UpdateUserInfoRequest.builder();
        builder.roleCode(currentUser.getRoleCodesForDisplay()).lastName(currentUser.getLastName()).middleName(currentUser.getMiddleName())
            .firstName(currentUser.getFirstName()).address(currentUser.getAddress()).gender(currentUser.getGender().getDisplayViName())
            .birthday(DateUtils.formatTo(currentUser.getBirthday())).phone(currentUser.getPhoneNo())
            .joinDate(DateUtils.formatTo(currentUser.getJoinDate())).idCode(currentUser.getIdCode());
        model.addAttribute("role_readonly", userInfoValidator.isRoleReadonly(session, userId));
        model.addAttribute("change_pass_request", new ChangePassRequest());
        model.addAttribute("update_infor_request", builder.build());
        model.addAttribute("header_title", "Thông tin người dùng");
        model.addAttribute("title", currentUser.getFullName());
        return "user/me";
    }

    @GetMapping(value = AuthRoute.User.ADD)
    public String add(@RequestParam(value = "error", required = false) String error, Model model, HttpSession session) {
        if (error != null) {
            LOGGER.debug(error);
            boolean errorState = error.contains("success") ? true : false;
            model.addAttribute("error_message", emh.getMessByKey(error));
            model.addAttribute("error_state", errorState);
        }
        AddUserRequest request = new AddUserRequest();
        model.addAttribute("request", request);
        model.addAttribute("header_title", "Đăng ký tài khoản");
        model.addAttribute("title", "Quick Man - Đăng ký tài khoản");
        return "user/add-user";
    }
}
