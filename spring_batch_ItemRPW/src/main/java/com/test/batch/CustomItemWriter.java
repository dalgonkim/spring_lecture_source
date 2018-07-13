package com.test.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class CustomItemWriter implements ItemWriter<String>{

	@Override
	public void write(List<? extends String> input) throws Exception {
		System.out.println("Write: " + input + "\n");		
	}

}
