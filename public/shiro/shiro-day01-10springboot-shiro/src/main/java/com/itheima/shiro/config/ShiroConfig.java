package com.itheima.shiro.config;

import com.itheima.shiro.core.ShiroDbRealm;
import com.itheima.shiro.core.filter.RolesOrAuthorizationFilter;
import com.itheima.shiro.core.impl.ShiroDbRealmImpl;
import com.itheima.shiro.properties.PropertiesUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description：权限管理配置类
 */
@Configuration
@ComponentScan(basePackages = "com.itheima.shiro.core")
@Log4j2
public class ShiroConfig {

    //创建cookie对象
    @Bean(name = "simpleCookie")
    public SimpleCookie simpleCookie(){
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setName("ShiroSession");
        return  simpleCookie;
    }

    //创建权限管理器
    @Bean("securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //管理realm
        securityManager.setRealm(shiroDbRealm());
        //管理会话
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    //自定义realm
    @Bean("shiroDbRealm")
    public ShiroDbRealm shiroDbRealm(){
        return new  ShiroDbRealmImpl();
    }

    //会话管理器
    @Bean("sessionManager")
    public DefaultWebSessionManager sessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        //关闭定时检测session的有效性
        sessionManager.setSessionValidationSchedulerEnabled(false);
        //生效cookie
        sessionManager.setSessionIdCookieEnabled(true);
        //指定cookie的生成策略
        sessionManager.setSessionIdCookie(simpleCookie());
        //指定全局会话超时时间
        sessionManager.setGlobalSessionTimeout(3600000);
        return sessionManager;
    }

    //创建生命周期的管理，使用static修饰提前加载时机
    @Bean("lifecycleBeanPostProcessor")
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return  new LifecycleBeanPostProcessor();
    }


    //为了使用shiro的注解进行权限相关的校验
    //aop增强（使用注解鉴权方式）
    /**
     * @Description AOP式方法级权限检查
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /**
     * @Description 配合DefaultAdvisorAutoProxyCreator事项注解权限校验
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(defaultWebSecurityManager());
        return new AuthorizationAttributeSourceAdvisor();
    }



    //shiro过滤器管理
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //交给web security manager管理
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager());
        //添加自定义的过滤器使其生效，调试ShiroFilterFactoryBean的
        //登录路径控制
        shiroFilterFactoryBean.setLoginUrl("/login");
        //未登录成功跳转到登录
        shiroFilterFactoryBean.setUnauthorizedUrl("/login");
        //DefaultFilterChainManager先加载filter，然后加载过滤器链
        // createFilterChainManager方法验证自定义过滤器被加载成功。(覆盖默认的filters DefaultFilters)
        shiroFilterFactoryBean.setFilters(filters());
        //过滤器链
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap());
        return shiroFilterFactoryBean;
    }



    /**
     * @Description 加载自定义过滤器
     */
    private Map<String, Filter> filters(){
        Map<String,Filter> map = new HashMap<>();
        map.put("roles-or", new RolesOrAuthorizationFilter());
        return map;
    }


    /**
     * @Description 过滤器链定义
     */
    private Map<String,String> filterChainDefinitionMap(){
        List<Object> list =  PropertiesUtil.propertiesShiro.getKeyList();
        Map<String,String> map = new LinkedHashMap<>();
        for (Object o : list) {
            String key = o.toString();
            String val = PropertiesUtil.getShiroValue(key);
            map.put(key, val);
        }
        return map;
    }





}
