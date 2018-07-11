package com.test.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
	
	//private Calculator cal=new Calculator();
	
	public static void main(String[] args) {
	
		ApplicationContext ctx=
				new GenericXmlApplicationContext("classpath:application-context.xml");
		Calculator cal=ctx.getBean("calculator",Calculator.class);
		
		CalculatorResult result=
				ctx.getBean("calResult",CalculatorResult.class);
		
		result.result();
		
	}
	
	

}
