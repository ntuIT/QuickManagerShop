package com.qman.web.module.login.biz;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qman.web.constant.biz.CommonConstant;
import com.qman.web.constant.biz.SessionConstant;
import com.qman.web.module.login.model.SessionData;
import com.qman.web.orm.entity.UserPo;
import com.qman.web.orm.service.UserService;

@Component
public class LoginBiz {

    @Autowired
    private UserService userService;

    public boolean doLogin(UserPo user, HttpSession session, HttpServletResponse response) {
        try {
            user.setLastLogin(new Date());
            SessionData si = new SessionData(user.getUserName(), user.getId(), user.getFullName(), user.getAvatarUrl(), user.getRoleList());
            session.setAttribute(SessionConstant.SESSION_INFO, si);
            // store at client's browser
            if (user.isKeepLogin) {
                response.addCookie(makeCookie(si.getUserId()));
            } else {
                response.addCookie(makeDeadCookie());
            }
            return userService.save(user) != null;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Make a @Cookie with value from @User.id
     */
    private Cookie makeCookie(long userId) {
        // try {
        // String cookieValue = Base64Utils.toString(userId);
        Cookie cookie = new Cookie(SessionConstant.SESSION_INFO, new Long(userId).toString());
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(CommonConstant.WEEK_TO_SECOND);
        return cookie;
        // } catch (IOException e) {
        // e.printStackTrace();
        // return null;
        // }
    }

    private Cookie makeDeadCookie() {
        Cookie cookie = new Cookie(SessionConstant.SESSION_INFO, "");
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0); // this will delete cookie has name like above SessionConstant.SESSION_INFO
        return cookie;
    }

    public void clearAuthenticationCookie(HttpServletResponse response) {
        response.addCookie(makeDeadCookie());
    }
}
