package com.cy.pj.common.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

import org.apache.tomcat.util.net.IPv6Utils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.cy.pj.common.annotation.RequiredCache;
import com.cy.pj.common.annotation.RequiredLog;
import com.cy.pj.common.util.IPUtils;
import com.cy.pj.common.util.ShiroUtils;
import com.cy.pj.sys.entity.SysLog;
import com.cy.pj.sys.service.SysLogService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
/**
 * 通过@Aspect注解描述的类型，为AOP中的一种切面类型，在这种切面类型中通常要定义
 * 两部分内容：
 * 1）切入点（PointCut）
 * 2）通知(Advice)
 * 
 * @author Administrator
 *
 */
//@Order(1) //数子越小优先级越高
@Aspect
@Component
@Slf4j
public class SysLogAspect {
	/**
	 * @Pointcut 用于定义切入点
	 * bean(SysUserServiceImpl):切入点表达式，SysUserServiceImpl为
	 * spring容器中bean的名字。在当前应用中SysUserServiceImpl代表bean
	 * 对象中所有方法的集合为切入点（这个切入点中的任意一个方法执行时，都要进行功能扩展）
	 */
	//@Pointcut("bean(sysUserServiceImpl)&&within(a.b.c)") 组合方式
	//@Pointcut("bean(sysUserServiceImpl)")
	@Pointcut("@annotation(com.cy.pj.common.annotation.RequiredLog)")
	public void doPointCut() {}//方法实现不需要写任何内容
	
	/**
	 * @Around 注解用于描述通知(Advice),切面中的方法除了切入点都是通知，
	 * 通知中要实现你扩展的功能
	 * @Around 注解描述的通知为Advice中的一种环绕通知，环绕通知中可以手动调用
	 * 目标方法之前和之后进行额外业务的实现
	 * 
	 * @param jp 连接点，对于@Around描述的方法，参数类型要求ProceedingJoinPoint
	 * @return 目标方法的返回值，对于@Around描述的方法，其返回值类型为Object
	 * @throws Throwable
	 */
	@Around("doPointCut()") //描述doPointCut()方法中的@Pointcut注解中内容为切入点表达式
	public Object logAround(ProceedingJoinPoint jp)
	throws Throwable{
		long start=System.currentTimeMillis();
		log.info("method start {}",start);
		
		try {
			Object result=jp.proceed();//调用本类中其他通知，或下一个切面，或目标方法
			long end=System.currentTimeMillis();
			log.info("method end {}",end);
			saveLog(jp,end-start);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("method error {}",System.currentTimeMillis());
			throw e;
		}
		
		
	}

	@Autowired
	private SysLogService sysLogService;
	/**
	 * 保存用户行为日志
	 *	1、获取用户行为日志
	 *	2、封装用户行为日志
	 *	3、存储用户行为日志
	 *
	 * @param jp
	 * @param time
	 */
	private void saveLog(ProceedingJoinPoint jp, long time) 
	throws Exception{
		//获取包名+类名+方法名
		//获取方法签名信息：方法目标对象类型的类全名+方法名
		MethodSignature ms=(MethodSignature)jp.getSignature();
		Class<?> targetClass = jp.getTarget().getClass();
		String dType=targetClass.getName();
		String methodName=ms.getName();
		String targetClassMethod=dType+"."+methodName;
		
		//获取方法参数
		//String params=new ObjectMapper().writeValueAsString(jp.getArgs());
		String params=Arrays.toString(jp.getArgs()); 
		
		//获取操作名
		Method targetMethod = 
				targetClass.getDeclaredMethod(ms.getName(), ms.getParameterTypes());
		RequiredLog rLog = 
				targetMethod.getAnnotation(RequiredLog.class);
		String operation="";
		if(rLog.operation()!=null) operation=rLog.operation();
		
		SysLog sysLog=new SysLog();
		
		sysLog.setIp(IPUtils.getIpAddr());
		sysLog.setUsername(ShiroUtils.getUsername());
		sysLog.setOperation(operation);
		sysLog.setMethod(targetClassMethod);
		sysLog.setParams(params);
		sysLog.setTime(time);
		sysLog.setCreatedTime(new Date());
		
//		new Thread() {
//			public void run() {
//				sysLogService.saveObject(sysLog);
//			};
//		}.start();
	
		sysLogService.saveObject(sysLog);
	}
	
}
