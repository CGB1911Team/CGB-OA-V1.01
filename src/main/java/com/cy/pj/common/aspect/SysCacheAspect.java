package com.cy.pj.common.aspect;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SysCacheAspect {
	
	private Map<String, Object> cache=new ConcurrentHashMap<>();//ConcurrentHashMap线程安全且效率高
	
	@Pointcut("execution(* com.cy.pj.sys.service.impl.SysMenuServiceImpl.findZtreeMenuNodes())")
	//@Pointcut("@annotation(com.cy.pj.common.annotation.RequiredCache)")
	public void doCachePointCut() {}
	
	@Around("doCachePointCut()")
	public Object doCachePObject(ProceedingJoinPoint jp) throws Throwable{
		System.out.println("get data from cache");
		Object obj = cache.get("menuZTree");
		if(obj!=null)  return obj; 
		Object result=jp.proceed(); //这里去调用其他切面，其他切面执行完再往下执行
		System.out.println("put data to cache");
		cache.put("menuZTree", result);
		return result;
	}
}
