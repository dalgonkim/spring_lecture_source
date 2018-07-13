package com.test.batch;


import org.springframework.batch.item.ItemProcessor;

public class CustomItemProcessor implements ItemProcessor<String, String>{

	@Override
	public String process(String input) throws Exception {
		
		if (input.contains("***")) {
            input = input.substring(3, input.length());
            input = "Mr. " + input;
        } else if (input.contains("+++")) {
            input = input.substring(3, input.length());
            input = "Mrs. " + input;
        } else
            return null;
        System.out.println("Process : " + input);
        
        return input;
	}

}
