package com.ss.store.config;

import com.ss.store.interceptor.LoginInterceptor;
import org.aopalliance.intercept.Interceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

// 拦截器的注册
@Configuration // 加载当前拦截器并注册
public class LoginInterceptorConfigurer implements WebMvcConfigurer {

    // 配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 创建自定义拦截器对象
        HandlerInterceptor interceptor= new LoginInterceptor();
        // 创建拦截白名单
        List<String> patterns=new ArrayList<>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/index.html");
        patterns.add("/web/login.html");
        patterns.add("/web/register.html");
        patterns.add("/web/product.html");
        patterns.add("/users/reg");
        patterns.add("/users/login");
        patterns.add("/districts/**");
        patterns.add("/products/**");
        // 配置黑名单(所有页面，在排除)，白名单
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**") // 黑名单
                .excludePathPatterns(patterns); // 白名单

    }
}
