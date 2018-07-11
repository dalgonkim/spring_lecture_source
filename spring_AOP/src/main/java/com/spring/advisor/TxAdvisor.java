package com.spring.advisor;

import org.aspectj.lang.ProceedingJoinPoint;

public class TxAdvisor {
	
	public Object trace(ProceedingJoinPoint joinPoint)throws Throwable{
		String signatureString=joinPoint.getSignature().toShortString();
		System.out.println("txAdvice start...!!");
		
		try{
			Object result=joinPoint.proceed();
			return result;
		}finally{
			System.out.println("txAdvice end...!!");
		}
		
	}
}
