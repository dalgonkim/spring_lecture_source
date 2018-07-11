package com.spring.batch;


import org.apache.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class GoodbyeTasklet implements Tasklet{

	private static final Logger logger=
			Logger.getLogger(GoodbyeTasklet.class);
	
	
	@Override
	public RepeatStatus execute(StepContribution arg0, 
								ChunkContext arg1) throws Exception {
		
		logger.info("GoodBye! Spring Batch Tasklet!");
		
		return RepeatStatus.FINISHED;
	}
	
	
}
