package com.spring.batch;

import org.apache.log4j.Logger;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class JobListener implements JobExecutionListener{
	
	private static final Logger logger=
			Logger.getLogger(JobListener.class);
	
	@Override
	public void afterJob(JobExecution exe) {
		logger.info(">>> Job Executed : "+exe.getJobInstance()
		.getJobName()+", Batch Status : "+exe.getStatus());	
		
	}

	@Override
	public void beforeJob(JobExecution exe) {
		logger.info(">>> Job To be Executed : "+exe.getJobInstance()
		.getJobName());
		
	}
	
	

}
