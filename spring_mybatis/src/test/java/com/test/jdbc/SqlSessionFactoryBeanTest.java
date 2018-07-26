package com.test.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/test/context/dataSource-context.xml")
public class SqlSessionFactoryBeanTest {
	
	@Autowired
	private SqlSessionFactoryBean sqlSessionFactory;
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Test
	public void testSqlSessionFactory()throws Exception{
		System.out.println(sqlSessionFactory);
	}
	
	@Test
	public void testSqlSession()throws Exception{
		System.out.println(sqlSession);
	}
}







