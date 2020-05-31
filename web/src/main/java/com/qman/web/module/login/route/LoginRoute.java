package com.qman.web.module.login.route;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.javatuples.Triplet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qman.web.constant.AuthRoute;
import com.qman.web.constant.PublicRoute;
import com.qman.web.constant.biz.SessionConstant;
import com.qman.web.interceptor.LoginInterceptor;
import com.qman.web.module.login.biz.LoginBiz;
import com.qman.web.module.login.model.LoginRequest;
import com.qman.web.module.login.model.SessionData;
import com.qman.web.module.login.validator.LoginValidator;
import com.qman.web.orm.entity.UserPo;
import com.qman.web.support.annotation.DoNotHaveToLogin;

@Controller
@RequestMapping(value = "/")
public class LoginRoute {

    @Autowired
    private LoginValidator validator;

    @Autowired
    private LoginBiz biz;

    @DoNotHaveToLogin
    @GetMapping(value = PublicRoute.LOGIN)
    public String index(Model model, HttpServletRequest request) {
        if (isValidSession(request)) { return "redirect:" + AuthRoute.DashBoard.ROOT; }
        model.addAttribute("request", new LoginRequest());
        model.addAttribute("header_title", "Đăng nhập");
        model.addAttribute("title", "Quick Man - Đăng ký tài khoản");
        return "login";
    }

    @DoNotHaveToLogin
    @PostMapping(value = PublicRoute.LOGIN)
    public String login(@ModelAttribute("request") LoginRequest reqBody, Model model, HttpServletRequest request, HttpServletResponse response) {
        if (isValidSession(request)) { return "redirect:" + AuthRoute.DashBoard.ROOT; }
        Triplet<Boolean, String, UserPo> validationResult = validator.validate(reqBody);
        if (validationResult.getValue0()) {
            boolean result = biz.doLogin(validationResult.getValue2(), request.getSession(), response);
            if (result) {
                return "redirect:" + AuthRoute.DashBoard.ROOT;
            } else {
                model.addAttribute("header_title", "Đăng ký tài khoản - LỖI: HỆ THỐNG");
            }
        } else {
            model.addAttribute("header_title", "Đăng ký tài khoản - LỖI: " + validationResult.getValue1());
        }
        model.addAttribute("request", new LoginRequest());
        model.addAttribute("title", "Quick Man - Đăng ký tài khoản");
        return "login";
    }

    @GetMapping(value = AuthRoute.LOGOUT)
    public String logout(HttpSession session, HttpServletResponse response) {
        session.invalidate();
        biz.clearAuthenticationCookie(response);
        return "redirect:" + PublicRoute.LOGIN;
    }

    private boolean isValidSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        SessionData si = (SessionData) session.getAttribute(SessionConstant.SESSION_INFO);
        if (si != null) return true;
        if (LoginInterceptor.makeSessionFromCookie(request)) return true;
        return false;
    }
}
