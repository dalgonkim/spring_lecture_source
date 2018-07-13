package com.test.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ibatis.sqlmap.client.SqlMapClient;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/com/dokdo/context/dataSource-context.xml")
public class SqlSessionFactoryBeanTest {
	
	@Autowired
	private SqlMapClient sqlClient;
	
	@Test
	public void testSqlMapClient() throws Exception{
		System.out.println(sqlClient);
	}
	
	

}










