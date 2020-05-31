package com.qman.web.interceptor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.qman.web.constant.biz.SessionConstant;
import com.qman.web.masterdata.MasterDataStorage;
import com.qman.web.masterdata.PermissionTable;
import com.qman.web.module.login.model.SessionData;

public class PermissionInterceptor extends HandlerInterceptorAdapter {

    private static final String ACTIVE_PATH = "active_path";

    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) { // This is RequestMapping like @GetMapping, @PostMapping handler
            Method method = ((HandlerMethod) handler).getMethod();
            SessionData si = (SessionData) request.getSession().getAttribute(SessionConstant.SESSION_INFO);
            GetMapping getMap = method.getAnnotation(GetMapping.class);
            if (getMap != null) { return validatePermission(si, request, getMap.value()); }
            PostMapping postMap = method.getAnnotation(PostMapping.class);
            if (postMap != null) { return validatePermission(si, request, postMap.value()); }
            PutMapping putMap = method.getAnnotation(PutMapping.class);
            if (putMap != null) { return validatePermission(si, request, putMap.value()); }
            LOGGER.debug("[NOT ALLOW] [1] [" + request.getRequestURI() + "]");
            return false;
        }
        LOGGER.debug("[NOT ALLOW] [2] [" + request.getRequestURI() + "]");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, //
        Object handler, ModelAndView modelAndView) throws Exception {
        if (handler instanceof HandlerMethod) { // This is RequestMapping like @GetMapping, @PostMapping handler
            Method method = ((HandlerMethod) handler).getMethod();
            GetMapping getMap = method.getAnnotation(GetMapping.class);
            if (getMap != null) {
                modelAndView.addObject("sidebarItems",
                    MasterDataStorage.bean().getSideBarInfo((String) request.getAttribute(ACTIVE_PATH), request.getSession()));
                modelAndView.addObject("roles", MasterDataStorage.bean().getRoleList());
                modelAndView.addObject("genders", MasterDataStorage.bean().getGenderList());
                SessionData sd = (SessionData) request.getSession().getAttribute(SessionConstant.SESSION_INFO);
                modelAndView.addObject("full_name", sd.getFullName());
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, //
        Object handler, Exception ex) throws Exception {}

    private boolean validatePermission(SessionData si, HttpServletRequest request, String[] listPath) {
        if (listPath != null && listPath.length > 0) {
            String path = listPath[0];
            String[] config = PermissionTable.getInstance().getConfigMap().get(path);
            if (config == null) return false;
            List<String> listConfigRole = Arrays.asList(config);
            Boolean allow = listConfigRole.containsAll(si.getRoleCodeList());
            if (allow) {
                request.setAttribute(ACTIVE_PATH, path); // store this value to make correct state of Sidebar
                LOGGER.debug("[ALLOW] [" + request.getRequestURI() + "]");
            } else {
                LOGGER.debug("[NOT ALLOW] [4] [" + request.getRequestURI() + "]");
            }
            return allow;
        }
        LOGGER.debug("[NOT ALLOW] [3] [" + request.getRequestURI() + "]");
        return false;
    }
}
