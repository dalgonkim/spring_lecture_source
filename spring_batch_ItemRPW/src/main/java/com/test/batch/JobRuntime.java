package com.test.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;

public class JobRuntime {
	
	private JobLauncher jobLauncher;
	
	private Job job;
		
	public JobRuntime(){
		
	}
	
	public void setJobLauncher(JobLauncher jobLauncher){
		this.jobLauncher = jobLauncher;
	}
	
	public void setJob(Job job){
		this.job = job;
	}
	
	public void start() throws Exception{
				        
        JobParameters jobParameters = new JobParametersBuilder().addLong("batch-date",
                System.currentTimeMillis()).toJobParameters();
		
        JobExecution exec = jobLauncher.run(job, jobParameters);
        
        System.out.println("Exit Status: " + exec.getStatus());
		
	}
	
}
