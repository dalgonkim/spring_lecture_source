package com.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/context/servlet-context.xml")
@WebAppConfiguration
public class ParamControllerTest {

	private static final Logger logger
	=LoggerFactory.getLogger(SampleControllerTest.class);
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup(){
		
		this.mockMvc=MockMvcBuilders.webAppContextSetup(this.wac).build();  		
		logger.info("MockMvc setup..................");
	}
	
	@Test
	public void testDoC()throws Exception{
		mockMvc.perform(get("/doC").param("msg", "안녕하세요"))
				.andExpect(status().isOk())
				.andExpect(view().name("result"))
				.andExpect(model().attributeExists("msg"));
	}
	
	
	
	
}









