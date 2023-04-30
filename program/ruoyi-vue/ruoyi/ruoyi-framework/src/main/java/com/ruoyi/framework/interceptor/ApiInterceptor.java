package com.ruoyi.framework.interceptor;

import com.ruoyi.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 防止重复提交拦截器
 *
 * @author ruoyi
 */
@Component
public class ApiInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            if (request.getRequestURI().equals("/api/user/list")) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

}
