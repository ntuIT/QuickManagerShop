package com.qman.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class RequestIPInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestIPInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        String clientIP = request.getRemoteAddr();
        LOGGER.debug("[REQUEST] [" + request.getMethod() + "] [" + request.getRequestURI() + "] from: " + clientIP);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, //
        Object handler, ModelAndView modelAndView) throws Exception {}

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, //
        Object handler, Exception ex) throws Exception {
        long startTime = (Long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        long executeTime = endTime - startTime;
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(
                "[RESPONSE] [" + request.getMethod() + "] [" + response.getStatus() + "] [" + request.getRequestURI() + "] : " + executeTime + "ms");
        }
    }
}
