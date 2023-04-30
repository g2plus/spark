package com.itheima.shiro.core.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * 自定义的filter
 * @Description：角色or判断过滤器
 */
public class RolesOrAuthorizationFilter extends AuthorizationFilter {


    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {

        Subject subject = getSubject(request, response);
        String[] rolesArray = (String[]) mappedValue;

        if (rolesArray == null || rolesArray.length == 0) {
            //no roles specified, so nothing to check - allow access.
            return true;
        }

        Set<String> roles = CollectionUtils.asSet(rolesArray);
        for (String role : roles) {
            //判断是否有此角色
            boolean flag = subject.hasRole(role);
            //有，继续循环
            if (flag){
                return flag;
            }//无，继续循环
        }
        //循环结束，返回false表示不可进行操作。
        return false;
    }

}
