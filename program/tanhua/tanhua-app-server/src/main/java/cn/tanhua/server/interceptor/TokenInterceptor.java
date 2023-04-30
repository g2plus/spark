package cn.tanhua.server.interceptor;

import cn.tanhua.commons.utils.JWTUtils;
import cn.tanhua.model.pojo.User;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.HandlerInterceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.err.println("当前访问URL："+ request.getRequestURI() + "参数：【" + JSON.toJSONString(request.getParameterMap())+"】");

        //1、获取请求头
        String token = request.getHeader("Authorization");

        //将token验证的代码提升到网关层面进行处理
        //2、使用工具类，判断token是否有效
//        boolean verifyToken = JWTUtils.verifyToken(token);
//        //3、如果token失效，返回状态码401，拦截
//        if(!verifyToken) {
//            response.setStatus(401);
//            return false;
//        }

        //4、如果token正常可用，放行
        //配置拦截器解析token将信息存入到本地线程
        //解析token，获取id和手机号码，构造User对象，存入Threadlocal
        Claims claims = JWTUtils.getClaims(token);
        String mobile = (String) claims.get("mobile");
        Integer id = (Integer) claims.get("id");

        User user = new User();
        user.setId(Long.valueOf(id));
        user.setMobile(mobile);

        UserHolder.set(user);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserHolder.remove();
    }
}
