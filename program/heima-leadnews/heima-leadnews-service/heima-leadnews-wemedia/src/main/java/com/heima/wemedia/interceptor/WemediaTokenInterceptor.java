package com.heima.wemedia.interceptor;

import com.alibaba.fastjson.JSON;
import com.heima.model.wemedia.pojos.WmUser;
import com.heima.utils.thread.WmThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class WemediaTokenInterceptor implements HandlerInterceptor {

    /**
     * 得到header中的用户信息，并且存入到当前线程中
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.err.println("当前访问URL："+ request.getRequestURI() + "参数：【" + JSON.toJSONString(request.getParameterMap())+"】");
        String userId = request.getHeader("userId");
        if(userId != null){
            //将登录用户的个人信息存入到当前线程中,方便进行信息的获取
            WmUser wmUser = new WmUser();
            wmUser.setId(Integer.valueOf(userId));
            WmThreadLocalUtil.setUser(wmUser);
        }
        return true;
    }

    /**
     * postHandle
     * 调用前提：preHandle返回true
     * 调用时间：Controller方法处理完之后，DispatcherServlet进行视图的渲染之前，也就是说在这个方法中你可以对ModelAndView进行操作
     * 执行顺序：链式Intercepter情况下，Intercepter按照声明的顺序倒着执行。
     * 备注：postHandle虽然post打头，但post、get方法都能处理
     * @param request
     * @param response
     * @param handler
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("请求treadlocal中存储的用户信息");
        WmThreadLocalUtil.clear();
    }

    /**
     *afterCompletion
     *调用前提：preHandle返回true
     *调用时间：DispatcherServlet进行视图的渲染之后
     *多用于清理资源
     * */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }

}
