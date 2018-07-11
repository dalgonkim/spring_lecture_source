package com.spring.batch;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class StepListener implements StepExecutionListener{
	
	private static final Logger logger=
			Logger.getLogger(StepListener.class);
	
	@Override
	public ExitStatus afterStep(StepExecution exe) {
		logger.info("### Step Executed : "+exe.getStepName());
		return null;
	}

	@Override
	public void beforeStep(StepExecution exe) {
		 logger.info("### Step Executed : "+exe.getStepName());		
	}
		

}
