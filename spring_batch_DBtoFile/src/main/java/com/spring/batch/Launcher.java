package com.spring.batch;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Launcher {

	public static void main(String[] args) throws Exception{

		ApplicationContext ctx=
				new GenericXmlApplicationContext("context/*-context.xml");
		JobRuntime runtime=(JobRuntime)ctx.getBean("runtime");
		runtime.launch();

	}

}
