package com.dokdo.dto;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/dokdo/context/root-context.xml")
public class BoardVOTest {

	@Autowired
	private BoardVO boardVO;
	
	@Before
	public void set(){
			boardVO=new BoardVO(1,"독도후기","너무좋았어요..",new Timestamp(new Date().getTime()));
	}
	
	@Test
	public void testGetTime()throws Exception{
		System.out.println(boardVO);
	}
}
