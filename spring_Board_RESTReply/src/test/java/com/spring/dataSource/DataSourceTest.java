package com.spring.datasource;

import java.sql.Connection;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/root-context.xml")
public class DataSourceTest{

	@Autowired
	private BasicDataSource dataSource; 
	
	@Test
	public void test() throws Exception{
		Connection conn=dataSource.getConnection();
		
		System.out.println(conn);
	}

}






