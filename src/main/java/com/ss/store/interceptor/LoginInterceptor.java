package com.ss.store.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // HttpServletRequest获取session对象
        // session检测uid是否存在，若不存在(未登录)则重定向到login.html
        Object obj = request.getSession().getAttribute("uid");
        if(obj==null){  // 未登录
            // 重定向到登录页面
            response.sendRedirect("/web/login.html");
            // 结束后续调用
            return false;
        }
        // 请求放行
        return true;
    }
}
