package com.spring.batch;

import org.apache.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;

public class JobRuntime {
	
	private static final Logger logger=
			Logger.getLogger(JobRuntime.class);
	
	private JobLauncher jobLauncher;
	private Job job;
	
	public void setJobLauncher(JobLauncher jobLauncher) {
		this.jobLauncher = jobLauncher;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	
	public void launch()throws Exception{
		JobParameters jobParameters = new JobParametersBuilder()
						.addLong("batch-date",System.currentTimeMillis())
						.toJobParameters();
		
		logger.info("jobparameters : "+jobParameters);
		
		JobExecution exec=jobLauncher.run(job,jobParameters);
		
		logger.info("Exit status : "+exec.getStatus());
	}
}








