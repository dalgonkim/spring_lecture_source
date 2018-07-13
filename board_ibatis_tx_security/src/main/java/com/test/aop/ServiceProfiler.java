package com.test.aop;

import java.sql.Connection;

import org.aspectj.lang.ProceedingJoinPoint;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ServiceProfiler {

	
	private SqlMapClient client;
	public void setClient(SqlMapClient client){
		this.client=client;
	}
	
	public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {
		
		String signatureString = joinPoint.getSignature().toShortString();		
		System.out.println(signatureString + " 시작");
		
		Connection conn=client.getDataSource().getConnection();
		
		long start = System.currentTimeMillis();
		try {
			conn.setAutoCommit(false);
			
			Object result = joinPoint.proceed();
			
			conn.commit();
			
			return result;
		}catch(Exception e){
			conn.rollback();
			throw e;
		}finally {
			long finish = System.currentTimeMillis();
			conn.setAutoCommit(true);
			if(conn!=null) conn.close();
			System.out.println(signatureString + " 종료");
			System.out.println(signatureString + " 실행 시간 : " +
					(finish - start) + "ms");
		}
		
	}
}
