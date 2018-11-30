package com.spring.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.
				result.MockMvcResultHandlers.print;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/spring/context/*-context.xml")
@WebAppConfiguration
public class ReplyControllerTest {

		@Autowired
		private WebApplicationContext ctx;
		
		private MockMvc mockMvc;
		
		@Before
		public void setUp(){
			mockMvc=MockMvcBuilders.webAppContextSetup(ctx).build();
		}
		
		@Test
		public void reply_list_test()throws Exception{
			mockMvc.perform(get("/replies/all/12345").param("id", "mimi"))
					.andDo(print())
				    .andExpect(status().isOk())
				    .andExpect(jsonPath("$",hasSize(1)))
				    .andExpect(jsonPath("$[0].rno",is(1)));
		}
		
}









