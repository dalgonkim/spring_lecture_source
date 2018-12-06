package com.spring.dataSource;

import java.sql.Connection;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.not;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/spring/context/dataSource-context.xml")
public class DataSourceTest {
	
	@Autowired
	private BasicDataSource dataSource;
	
	@Test
	public void dataSourceTest()throws Exception{
		Connection con=dataSource.getConnection();
		
		assertThat(con,not(nullValue()));
	}
	
	
}









