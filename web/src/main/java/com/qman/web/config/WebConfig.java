package com.qman.web.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.qman.web.constant.AuthRoute;
import com.qman.web.constant.PublicRoute;
import com.qman.web.interceptor.LoginInterceptor;
import com.qman.web.interceptor.PermissionInterceptor;
import com.qman.web.interceptor.RequestIPInterceptor;
import com.qman.web.support.resolver.AccountArgumentResolver;

@SuppressWarnings("deprecation")
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePath
            = Arrays.asList("/", "/js/**", "/css/**", "/webjars/**", "/fonts/**", "/img/**", "/favicon.ico", PublicRoute.LOGIN, AuthRoute.LOGOUT, "/error");
        registry.addInterceptor(new RequestIPInterceptor()).addPathPatterns("/**").excludePathPatterns("/js/**", "/css/**", "/webjars/**",
            "/fonts/**", "/img/**", "/favicon.ico");
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns(excludePath);
        registry.addInterceptor(new PermissionInterceptor()).addPathPatterns("/**").excludePathPatterns(excludePath);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new AccountArgumentResolver());
    }
}
