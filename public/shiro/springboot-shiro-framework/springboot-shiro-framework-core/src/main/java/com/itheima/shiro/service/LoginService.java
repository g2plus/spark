package com.itheima.shiro.service;

import com.itheima.shiro.base.BaseResponse;
import com.itheima.shiro.vo.LoginVO;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;

import java.util.Map;

/**
 * @Description 登陆业务接口
 */

public interface LoginService {

	/**
	 * @Description 登陆路由
	 * @param loginVo 登录参数
	 * @return
	 */
	public Map<String, String> route(LoginVO loginVo) throws UnknownAccountException,IncorrectCredentialsException;

	/**
	 * @Description jwt方式登录
	 @param loginVo 登录参数
	  * @return
	 */
	public BaseResponse routeForJwt(LoginVO loginVo) throws UnknownAccountException,IncorrectCredentialsException;

}
