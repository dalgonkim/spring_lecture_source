package com.test.datasource;


import static org.junit.Assert.assertThat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.commons.dbcp2.BasicDataSource;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
public class DataSourceTest {
	
	@Autowired
	private BasicDataSource dataSource;
	
	
	@Test
	public void test() throws Exception{
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		String sql="select * from tbl_board";
		
		con=dataSource.getConnection();
		
		stmt=con.createStatement();
		
		rs=stmt.executeQuery(sql);
		
		while(rs.next()){
			assertThat(rs.getInt("bno"),greaterThan(0));			
		}
		
	}

}







