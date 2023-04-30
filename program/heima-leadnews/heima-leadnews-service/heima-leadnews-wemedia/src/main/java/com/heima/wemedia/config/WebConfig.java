package com.heima.wemedia.config;

import com.heima.wemedia.interceptor.WemediaTokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 拦截所有路径，仅运行登录请求与验证请求通过
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new WemediaTokenInterceptor())
                .addPathPatterns("/**");
    }
}
