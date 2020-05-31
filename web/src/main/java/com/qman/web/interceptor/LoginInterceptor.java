package com.qman.web.interceptor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.qman.web.constant.PublicRoute;
import com.qman.web.constant.biz.SessionConstant;
import com.qman.web.module.login.model.SessionData;
import com.qman.web.module.user.biz.UserInfoBiz;
import com.qman.web.orm.entity.UserPo;
import com.qman.web.support.annotation.DoNotHaveToLogin;

/**
 * @author DEV-LongDT
 *
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) { // This is RequestMapping like @GetMapping, @PostMapping handler
            Method method = ((HandlerMethod) handler).getMethod();
            if (!method.isAnnotationPresent(DoNotHaveToLogin.class)) {
                SessionData si = (SessionData) request.getSession().getAttribute(SessionConstant.SESSION_INFO);
                if (si == null) {
                    boolean cookieValid = makeSessionFromCookie(request);
                    if (cookieValid) return true;
                    response.sendRedirect(request.getContextPath() + PublicRoute.LOGIN);
                    LOGGER.debug("[NOT ALLOW] [1] [" + request.getRequestURI() + "]");
                    return false;
                }
            }
            LOGGER.debug("[ALLOW] [" + request.getRequestURI() + "]");
            return true;
        }
        LOGGER.debug("[NOT ALLOW] [2] [" + request.getRequestURI() + "]");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, //
        Object handler, ModelAndView modelAndView) throws Exception {}

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, //
        Object handler, Exception ex) throws Exception {}

    public static boolean makeSessionFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) return false;
        List<Cookie> filteredCookies
            = Arrays.stream(cookies).filter(item -> item.getName().equals(SessionConstant.SESSION_INFO)).collect(Collectors.toList());
        if (filteredCookies != null && filteredCookies.size() == 1 && filteredCookies.get(0).getValue() != null
            && !filteredCookies.get(0).getValue().isEmpty()) {
            LOGGER.debug("makeSessionFromCookie: " + filteredCookies.get(0).getName());
            Long userId = null;
            try {
                userId = new Long(filteredCookies.get(0).getValue());
            } catch (NumberFormatException e) {
                LOGGER.debug("userId from cookie is not valid number!");
                return false;
            }
            LOGGER.debug("Fetching new user info from DB with id: " + userId);
            UserPo user = UserInfoBiz.bean().getById(userId);
            if (user != null) {
                LOGGER.debug("Making new session info with id: " + userId);
                SessionData newSI = new SessionData(user.getUserName(), user.getId(), user.getFullName(), user.getAvatarUrl(), user.getRoleList());
                request.getSession().setAttribute(SessionConstant.SESSION_INFO, newSI);
                LOGGER.debug("Cookie with name: " + SessionConstant.SESSION_INFO + " is VALID to make session!");
                return true;
            } else {
                LOGGER.debug("Fail to make new session info with id " + userId + " (user doesn't exist)");
                return false;
            }
        }
        LOGGER.debug("Cookie with name: " + SessionConstant.SESSION_INFO + " is NOT valid(null) to make session!");
        return false;
    }
}
