package com.test.batch;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class Launcher {

	private static final String SPRING_CONTEXT_CONFIG_FILE_PATH = "spring-context.xml";

    ////////////////////////////////////////////////////////////////////////////	
	
    private static ConfigurableApplicationContext appContext;

    ////////////////////////////////////////////////////////////////////////////
    
    public static void main(String[] args)
    {

        try {        	
          Launcher.appContext = new ClassPathXmlApplicationContext(
          SPRING_CONTEXT_CONFIG_FILE_PATH);

          JobRuntime runtime = (JobRuntime) appContext.getBean("runtime");
        	
          runtime.start();
           
        }
        catch (Exception e) {
            System.out.print("Launcher has failed: " + e.getMessage());
            System.exit(-1);
        }
    }
}
