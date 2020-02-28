package com.cy.pj.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Aspect
@Component
public class SysTimeAspect {
	@Pointcut("bean(*ServiceImpl)")
	public void doTime(){}
 
	/**前置通知*/
	@Before("doTime()")
	public void doBefore(JoinPoint jp){
		System.out.println("time doBefore()");
	}
	
	@After("doTime()")
	public void doAfter(){ //类似于finally{}代码块
		System.out.println("time doAfter()");
	}
	
	/**核心业务正常结束时执行
	 * 说明：假如有after，先执行after,再执行returning*/
	@AfterReturning("doTime()")
	public void doAfterReturning(){
		System.out.println("time doAfterReturning");
	}
	
	/**核心业务出现异常时执行
                 说明：假如有after，先执行after,再执行Throwing*/
	@AfterThrowing("doTime()")
	public void doAfterThrowing(){
		System.out.println("time doAfterThrowing");
	}
	@Around("doTime()")
	public Object doAround(ProceedingJoinPoint jp)
			throws Throwable{
		System.out.println("doAround.before");
		try {
			Object obj=jp.proceed(); //调用本类其他通知>或下一个切面>或目标方法
			return obj;
		} catch (Exception e) {
			System.out.println("doAround.error-->"+e.getMessage());
			throw e;
		}finally {
			System.out.println("doAround.after");
		}
	}

}
