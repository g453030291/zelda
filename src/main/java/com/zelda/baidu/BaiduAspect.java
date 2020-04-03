package com.zelda.baidu;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * baidu接口切面
 * @author mantou
 */
@Aspect
@Component
public class BaiduAspect {

	@Pointcut("execution(* com.zelda.baidu.Words.*(..))")
	public void BrokerAspect(){
		System.out.println("切入了");
	}

	@Before("BrokerAspect()")
	public void doBefore(){
		System.out.println("doBefore");
	}

	@After("BrokerAspect()")
	public void doAfter(){
		System.out.println("doAfter");
	}

	@AfterReturning("BrokerAspect()")
	public void afterReturning(){
		System.out.println("afterReturning");
	}


	@AfterThrowing("BrokerAspect()")
	public void afterThrowing(){
		System.out.println("afterThrowing");
	}

}
