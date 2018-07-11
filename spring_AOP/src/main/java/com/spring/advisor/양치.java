package com.spring.advisor;

import org.aspectj.lang.JoinPoint;

public class 양치 {
	
	public void chikachika(JoinPoint joinPoint) throws Exception{
		String behavior = joinPoint.getSignature().toShortString();
		System.out.println("pointcut : "+behavior);
		System.out.println("양치하기");
	}
}
