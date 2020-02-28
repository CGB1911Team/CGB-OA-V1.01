package com.cy.pj.common.web;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cy.pj.common.vo.JsonResult;
/**
 * 全局异常处理类
 * @ControllerAdvice 描述的类为Spring MVC提供一个全局异常处理类，控制层出现异常后，可以由此类进行处理
 * @author Administrator
 *
 */
//@ControllerAdvice
//@ResponseBody
@RestControllerAdvice
public class GlobalExceptionHandler {
	/**
	 * @ExceptionHandler 描述的方法为spring mvc中的异常处理方法
	 */
	@ExceptionHandler(RuntimeException.class)
	//@ExceptionHandler(value= {ServiceException.class,IllegalArgumentException.class})

	/*下面的参数可以写异常信息的父类*/
	public JsonResult doHandleRuntimeException(RuntimeException e){
	    e.printStackTrace();//也可以写日志
		return new JsonResult(e);//封装异常信息
	}

	@ExceptionHandler(ShiroException.class)
	public JsonResult doHandleShiroException(
			ShiroException e) {
		JsonResult r=new JsonResult();
		r.setState(0);
		if(e instanceof UnknownAccountException) {
			r.setMessage("账户不存在");
		}else if(e instanceof LockedAccountException) {
			r.setMessage("账户已被禁用");
		}else if(e instanceof IncorrectCredentialsException) {
			r.setMessage("密码不正确");
		}else if(e instanceof AuthorizationException) {
			r.setMessage("没有此操作权限");
		}else {
			r.setMessage("系统维护中");
		}
		e.printStackTrace();
		return r;
	}

}
