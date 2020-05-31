package com.qman.web.support.resolver;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.qman.web.constant.biz.SessionConstant;
import com.qman.web.orm.entity.UserPo;
import com.qman.web.support.annotation.Account;

public class AccountArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public Object resolveArgument(MethodParameter arg0, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest,
        WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        UserPo acc = (UserPo) request.getSession().getAttribute(SessionConstant.SESSION_INFO);
        return acc;
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(Account.class);
    }
}
